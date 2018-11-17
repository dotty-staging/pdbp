package pdbp.program.instances.active.reading.twobigints

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

import pdbp.types.product.productType._

import pdbp.program.Program
import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation

import pdbp.program.instances.types.active.activeTypes._
import pdbp.program.instances.types.active.reading.readingActiveTypes._

import pdbp.program.instances.active.reading.ReadingProgram

import pdbp.program.instances.active.implicits.program

object implicits {

  implicit object readingProgram
      extends ReadingProgram[BigInt && BigInt]()
      with Computation[ReadingActive[BigInt && BigInt]]()
      with Program[`=>RA`[BigInt && BigInt]]()
      with Reading[BigInt && BigInt, `=>RA`[BigInt && BigInt]]()
      with ComputationTransformation[Active, ReadingActive[BigInt && BigInt]]()
      with ReadingTransformation[BigInt && BigInt, Active]()

}
