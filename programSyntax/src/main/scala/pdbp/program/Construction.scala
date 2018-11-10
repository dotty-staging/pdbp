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

trait Construction[>-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] def product[Z, Y, X]
    : (Z >--> Y && Thunk[Z >--> X]) => Z >--> (Y && X)

  private[pdbp] def and[Z, X, Y, W]
    : (Z >--> X && Thunk[Y >--> W]) => (Z && Y) >--> (X && W) = {
    (`z>-->x`, `y>-->w`) =>
      product(seqCompose(`(z&&y)>-->z`, Thunk(`z>-->x`)),
              Thunk(seqCompose(`(z&&y)>-->y`, `y>-->w`)))
  }

  private[pdbp] def left[Z, X, Y](`z>-->x`: Z >--> X): (Z && Y) >--> (X && Y) =
    and(`z>-->x`, Thunk(`y>-->y`))

  private[pdbp] def right[Z, W, Y](`y>-->w`: Y >--> W): (Z && Y) >--> (Z && W) =
    and(`z>-->z`, Thunk(`y>-->w`))

  def `let`[Z, Y, X](`z>-->y`: Z >--> Y): In[Z, Y, X] =
    new In[Z, Y, X] {
      def `in`(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X =
        seqCompose(product(`z>-->z`, Thunk(`z>-->y`)), Thunk(`(z&&y)>-->x`))
    }

  trait In[Z, Y, X] {
    def `in`(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X
  }

}

object constructionOperators {

  implicit class ConstructionOperators[>-->[- _, + _]: Construction, -Z, +Y](
      `z>-->y`: Z >--> Y) {

    private val implicitConstruction = implicitly[Construction[>-->]]

    import implicitConstruction._

    def &[ZZ <: Z, X](`zz>-->x`: => ZZ >--> X) =
      product(`z>-->y`, Thunk(`zz>-->x`))

    def &&[X, W](`x>-->w`: => X >--> W) =
      and(`z>-->y`, Thunk(`x>-->w`))

  }

}
