package pdbp.computation.transformation.delimiting

//       _______         __    __        _______
//      / ___  /\       / /\  / /\      / ___  /\
//     / /__/ / / _____/ / / / /_/__   / /__/ / /
//    / _____/ / / ___  / / / ___  /\ /____  / /
//   / /\____\/ / /__/ / / / /__/ / / \___/ / /
//  /_/ /      /______/ / /______/ /     /_/ /
//  \_\/       \______\/  \______\/      \_\/
//                                           v1.0
//  Program Description Based Programming Library
//  author        Luc Duponcheel        2017-2018

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.types.prompt.Prompt

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.Program

import pdbp.program.state.State

import pdbp.program.control.Control

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

import pdbp.computation.transformation.state.StateTransformation._

object ControlTransformation {

  trait Stack[A, -Z]

  private[pdbp] case class EmptyStack[A, -Y]() extends Stack[A, Y]

  private[pdbp] case class PushPrompt[A, -Y](prompt: Prompt[A, Y],
                                             stack: Stack[A, Y])
      extends Stack[A, Y]

  private[pdbp] case class PushProg[C[+ _], STC[+ _], A, -Z, +Y, YY >: Y](
      prog: Prog[C, STC, A, Z, YY],
      stack: Stack[A, YY])
      extends Stack[A, Z]

  private[pdbp] type StackTransformer[A, -Z, +Y] = Stack[A, Y] => Stack[A, Z]

  private[pdbp] case class Prog[C[+ _], STC[+ _], A, -Z, +Y](
      `z=>ctcy`: Thunk[Z => DelimitingTransformed[A, C, STC][Y]])

  private[pdbp] type DelimitingTransformed[A, C[+ _], STC[+ _]] = [+Z] => Stack[A, Z] => STC[A]

}

import ControlTransformation._

