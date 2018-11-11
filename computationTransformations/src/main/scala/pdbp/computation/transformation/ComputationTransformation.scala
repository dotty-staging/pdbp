package pdbp.computation.transformation

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

import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`
import pdbp.naturalTransformation.binary.`~B~>`

import pdbp.program.Program

import pdbp.computation.Computation

private[pdbp] trait ComputationTransformation[C[+ _]: Computation, T[+ _]]
    extends Computation[T]
    with Program[Kleisli[T]] {

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC}

  private[pdbp] val transform: C `~U~>` T

  override private[pdbp] def result[Z]: Z => T[Z] = { z =>
    transform(resultC(z))
  }

}
