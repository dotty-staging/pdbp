package pdbp.utils

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

import scala.io.StdIn.readInt

import pdbp.types.product.productType._

object effectfulFunctionUtils {

  def effectfulReadBigIntFromConsoleWithMessageFunction(
      message: String): Unit => BigInt = { _ =>
    println(message)
    BigInt(readInt())
  }

  def effectfulReadArgumentAndResultMultiplierWithMessagesFunction(
      argumentMessage: String,
      resultMultiplierMessage: String): Unit => BigInt && BigInt = { _ =>
    println(argumentMessage)
    val argument = BigInt(readInt())
    println(resultMultiplierMessage)
    val resultMultiplier = BigInt(readInt())
    (argument, resultMultiplier)
  }

  def effectfulWriteToConsoleWithMessageLineFunction[Y](
      message: String): Y => Unit = { y =>
    println(s"$message\n$y")
  }

  def effectfulWriteToConsoleWithMessageFunction[Y](
      message: String): Y => Unit = { y =>
    print(s"$message$y")
  }

}
