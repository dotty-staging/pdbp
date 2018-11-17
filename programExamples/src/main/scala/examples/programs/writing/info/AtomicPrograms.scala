package examples.programs.writing.info

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
import pdbp.program.writing.Writing

import examples.programs.HelperPrograms

import examples.types.info.types.Info

trait AtomicPrograms[
    W: Writable, 
    >-->[- _, + _]: Program
                  : [>-->[- _, + _]] => Writing[W, >-->]]
    extends HelperPrograms[>-->] {

  private val implicitProgram = implicitly[Program[>-->]]

  import implicitProgram._

  private val implicitWriting = implicitly[Writing[W, >-->]]

  import implicitWriting._

  // private[examples] def info[Z, Y](
  //     methodName: String): (Z >--> Y) => ((Info => W) `I=>` Z >--> Y) = {
  //   writingInfo { (z, y) => 
  //     s"$methodName($z) => $y"
  //   }
  // } 

  private[examples] def info[Z, Y](
      methodName: String): (Z >--> Y) => ((Info => W) `I=>` Z >--> Y) = {
    writing {
      case (z, y) =>
        Info(s"$methodName($z) => $y")
    }
  }     

  val isZero: (Info => W) `I=>` BigInt >--> Boolean =
    info("isZero") {
      isZeroHelper
    }

  def one[Z]: (Info => W) `I=>` Z >--> BigInt =
    info("one") {
      oneHelper
    }

  val subtractOne: (Info => W) `I=>` BigInt >--> BigInt =
    info("subtractOne") {
      subtractOneHelper
    }

  val multiply: (Info => W) `I=>` (BigInt && BigInt) >--> BigInt =
    info("multiply") {
      multiplyHelper
    }        

}
