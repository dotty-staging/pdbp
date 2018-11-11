package pdbp.computation.transformation.free

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
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object FreeTransformation {

  sealed trait Free[C[+ _], +Z]

  final case class Transform[C[+ _], +Z](cz: C[Z]) extends Free[C, Z]
  final case class Result[C[+ _], +Z](z: Z) extends Free[C, Z]
  final case class Bind[C[+ _], -Z, ZZ <: Z, +Y](
      fczz: Free[C, ZZ],
      `z=>fcy`: Thunk[Z => FreeTransformed[C][Y]])
      extends Free[C, Y]

  private[pdbp] type FreeTransformed[C[+ _]] = [+Z] => Free[C, Z]

}

import FreeTransformation._

private[pdbp] trait FreeTransformation[C[+ _]: Computation]
    extends ComputationTransformation[C, FreeTransformed[C]] {

  private type FTC = FreeTransformed[C]

  override private[pdbp] val transform: C `~U~>` FTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): FTC[Z] =
      Transform(cz)
  }

  override private[pdbp] def result[Z]: Z => FTC[Z] = { z =>
    Result(z)
  }

  override private[pdbp] def bind[Z, Y]
    : (FTC[Z] && Thunk[Z => FTC[Y]]) => FTC[Y] = { (ftcz, `z=>ftcy`) =>
    Bind(ftcz, `z=>ftcy`)
  }

}
