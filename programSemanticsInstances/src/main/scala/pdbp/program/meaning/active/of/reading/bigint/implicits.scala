package pdbp.program.meaning.active.of.reading.bigint

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

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation
import pdbp.computation.meaning.of.reading.reading.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.reading.bigint.implicits.readingProgram

import pdbp.program.meaning.active.of.active.implicits.identity
import pdbp.program.meaning.active.of.reading.ReadingMeaning

object implicits {

  implicit object reading
      extends ReadingMeaning[BigInt]
      with ComputationMeaningTransformation[BigInt, Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    ReadingActive[BigInt],
                                                    ReadingActive[BigInt]]()
      with ComputationMeaning[ReadingActive[BigInt], ReadingActive[BigInt]]()
      with ProgramMeaning[`=>RA`[BigInt], `=>RA`[BigInt]]()

}
