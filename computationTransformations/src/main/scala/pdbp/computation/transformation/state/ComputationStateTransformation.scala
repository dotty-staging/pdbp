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

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.state.State

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationStateTransformation[
    S, 
    C[+ _]: Computation
          : [C[+ _]] => State[S, Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with State[S, Kleisli[T]] {

  private val implicitState: State[S, Kleisli[C]] =
    implicitly[State[S, Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] val `s>-->u`: S `=>T` Unit = { s =>
    transform(implicitState.`s>-->u`(s))
  }

  override private[pdbp] val `u>-->s`: Unit `=>T` S = { u =>
    transform(implicitState.`u>-->s`(u))
  }  

}