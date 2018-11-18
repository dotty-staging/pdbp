package pdbp.computation.transformation.trying

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

import pdbp.utils.failureUtils._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.Program
import pdbp.program.failure.Failure

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

import pdbp.computation.transformation.trying.types.tryType._

import pdbp.computation.transformation.trying.utils.tryUtils._

private[pdbp] object FailureTransformation {

  private[pdbp] type TryingTransformed = [C[+ _]] => [+Z] => C[Try[Z]]

}

import FailureTransformation._

private[pdbp] trait FailureTransformation[C[+ _]: Computation]
    extends ComputationTransformation[C, TryingTransformed[C]]
    with Failure[Kleisli[TryingTransformed[C]]] {

  private type TTC = TryingTransformed[C]

  private type `=>TTC` = Kleisli[TTC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC, bind => bindC}

  private def ok[Z]: Z => TTC[Z] = z => resultC(Ok(z))

  private def ko[Z]: Throwable => TTC[Z] = t => resultC(Ko(t))

  override private[pdbp] val transform: C `~U~>` TTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): TTC[Z] =
      bindC(cz, Thunk(ok))
  }

  override private[pdbp] def bind[Z, Y]
    : (TTC[Z] && Thunk[Z => TTC[Y]]) => TTC[Y] = { (ttcz, `z=>ttcy`) =>
    bindC(ttcz, Thunk(fold(`z=>ttcy`.eval, ko)))
  }

  override private[pdbp] def failure[Z, Y]
    : Thunk[Z => Throwable] => Z `=>TTC` Y = { `z=>t` => z =>
    resultC(Ko(`z=>t`.eval(z)))
  }

  override def `try`[Z, Y]: (Z `=>TTC` Y) => Catch[Z, Y] = { `z=>ttcy` =>
    new {
      override def `catch`(
          `(z&&t)=>ttcy`: => (Z && Throwable) `=>TTC` Y): Z `=>TTC` Y = { z =>
        try {
          `z=>ttcy`(z)
        } catch safely {
          case t: Throwable =>
            bindC(`(z&&t)=>ttcy`((z, t)), Thunk(fold(result, `throw`(t))))
        }
      }
    }
  }

  override def trying[Z, Y]: (Z `=>TTC` Y) => Catching[Z, Y] = { `z=>ttcy` =>
    new {
      override def catching(`t=>ttcy`: => Throwable `=>TTC` Y): Z `=>TTC` Y = {
        z =>
          bindC(`z=>ttcy`(z), Thunk(fold(result, `t=>ttcy`)))
      }
    }
  }

}
