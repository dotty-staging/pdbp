package pdbp.computation.transformation.reading

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

import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationReadingTransformation[
    R, 
    C[+ _]: Computation
          : [C[+ _]] => Reading[R, Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with Reading[R, Kleisli[T]] {

  private val implicitReading: Reading[R, Kleisli[C]] =
    implicitly[Reading[R, Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] val `u>-->r`: Unit `=>T` R = 
    transform(implicitReading.`u>-->r`)

}