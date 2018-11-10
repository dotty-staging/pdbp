package examples.mainPrograms

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

import pdbp.program.compositionOperator._

import examples.programs.Factorial

trait MainFactorial[>-->[- _, + _]: Program] {

  private object factorialObject extends Factorial[>-->]

  import factorialObject.factorial

  val producer: Unit >--> BigInt

  val consumer: BigInt >--> Unit

  lazy val mainFactorial: Unit >--> Unit =
    producer >-->
      factorial >-->
      consumer

}
