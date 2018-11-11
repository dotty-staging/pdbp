package pdbp.computation.meaning

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

import pdbp.naturalTransformation.binary.`~B~>`
import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.program.meaning.ProgramMeaning

private[pdbp] trait ComputationMeaning[C[+ _]: Computation, M[+ _]: Computation]
    extends ProgramMeaning[Kleisli[C], Kleisli[M]] {

  private[pdbp] lazy val unaryTransformation: C `~U~>` M

  private type `=>C` = Kleisli[C]

  private type `=>M` = Kleisli[M]

  private[pdbp] override lazy val binaryTransformation: `=>C` `~B~>` `=>M` =
    unaryTransformation

}
