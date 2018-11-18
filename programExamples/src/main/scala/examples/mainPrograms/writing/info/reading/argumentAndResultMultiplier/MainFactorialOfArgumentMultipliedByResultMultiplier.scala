package examples.mainPrograms.writing.info.reading.argumentAndResultMultiplier

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

import pdbp.types.implicitFunctionType._

import pdbp.writable.Writable

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.program.compositionOperator._

import examples.types.info.types.Info

import examples.programs.writing.info.reading.argumentAndResultMultiplier.FactorialOfArgumentMultipliedByResultMultiplier
import examples.programs.writing.info.reading.argumentAndResultMultiplier.FactorialOfArgumentMultipliedByResultMultiplier._

trait MainFactorialOfArgumentMultipliedByResultMultiplier[
    W: Writable,
    >-->[- _, + _]: Program
                  : [>-->[- _, + _]] => Reading[ArgumentAndResultMultiplier, >-->]
                  : [>-->[- _, + _]] => Writing[W, >-->]] {

  private object factorialOfArgumentMultipliedByResultMultiplierObject
      extends FactorialOfArgumentMultipliedByResultMultiplier[W, >-->]

  import factorialOfArgumentMultipliedByResultMultiplierObject.factorialMultipliedByResultMultiplier

  val producer: Unit >--> BigInt

  val consumer: BigInt >--> Unit

  lazy val mainFactorialMultipliedByResultMultiplier
    : (Info => W) `I=>` Unit >--> Unit =
    producer >-->
      factorialMultipliedByResultMultiplier >-->
      consumer

}
