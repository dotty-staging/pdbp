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

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._

private[pdbp] trait Lifting[C[+ _]]
    extends ObjectLifting[C]
    with FunctionLifting[C]
    with OperatorLifting[C] {

  // lifting apply

  private[pdbp] def liftApply[Z, Y]: (C[Z => Y] && C[Z]) => C[Y] =
    liftOperator(`((z=>y)&&z)=>y`)

  // lifting and

  private[pdbp] def liftProduct[Z, Y]: (C[Z] && C[Y]) => C[Z && Y] =
    liftOperator(`(z&&y)=>(z&&y)`)

  private[pdbp] def liftAnd[Z, Y, X, W]
    : ((Z => C[X]) && Thunk[Y => C[W]]) => ((Z && Y) => C[X && W]) = {
    (`z=>cx`, `y=>cw`) =>
      and(`z=>cx`, `y=>cw`) andThen liftProduct
  }

  // lifting or

  private[pdbp] def liftSum[Z, Y]: (C[Z] || C[Y]) => C[Z || Y] =
    sum(Thunk(lift1[Z, Z || Y](`z=>(z||y)`)),
        Thunk(lift1[Y, Z || Y](`y=>(z||y)`)))

  private[pdbp] def liftOr[Z, Y, X, W]
    : (Thunk[Z => C[X]] && Thunk[Y => C[W]]) => ((Z || Y) => C[X || W]) = {
    (`z=>cx`, `y=>cw`) =>
      or(`z=>cx`, `y=>cw`) andThen liftSum
  }

  // lift1 in terms of lift0 and lift2

  private[pdbp] override def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] = {
    `z=>y` => cz =>
      lift2(`((z=>y)&&z)=>y`)(lift0(`z=>y`), cz)

  }

  // keep on lifting

  private[pdbp] def lift3[Z, Y, X, W]
    : ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] = {
    `((z&&y)&&x)=>w` =>
      `(z=>y)=>((z&&x)=>(y&&x)))`(liftProduct) andThen lift2(`((z&&y)&&x)=>w`)
  }

  // and so on ...

}
