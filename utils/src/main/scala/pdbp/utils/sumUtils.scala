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
import pdbp.types.Thunk
import pdbp.types.product.productType._
import pdbp.types.sum.sumType._

object sumUtils {

  def `z=>(z||y)`[Z, Y]: Z => (Z || Y) = { z =>
    Left(z)
  }

  def `y=>(z||y)`[Z, Y]: Y => (Z || Y) = { y =>
    Right(y)
  }

  def foldBoolean[Z]: (Thunk[Z], Thunk[Z]) => Boolean => Z = { (tz, fz) =>
    {
      case true  => tz.eval
      case false => fz.eval
    }
  }

  def `(w&&b)=>(w||w)`[W]: (W && Boolean) => (W || W) = { (w, b) =>
    foldBoolean[W || W](Thunk(Left(w)), Thunk(Right(w)))(b)
  }

  def sum[Z, Y, X]: (Thunk[Y => Z] && Thunk[X => Z]) => (Y || X) => Z = {
    (`y=>z`, `x=>z`) =>
      {
        case Left(y) =>
          `y=>z`.eval(y)
        case Right(x) =>
          `x=>z`.eval(x)
      }
  }

  def `(y||x)=>y`[Y, X]: (Y || X) => Y =
    sum[Y, Y, X](Thunk(y => y), Thunk(_ => ???))

  def `(y||x)=>x`[Y, X]: (Y || X) => X =
    sum[X, Y, X](Thunk(_ => ???), Thunk(x => x))

  def `(y||x)=>b`[Y, X]: (Y || X) => Boolean =
    sum[Boolean, Y, X](Thunk(_ => true), Thunk(_ => false))

  def or[Z, Y, X, W]: (Thunk[X => Z], Thunk[W => Y]) => (X || W) => (Z || Y) = {
    (`x=>z`, `w=>y`) =>
      {
        case Left(x) =>
          Left(`x=>z`.eval(x))
        case Right(w) =>
          Right(`w=>y`.eval(w))
      }
  }

}
