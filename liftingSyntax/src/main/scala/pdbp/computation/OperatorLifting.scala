package pdbp.computation

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

private[pdbp] trait OperatorLifting[C[+ _]] {

  private[pdbp] def liftOperator[Z, Y, X](
      `(z&&y)=>x`: (Z && Y) => X): (C[Z] && C[Y]) => C[X] =
    lift2(`(z&&y)=>x`)

  private[pdbp] def lift2[Z, Y, X]: ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] =
    liftOperator

}
