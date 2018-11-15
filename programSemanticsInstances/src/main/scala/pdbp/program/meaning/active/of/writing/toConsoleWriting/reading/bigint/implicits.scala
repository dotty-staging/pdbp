package pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint

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

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.computation.meaning.of.reading.reading.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

import pdbp.program.instances.active.reading.bigint.implicits.readingProgram

import pdbp.program.meaning.active.of.active.implicits.identity

import pdbp.program.meaning.active.of.writing.toConsoleWriting.implicits.effectExecuting

object implicits {

  implicit object readingEffectExecuting
      extends ComputationMeaningTransformation[BigInt,
                                               WritingActive[ToConsoleWriting],
                                               Active]()
      with ImplicitComputationMeaningTransformation[
        WritingActive[ToConsoleWriting],
        Active,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ComputationMeaning[ReadingWritingActive[BigInt, ToConsoleWriting],
                              ReadingActive[BigInt]]()
      with ProgramMeaning[`=>RWA`[BigInt, ToConsoleWriting], `=>RA`[BigInt]]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      effectExecuting

  }

}
