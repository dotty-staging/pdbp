package pdbp.program.meaning.active.of.writing.toConsoleWriting

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

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation
import pdbp.computation.meaning.of.writing.toConsoleWriting.effectExecuting.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.writing.writingActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.writing.toConsoleWriting.implicits.writingProgram

import pdbp.program.meaning.active.of.active.implicits.identity

object implicits {

  implicit object effectExecuting
      extends ComputationMeaningTransformation[Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    WritingActive[
                                                      ToConsoleWriting],
                                                    Active]()
      with ComputationMeaning[WritingActive[ToConsoleWriting], Active]()
      with ProgramMeaning[`=>WA`[ToConsoleWriting], `=>A`]() {

    override private[pdbp] implicit val implicitComputationMeaning =
      identity

  }

}
