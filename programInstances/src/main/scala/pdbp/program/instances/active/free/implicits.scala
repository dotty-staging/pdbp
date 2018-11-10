package pdbp.program.instances.active.free

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

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object freeProgram
      extends Computation[FreeActive]
      with Program[`=>FA`]
      with ComputationTransformation[Active, FreeActive]()
      with FreeTransformation[Active]()

}
