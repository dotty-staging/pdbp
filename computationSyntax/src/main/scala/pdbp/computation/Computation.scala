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
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.utils.sumUtils._

import pdbp.program.Program
import pdbp.program.Applying

import pdbp.lifting.Lifting

private[pdbp] trait Computation[C[+ _]]
    extends Resulting[C]
    with Binding[C]
    with Lifting[C]
    with Program[Kleisli[C]]
    with Applying[Kleisli[C]] {

  override private[pdbp] def lift0[Z]: Z => C[Z] =
    result

  override private[pdbp] def lift1[Z, Y]: (Z => Y) => C[Z] => C[Y] = `z=>y` => {
    case cz =>
      bind(cz, Thunk(z => result(`z=>y`(z))))
  }

  override private[pdbp] def lift2[Z, Y, X]
    : ((Z && Y) => X) => (C[Z] && C[Y]) => C[X] = `(z&&y)=>x` => {
    case (cz, cy) =>
      bind(cz, Thunk(z => bind(cy, Thunk(y => result(`(z&&y)=>x`(z, y))))))
  }

  override private[pdbp] def lift3[Z, Y, X, W]
    : ((Z && Y && X) => W) => (C[Z] && C[Y] && C[X]) => C[W] =
    `(z&&y&&x)=>w` => {
      case ((cz, cy), cx) =>
        bind(
          cz,
          Thunk(
            z =>
              bind(cy,
                   Thunk(y =>
                     bind(cx, Thunk(x => result(`(z&&y&&x)=>w`((z, y), x))))))))
    }

  // and so on ...

  private type `=>C` = Kleisli[C]

  override def function[Z, Y]: (Z => Y) => Z `=>C` Y = { `z=>y` => z =>
    result(`z=>y`(z))
  }

  override def seqCompose[Z, Y, X]
    : (Z `=>C` Y && Thunk[Y `=>C` X]) => Z `=>C` X = {
    (`z=>cy`, `y=>cx`) => z =>
      bind(`z=>cy`(z), `y=>cx`)
  }

  override def product[Z, Y, X]
    : (Z `=>C` Y && Thunk[Z `=>C` X]) => Z `=>C` (Y && X) = {
    (`z=>cy`, `z=>cx`) => z =>
      bind(`z=>cy`(z),
           Thunk(y => bind(`z=>cx`.eval(z), Thunk(x => result(y, x)))))
  }

  override def sum[Z, Y, X]
    : (Thunk[Y `=>C` Z] && Thunk[X `=>C` Z]) => (Y || X) `=>C` Z = {
    (`y=>cz`, `x=>cz`) =>
      pdbp.utils.sumUtils.sum(`y=>cz`, `x=>cz`)
  }

  override private[pdbp] def apply[Z, Y]: (Z && (Z `=>C` Y)) `=>C` Y = {
    (z, `z=>cy`) =>
      `z=>cy`(z)
  }

  // useful

  private[pdbp] def flatten[Z]: C[C[Z]] => C[Z] = { ccz =>
    bind(ccz, Thunk(identity[C[Z]]))
  }

  private[pdbp] def flatSeqCompose[Z, Y, X](
      `z=>cy`: Z `=>C` Y,
      `y=>ccx`: Thunk[Y `=>C` C[X]]): Z `=>C` X =
    seqCompose(
      seqCompose(
        `z=>cy`,
        `y=>ccx`
      ),
      Thunk(identity[C[X]])
    )

}
