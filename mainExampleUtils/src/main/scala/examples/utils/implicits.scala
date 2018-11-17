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

import pdbp.types.product.productType._

import pdbp.utils.effectfulFunctionUtils._

import pdbp.utils.effectfulReadUtils._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.utils.toConsoleWritingUtils._

import examples.types.info.types.Info

object implicits {

  implicit lazy val bigIntEffectfullyReadFromConsole: BigInt =
    bigIntEffectfullyReadFromConsoleWithMessage(
      "please type an integer to read")

  implicit lazy val argumentAndResultMultiplierEffectfullyReadFromConsole: BigInt && BigInt =
    argumentAndResultMultiplierEffectfullyReadFromConsoleWithMessages(
      "please type an integer argument to read",
      "please type an integer result multiplier to read"
    )

  implicit val convertFactorialOfBigIntReadToToConsoleWriting
    : BigInt => ToConsoleWriting =
    toToConsoleWritingWithMessageLine(
      "the factorial value of the integer read is"
    )

  implicit val infoToToConsoleWriting: Info => ToConsoleWriting = { info =>
    ToConsoleWriting({ _ =>
      effectfulWriteToConsoleWithMessageFunction("")(info.toString)
    })
  }    

}
