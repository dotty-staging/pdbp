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

import pdbp.program.Program
import pdbp.program.Applying

import pdbp.computation.Resulting
import pdbp.computation.Binding

private[pdbp] trait ProgramWithApplying[>-->[- _, + _]]
    extends Program[>-->]
    with Applying[>-->]
    with Resulting[Kleisli[>-->]]
    with Binding[Kleisli[>-->]] {

  private type C = Kleisli[>-->]

  override private[pdbp] def result[Z]: Z => C[Z] =
    `z=>(u>-->z)`

  override private[pdbp] def bind[Z, Y]: (C[Z] && Thunk[Z => C[Y]]) => C[Y] = {
    (cz, `z=>cy`) =>
      seqCompose(cz,
                 Thunk(
                   seqCompose(product(`z>-->u`, Thunk(function(`z=>cy`.eval))),
                              Thunk(apply))))
  }

}
