package pdbp.program.utils

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

import pdbp.utils.effectfulFunctionUtils._

class EffectfulUtils[>-->[- _, + _]: Program] {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  def effectfulReadBigIntFromConsoleWithMessage(
      message: String): Unit >--> BigInt =
    function(effectfulReadBigIntFromConsoleWithMessageFunction(message))

  def effectfulWriteToConsoleWithMessageLine[Y](message: String): Y >--> Unit =
    function(effectfulWriteToConsoleWithMessageLineFunction(message))

}
