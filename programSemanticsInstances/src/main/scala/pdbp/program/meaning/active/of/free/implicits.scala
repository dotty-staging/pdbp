package pdbp.program.meaning.active.of.free

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
import pdbp.computation.meaning.of.free.tailrecFolding.ComputationMeaningTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.implicits.program
import pdbp.program.instances.active.free.implicits.freeProgram

import pdbp.program.meaning.active.of.active.implicits.identity

object implicits {

  implicit object tailrecFolding
      extends ComputationMeaningTransformation[Active, Active]()
      with ImplicitComputationMeaningTransformation[Active,
                                                    Active,
                                                    FreeActive,
                                                    Active]()
      with ComputationMeaning[FreeActive, Active]()
      with ProgramMeaning[`=>FA`, `=>A`]() {

    override private[pdbp] implicit val implicitComputationMeaning
      : ComputationMeaning[Active, Active] =
      identity

  }

}
