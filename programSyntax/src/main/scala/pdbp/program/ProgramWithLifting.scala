package pdbp.program

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
import pdbp.types.kleisli.unary.kleisliUnaryTypeConstructorType._

import pdbp.computation.Lifting

private[pdbp] trait ProgramWithLifting[>-->[- _, + _]]
    extends Program[>-->]
    with Lifting[Kleisli[>-->]] {

  private type C = Kleisli[>-->]

  private[pdbp] override def lift0[Z]: Z => C[Z] = 
    `z=>(u>-->z)`

  private[pdbp] override def lift2[Z, Y, X]
    : ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] = { `(z&&y)=>x` => (cy, cz) =>
    seqCompose(product(cy, Thunk(cz)), Thunk(function(`(z&&y)=>x`)))
  }

}
