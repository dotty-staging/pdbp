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

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

object productUtils {

  def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z = { (z, _) =>
    z
  }

  def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y = { (_, y) =>
    y
  }

  def `(z&&y&&x)=>(y&&x)`[Z, Y, X]: (Z && Y && X) => (Y && X) = {
    case ((_, y), x) => (y, x)
  }

  def `((z=>y)&&z)=>y`[Z, Y]: ((Z => Y) && Z) => Y = { (`z=>y`, z) =>
    `z=>y`(z)
  }
  
  def `(z&&y)=>(z&&y)`[Z, Y]: (Z && Y) => (Z && Y) = { `z&&y` =>
    `z&&y`
  }

  def `(z=>y)=>((z&&x)=>(y&&x)))`[Z, Y, X]
    : (Z => Y) => ((Z && X) => (Y && X)) = { `z=>y` => (z, x) =>
    (`z=>y`(z), x)
  }

  def and[Z, X, Y, W]: ((Z => X) && Thunk[Y => W]) => ((Z && Y) => (X && W)) = {
    (z2x, y2w) => (z, y) =>
      (z2x(z), y2w.eval(y))
  }

  def product[Z, Y, X]: ((Z => Y) && Thunk[Z => X]) => (Z => (Y && X)) = {
    (z2y, z2x) => z =>
      (z2y(z), z2x.eval(z))
  }

}
