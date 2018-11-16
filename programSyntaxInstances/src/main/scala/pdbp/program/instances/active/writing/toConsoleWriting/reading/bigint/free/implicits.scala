package pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.free

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

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.writing.reading.free.FreeReadingWritingTransformation
import pdbp.computation.transformation.reading.ComputationReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

import pdbp.program.instances.active.writing.reading.free.FreeReadingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

object implicits {

  implicit object freeReadingWritingProgram
      extends FreeReadingWritingProgram[BigInt, ToConsoleWriting]()
      with Computation[FreeReadingWritingActive[BigInt, ToConsoleWriting]]()
      with Program[`=>FRWA`[BigInt, ToConsoleWriting]]()
      with Reading[BigInt, `=>FRWA`[BigInt, ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>FRWA`[BigInt, ToConsoleWriting]]()
      with ComputationTransformation[
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]]()
      with FreeTransformation[ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ComputationReadingTransformation[
        BigInt,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]
      ]()
      with ComputationWritingTransformation[
        ToConsoleWriting,
        ReadingWritingActive[BigInt, ToConsoleWriting],
        FreeReadingWritingActive[BigInt, ToConsoleWriting]
      ]()
      with FreeReadingWritingTransformation[
        BigInt,
        ToConsoleWriting,
        ReadingWritingActive[BigInt, ToConsoleWriting]]()

}