private[pdbp] trait ControlTransformation[
    A,
    C[+ _]: Computation,
    STC[+ _]: Computation
            : [STC[+ _]] => State[Long, Kleisli[STC]]](
              implicit `c~u~>stc`: C `~U~>` STC
              )
    extends ComputationTransformation[C, DelimitingTransformed[A, C, STC]]
    with Control[A, Kleisli[DelimitingTransformed[A, C, STC]]] {

  private type `=>STC` = Kleisli[STC]

  private type CTC = DelimitingTransformed[A, C, STC]
  private type `=>CTC` = Kleisli[CTC]

  private val implicitComputation = implicitly[Computation[STC]]

  import implicitComputation.{bind => bindSTC}

  private val implicitProgram = implicitly[Program[Kleisli[STC]]]

  import implicitProgram.{
    `z>-->z` => `z=>stcz`,
    seqCompose => seqComposeSTC,
    function => functionSTC
  }

  private val implicitLongState = implicitly[State[Long, Kleisli[STC]]]

  import implicitLongState._

  private def `z=>stca`[Z] = `z=>stcz`.asInstanceOf[Z `=>STC` A]

  private def stackToStateProgram[Z]: Stack[A, Z] => Z `=>STC` A = {
    case emptyStack: EmptyStack[_, _] =>
      `z=>stca`
    case pushPrompt: PushPrompt[_, _] =>
      val stack = pushPrompt.stack
      stackToStateProgram(stack)
    case pushProg: PushProg[_, STC, _, Z, _, _] =>
      val prog = pushProg.prog
      val stack = pushProg.stack
      z =>
        prog.`z=>ctcy`.eval(z)(stack)
  }

  private def pushProg[Z, Y]
    : Prog[C, STC, A, Z, Y] => StackTransformer[A, Z, Y] = { prog =>
    PushProg[C, STC, A, Z, Y, Y](prog, _)
  }

  private def pushPrompt[Y]: (Prompt[A, Y] && CTC[Y]) => CTC[Y] = {
    case (prompt, ctcy) =>
      stack =>
        ctcy(PushPrompt[A, Y](prompt, stack))
  }

  private def popStack[Z, Y]: (
      Prompt[A, Y],
      Stack[A, Z]) => (StackTransformer[A, Z, Y] && Stack[A, Y]) =
    (prompt, stack) =>
      stack match {
        case emptyStack_ : EmptyStack[_, _] =>
          throw new IllegalArgumentException(
            "Prompt was not found on the stack.")
        case pushPrompt_ : PushPrompt[_, _] =>
          val prompt_ = pushPrompt_.prompt
          val stack_ = pushPrompt_.stack
          if (prompt_ == prompt) {
            (identity.asInstanceOf[StackTransformer[A, Z, Y]],
             stack_.asInstanceOf[Stack[A, Y]])
          } else {
            popStack(prompt, stack_) match {
              case (stackTransformer, stack) =>
                (stackTransformer andThen { PushPrompt(prompt_, _) }, stack)
            }
          }
        case pushProg_ : PushProg[C, STC, _, _, _, _] =>
          val stack_ = pushProg_.stack
          val prog_ = pushProg_.prog
          popStack(prompt, stack_) match {
            case (stackTransformer, stack) =>
              (stackTransformer andThen { PushProg(prog_, _) }, stack)
          }
    }

  private def stackTransformerToProgramTransformer[Z, Y, X]: Prompt[A, Y] => (
      StackTransformer[A, X, Y] => (Z `=>CTC` Y)) => (Z `=>CTC` X) =
    prompt =>
      `st=>(z=>ctcy)` =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (stackTransformer, stack) =>
                `st=>(z=>ctcy)`(stackTransformer)(z)(stack)
    }

  override private[pdbp] val transform: C `~U~>` CTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): CTC[Z] = { stack =>
      seqComposeSTC(`c~u~>stc`.apply[Z], Thunk(stackToStateProgram(stack)))(cz)
    }
  }

  override private[pdbp] def bind[Z, Y]
    : (CTC[Z] && Thunk[Z `=>CTC` Y]) => CTC[Y] = { (ctcz, `z=>ctcy`) =>
    pushProg(Prog(`z=>ctcy`)) andThen ctcz
  }

  override private[pdbp] def newPrompt[Y]: Unit `=>CTC` Prompt[A, Y] = {
    u => stack =>
      seqComposeSTC(
        seqComposeSTC(
          readStateModifiedWith(_ + 1),
          Thunk(functionSTC(Prompt(_)))
        ),
        Thunk(stackToStateProgram(stack))
      )(u)
  }

  override def reset[Z, Y]: (Prompt[A, Y] => (Z `=>CTC` Y)) => (Z `=>CTC` Y) = {
    `p=>(z=>ctcy)` => z =>
      bind(newPrompt(()), Thunk({ prompt =>
        pushPrompt((prompt, `p=>(z=>ctcy)`(prompt)(z)))
      }))
  }

  override def shift[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        stackTransformerToProgramTransformer(prompt) { stackTransformer =>
          { z =>
            pushPrompt(prompt, `(x=>ctcy)=>(z=>ctcy)` { x =>
              pushPrompt(prompt, stackTransformer andThen result(x))
            }(z))
          }
    }

  override def shift0[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        stackTransformerToProgramTransformer(prompt) { stackTransformer =>
          `(x=>ctcy)=>(z=>ctcy)` { x =>
            pushPrompt(prompt, stackTransformer andThen result(x))
          }
    }

  override def control[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        stackTransformerToProgramTransformer(prompt) { stackTransformer =>
          { z =>
            pushPrompt(prompt, `(x=>ctcy)=>(z=>ctcy)` { x =>
              stackTransformer andThen result(x)
            }(z))
          }
    }

  override def control0[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        stackTransformerToProgramTransformer(prompt) { stackTransformer =>
          `(x=>ctcy)=>(z=>ctcy)` { x =>
            stackTransformer andThen result(x)
          }
    }

  override def abort[Z, Y, X]
    : (Z `=>CTC` Y) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `z=>ctcy` =>
      prompt =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (_, stack) => `z=>ctcy`(z)(stack)
    }

  override def stop[Y, X]: Prompt[A, Y] => (Y `=>CTC` X) =
    abort[Y, Y, X](`z>-->z`)

}

/*
  override def shift[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (stackTransformer, stack) =>
                pushPrompt(prompt, `(x=>ctcy)=>(z=>ctcy)` { x =>
                  pushPrompt(prompt, stackTransformer andThen result(x))
                }(z))(stack)
    }
 */

/*
  override def shift0[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (stackTransformer, stack) =>
                `(x=>ctcy)=>(z=>ctcy)` { x =>
                  pushPrompt(prompt, stackTransformer andThen result(x))
                }(z)(stack)
    }
 */
/*
  override def control[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (stackTransformer, stack) =>
                pushPrompt(prompt, `(x=>ctcy)=>(z=>ctcy)` { x =>
                  stackTransformer andThen result(x)
                }(z))(stack)
    }
 */

/*

  override def control0[Z, Y, X]
    : ((X `=>CTC` Y) => (Z `=>CTC` Y)) => (Prompt[A, Y] => (Z `=>CTC` X)) =
    `(x=>ctcy)=>(z=>ctcy)` =>
      prompt =>
        z =>
          stack =>
            popStack(prompt, stack) match {
              case (stackTransformer, stack) =>
                `(x=>ctcy)=>(z=>ctcy)` { x =>
                  stackTransformer andThen result(x)
                }(z)(stack)
    }

 */
// fyi: reset can als be defined pointless

// override def reset[Z, Y]: (Prompt[A, Y] => (Z `=>CTC` Y)) => (Z `=>CTC` Y) =
//       `p=>(z=>ctcy)` => {
//         z =>
//           val promptToComputation: Prompt[A, Y] => CTC[Y] =
//             function {
//               prompt =>
//                 `p=>(z=>ctcy)`(prompt)(z)
//             }
//           val pushPromptProgram: (Prompt[A, Y] && CTC[Y]) `=>CTC` CTC[Y] =
//             function(pushPrompt[Y])
//           flatSeqCompose(
//                 newPrompt,
//                 `let` {
//                   promptToComputation
//                 } `in` {
//                   pushPromptProgram
//                 }
//            ) (z)
//       }
/*
  class Catch[Z, Y, X](`x=>ctcy`: X `=>CTC` Y) {
    def `catch`(`z=>ctcy`: Z `=>CTC` Y): Z `=>CTC` Try[Y] =
      replaceBy[Z, Try[Y], X] { (z: Z) =>
        try {
         bind(`z=>ctcy`(z), y => result(ok(y)) )
        } catch safely {
          case t =>
            result(ko(t))
        }
      } fragment { (x: X) =>
        try {
         bind(`x=>ctcy`(x), y => result(ok(y)) )
        } catch safely {
          case t =>
            result(ko(t))
        }
      }
  }



  def `try`[Z, Y](`z=>ctcy`: Z `=>CTC` Y): Catch[Z, Y] =
    new Catch[Z, Y](`z=>ctcy`)

  class Catch[Z, Y](`z=>ctcy`: Z `=>CTC` Y)  {

    val `z=>ctcty`: Z `=>CTC` Try[Y] =
      seqCompose(`z=>ctcy`, y => result(ok(y)))

    def `catch`(`z==>ctcty`: Z `=>CTC` Try[Y]): Z `=>CTC` Try[Y] = { z =>
      try {
        `z=>ctcty`(z)
      } catch {
        case _ =>
        (replaceBy[Z, Try[Y], Try[Y]] {
          `z==>ctcty`
        } fragment {
          `z=>ctcty`
        })(z)
      }
    }

  }

 */

// }
