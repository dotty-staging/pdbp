package examples.utils

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

import pdbp.utils.effectfulReadUtils._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.utils.toConsoleWritingUtils._

object implicits {

  implicit val bigIntEffectfullyReadFromConsole: BigInt =
    bigIntEffectfullyReadFromConsoleWithMessage(
      "please type an integer to read")

  implicit val convertFactorialOfBigIntReadToToConsoleWriting
    : BigInt => ToConsoleWriting =
    toToConsoleWritingLineWithMessage(
      "the factorial value of the integer read is"
    )

}
