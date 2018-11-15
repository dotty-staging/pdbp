package pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint

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

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation
import pdbp.computation.transformation.writing.reading.ReadingWritingTransformation

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.writing.reading.ReadingWritingProgram
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram

object implicits {

  implicit object readingWritingProgram
      extends ReadingWritingProgram[BigInt, ToConsoleWriting]()
      with Computation[ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with Program[`=>RWA`[BigInt, ToConsoleWriting]]()
      with Reading[BigInt, `=>RWA`[BigInt, ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>RWA`[BigInt, ToConsoleWriting]]()
      with ComputationTransformation[
        WritingActive[ToConsoleWriting],
        ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ReadingTransformation[BigInt, WritingActive[ToConsoleWriting]]()
      with ComputationWritingTransformation[
        ToConsoleWriting,
        WritingActive[ToConsoleWriting],
        ReadingWritingActive[BigInt, ToConsoleWriting]]()
      with ReadingWritingTransformation[BigInt,
                                        ToConsoleWriting,
                                        WritingActive[ToConsoleWriting]]()
}
