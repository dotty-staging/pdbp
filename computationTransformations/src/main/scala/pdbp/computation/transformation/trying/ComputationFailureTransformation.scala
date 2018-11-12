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
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.failure.Failure

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationFailureTransformation[
    C[+ _]: Computation
          : [C[+ _]] => Failure[Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with Failure[Kleisli[T]] {

  private val implicitFailure: Failure[Kleisli[C]] =
    implicitly[Failure[Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] def failure[Z, Y]: Thunk[Z => Throwable] => Z `=>T` Y  = { 
    `z=>t` =>
      transform(implicitFailure.failure(`z=>t`))
  } 

}
