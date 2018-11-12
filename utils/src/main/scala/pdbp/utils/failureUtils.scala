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

import scala.util.control.ControlThrowable

object failureUtils {

  def safely[T](
      handler: PartialFunction[Throwable, T]): PartialFunction[Throwable, T] = {
    case t: ControlThrowable =>
      throw t
    case t: Throwable if handler.isDefinedAt(t) =>
      handler(t)
    case t: Throwable =>
      throw t
  }

}
