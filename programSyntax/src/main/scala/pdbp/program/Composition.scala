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

trait Composition[>-->[- _, + _]] {

  private[pdbp] def seqCompose[Z, Y, X]
    : (Z >--> Y && Thunk[Y >--> X]) => Z >--> X

}

object compositionOperator {

  implicit class CompositionOperator[>-->[- _, + _]: Composition, -Z, +Y](
      `z>-->y`: Z >--> Y) {

    private val implicitComposition = implicitly[Composition[>-->]]

    import implicitComposition._

    def >-->[X](`y>-->x`: => Y >--> X) =
      seqCompose(`z>-->y`, Thunk(`y>-->x`))

  }

}
