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

import pdbp.types.product.productType._
import pdbp.types.trying.tryType._

object tryingUtils {

  def fold[Z, Y]: ((Z => Y) && (Throwable => Y)) => (Try[Z] => Y) = {
    case (z2y, t2y) => {
      case Ok(z) =>
        val y = z2y(z)
        y
      case Ko(t) =>
        val y = t2y(t)
        y
    }
  }

}