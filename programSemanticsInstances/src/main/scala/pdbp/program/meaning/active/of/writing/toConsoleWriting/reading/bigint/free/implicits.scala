package pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.free

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

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.computation.meaning.of.free.tailrecFolding.ComputationMeaningTransformation

import pdbp.program.instances.types.active.reading.readingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

import pdbp.program.instances.active.reading.bigint.implicits.readingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free.implicits.freeReadingWritingProgram

import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.implicits.readingImplicitEffectExecuting

object implicits {

  implicit object tailrecFoldingReadingEffectExecuting
      extends ComputationMeaningTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ImplicitComputationMeaningTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt],
        FreeReadingWritingActive[BigInt, ToConsoleWriting],
        ReadingActive[BigInt]]()
      with ComputationMeaning[FreeReadingWritingActive[BigInt, ToConsoleWriting],
                              ReadingActive[BigInt]]()
      with ProgramMeaning[`=>FRWA`[BigInt, ToConsoleWriting], `=>RA`[BigInt]]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      readingImplicitEffectExecuting

  }

}
