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
import pdbp.types.sum.sumType._

import pdbp.utils.productUtils._
import pdbp.utils.sumUtils._

trait Condition[>-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] & Construction[>-->] =>

  private[pdbp] def sum[Z, Y, X]
    : (Thunk[Y >--> Z] && Thunk[X >--> Z]) => (Y || X) >--> Z

  private[pdbp] def or[Z, X, Y, W]
    : (Thunk[X >--> Z], Thunk[W >--> Y]) => (X || W) >--> (Z || Y) = {
    (`x>-->z`, `w>-->y`) =>
      sum(Thunk(seqCompose(`x>-->z`.eval, Thunk(`z>-->(z||y)`))),
          Thunk(seqCompose(`w>-->y`.eval, Thunk(`y>-->(z||y)`))))
  }

  def `if`[W, Z](`w>-->b`: W >--> Boolean): Apply[W, Z] =
    new Apply[W, Z] {
      override def apply(`w>-t->z`: => W >--> Z): Else[W, Z] =
        new Else[W, Z] {
          override def `else`(`w>-f->z`: => W >--> Z): W >--> Z =
            seqCompose(`let`(`w>-->b`) `in` `(w&&b)>-->(w||w)`,
                       Thunk(sum(Thunk(`w>-t->z`), Thunk(`w>-f->z`))))
        }
    }

  trait Apply[W, Z] {
    def apply(`w>-t->z`: => W >--> Z): Else[W, Z]
  }

  trait Else[W, Z] {
    def `else`(`w>-f->z`: => W >--> Z): W >--> Z
  }

}
