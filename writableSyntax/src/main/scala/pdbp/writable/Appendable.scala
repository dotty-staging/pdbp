package pdbp.writable

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
import pdbp.types.const.constType._

import pdbp.lifting.OperatorLifting

private[pdbp] trait Appendable[W] extends OperatorLifting[Const[W]] {

  private[pdbp] val append: W && W => W

  override private[pdbp] def lift2[Z, Y, X]
    : ((Z && Y) => X) => ((W && W) => W) = { _ =>
    append
  }

}
