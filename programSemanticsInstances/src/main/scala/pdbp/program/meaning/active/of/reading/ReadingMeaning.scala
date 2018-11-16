package pdbp.program.meaning.active.of.reading

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

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.of.reading.readingImplicit.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.meaning.active.of.active.implicits.identity

trait ReadingMeaning[R]
    extends ComputationMeaningTransformation[R, Active, Active]
    with ComputationMeaning[ReadingActive[R], ReadingActive[R]] {

  override private[pdbp] implicit val implicitComputationMeaning
    : ComputationMeaning[Active, Active] =
    identity

}
