package pdbp.computation.transformation.state

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

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.state.State

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object StateTransformation {

  private[pdbp] type StateTransformed[S, C[+ _]] = [+Z] => S => C[(S, Z)]

}

import StateTransformation._

private[pdbp] trait StateTransformation[S, C[+ _]: Computation]
    extends ComputationTransformation[C, StateTransformed[S, C]]
    with State[S, Kleisli[StateTransformed[S, C]]] {

  private type STC = StateTransformed[S, C]
  private type `=>STC` = Kleisli[STC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC, bind => bindC}

  override private[pdbp] val transform: C `~U~>` STC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): STC[Z] = { s =>
      bindC(cz, Thunk({ z =>
        resultC((s, z))
      }))
    }
  }

  override private[pdbp] def bind[Z, Y]
    : (STC[Z] && Thunk[Z => STC[Y]]) => STC[Y] = { (stcz, `z>=stcy`) => s =>
    bindC(stcz(s), Thunk({ (s, z) =>
      `z>=stcy`.eval(z)(s)
    }))
  }

  override private[pdbp] val `u>-->s`: Unit `=>STC` S = { u => s =>
    resultC((s, s))
  }

  override private[pdbp] val `s>-->u`: S `=>STC` Unit = { s => _ =>
    resultC((s, ()))
  }

}
