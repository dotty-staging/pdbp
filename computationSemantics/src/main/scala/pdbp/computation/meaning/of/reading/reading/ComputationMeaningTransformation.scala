package pdbp.computation.meaning.of.reading.reading

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

import pdbp.types.implicitFunctionType.`I=>`
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

private[pdbp] trait ComputationMeaningTransformation[
    R, C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[C,
                                                     M,
                                                     ReadingTransformed[R, C],
                                                     ReadingTransformed[R, M]] {

  private type RTC = ReadingTransformed[R, C]
  private type RTM = ReadingTransformed[R, M]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[RTC, RTM] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    implicit object readingComputation
        extends ReadingTransformation[R, C]()
        with ComputationTransformation[C, RTC]()

    implicit object readingComputationMeaning
        extends ReadingTransformation[R, M]()
        with ComputationTransformation[M, RTM]()

    object implicitValueReadingComputationMeaning
        extends ComputationMeaning[RTC, RTM]()
        with ProgramMeaning[Kleisli[RTC], Kleisli[RTM]]() {

      override private[pdbp] lazy val unaryTransformation: RTC `~U~>` RTM =
        new {
          override private[pdbp] def apply[Z](rtcz: RTC[Z]): RTM[Z] =
            `c~u~>m`(rtcz(implicitly[R]))
        }

    }

    implicitValueReadingComputationMeaning

  }

}
