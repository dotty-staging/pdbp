package examples.programs.writing.info.reading.argumentAndResultMultiplier

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
import pdbp.types.product.productType._

import pdbp.writable.Writable

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.program.compositionOperator._
import pdbp.program.constructionOperators._

import examples.types.info.types.Info

import examples.programs.HelperPrograms

import examples.programs.writing.info.AtomicPrograms

object FactorialOfArgumentMultipliedByResultMultiplier {
  type ArgumentAndResultMultiplier = BigInt && BigInt
}

import FactorialOfArgumentMultipliedByResultMultiplier._

class FactorialOfArgumentMultipliedByResultMultiplier[
    W: Writable,
    >-->[- _, + _]: Program
                  : [>-->[- _, + _]] => Reading[ArgumentAndResultMultiplier, >-->]
                  : [>-->[- _, + _]] => Writing[W, >-->]]
    extends AtomicPrograms[W, >-->]() 
    with HelperPrograms[>-->]() {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  private val implicitArgumentAndResultMultiplierReading =
    implicitly[Reading[ArgumentAndResultMultiplier, >-->]]

  import implicitArgumentAndResultMultiplierReading._ 

  private val readResultMultiplier = 
    read >--> function { (_, resultMultiplier) =>
      resultMultiplier
    }

  val factorial: (Info => W) `I=>` BigInt >--> BigInt =
    info("factorial") {
      `if`(isZero) {
        one
      } `else` {
        `let` {
          subtractOne >-->
            factorial
        } `in` {
          multiply
        }
      }
    }    

  val factorialMultipliedByResultMultiplier: (Info => W) `I=>` BigInt >--> BigInt =
    (factorial & readResultMultiplier) >--> multiply

}