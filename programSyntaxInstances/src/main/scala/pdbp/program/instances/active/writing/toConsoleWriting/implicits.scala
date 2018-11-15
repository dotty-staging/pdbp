package pdbp.program.instances.active.writing.toConsoleWriting

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
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.WritingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._

import pdbp.program.instances.active.writing.WritingProgram

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object writingProgram
      extends WritingProgram[ToConsoleWriting]()
      with Computation[WritingActive[ToConsoleWriting]]()
      with Program[`=>WA`[ToConsoleWriting]]()
      with Writing[ToConsoleWriting, `=>WA`[ToConsoleWriting]]()
      with ComputationTransformation[Active, WritingActive[ToConsoleWriting]]()
      with WritingTransformation[ToConsoleWriting, Active]()

}
