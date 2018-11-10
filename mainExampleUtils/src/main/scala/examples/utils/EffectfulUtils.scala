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

import pdbp.program.Program

class EffectfulUtils[>-->[- _, + _]: Program]
    extends pdbp.program.utils.EffectfulUtils[>-->] {

  lazy val effectfulReadBigIntFromConsole: Unit >--> BigInt =
    effectfulReadBigIntFromConsoleWithMessage("please type an integer")

  lazy val effectfulWriteFactorialOfBigIntToConsole: BigInt >--> Unit =
    effectfulWriteLineToConsoleWithMessage(
      "the factorial value of the integer is")

}
