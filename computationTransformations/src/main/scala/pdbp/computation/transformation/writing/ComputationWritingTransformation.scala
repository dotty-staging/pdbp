package pdbp.computation.transformation.writing

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

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] trait ComputationWritingTransformation[
    W, 
    C[+ _]: Computation
          : [C[+ _]] => Writing[W, Kleisli[C]],
    T[+ _]]
    extends ComputationTransformation[C, T]
    with Writing[W, Kleisli[T]] {

  private val implicitWriting: Writing[W, Kleisli[C]] =
    implicitly[Writing[W, Kleisli[C]]]

  private type `=>T` = Kleisli[T]

  override private[pdbp] val `w>-->u`: W `=>T` Unit = 
    transform(implicitWriting.`w>-->u`)

}