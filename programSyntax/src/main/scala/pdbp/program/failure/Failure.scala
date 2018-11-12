package pdbp.program.failure

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

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

trait Failure[>-->[- _, + _]] {

  // raising failure

  private[pdbp] def failure[Z, Y]: Thunk[Z => Throwable] => Z >--> Y

  def `throw`[Z, Y](t: => Throwable): Z >--> Y =
    failure(Thunk(_ => t))

  // handling exceptions

  def `try`[Z, Y]: (Z >--> Y) => Catch[Z, Y]

  trait Catch[Z, Y] {
    def `catch`(`(z&&t)>-->y`: => (Z && Throwable) >--> Y): Z >--> Y
  }

  // handling failure

  def trying[Z, Y]: (Z >--> Y) => Catching[Z, Y]

  trait Catching[Z, Y] {
    def catching(`t>-->y`: => Throwable >--> Y): Z >--> Y
  }

}
