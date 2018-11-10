package pdbp.computation.meaning.transformation

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

import pdbp.types.implicitFunctionType.`I=>`

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.meaning.ComputationMeaning

private[pdbp] trait ImplicitComputationMeaningTransformation[
    C[+ _]: Computation,
    M[+ _]: Computation,
    TC[+ _]: Computation,
    TM[+ _]: Computation]
    extends (ComputationMeaning[C, M] `I=>` ComputationMeaning[TC, TM])
    with ComputationMeaning[TC, TM] {

  // todo: is lazy maybe needed here?
  private[pdbp] implicit val implicitComputationMeaning: ComputationMeaning[C, M]

  override private[pdbp] lazy val unaryTransformation: TC `~U~>` TM =
    this(implicitComputationMeaning).unaryTransformation

}
