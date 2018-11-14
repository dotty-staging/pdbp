package pdbp.computation.transformation.reading

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

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.reading.Reading

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object ReadingTransformation {

  private[pdbp] type ReadingTransformed[R, C[+ _]] = [+Z] => R `I=>` C[Z]

}

import ReadingTransformation._

private[pdbp] trait ReadingTransformation[R, C[+ _]: Computation]
    extends ComputationTransformation[C, ReadingTransformed[R, C]]
    with Reading[R, Kleisli[ReadingTransformed[R, C]]] {

  private type RTC = ReadingTransformed[R, C]
  private type `=>RTC` = Kleisli[RTC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{result => resultC, bind => bindC}

  override private[pdbp] val transform: C `~U~>` RTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): RTC[Z] = {
      cz
    }
  }

  override private[pdbp] def bind[Z, Y]
    : (RTC[Z] && Thunk[Z => RTC[Y]]) => RTC[Y] = { (rtcz, `z>=rtcy`) =>
    bindC(rtcz, Thunk({ z =>
      `z>=rtcy`.eval(z)
    }))
  }

  override private[pdbp] val `u>-->r`: Unit `=>RTC` R = { _ =>
    resultC(implicitly[R])
  }

}
