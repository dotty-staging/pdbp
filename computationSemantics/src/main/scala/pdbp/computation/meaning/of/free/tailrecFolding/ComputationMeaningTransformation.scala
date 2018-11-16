package pdbp.computation.meaning.of.free.tailrecFolding

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
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning

import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

private[pdbp] trait ComputationMeaningTransformation[
    C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[C,
                                                     M,
                                                     FreeTransformed[C],
                                                     M] {

  private type FTC = FreeTransformed[C]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[FTC, M] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    val implicitComputation = implicitly[Computation[M]]

    import implicitComputation.{result => resultM, bind => bindM}

    implicit object freeComputation
        extends FreeTransformation[C]()
        with ComputationTransformation[C, FTC]()

    object tailrecFoldingComputationMeaning
        extends ComputationMeaning[FTC, M]()
        with ProgramMeaning[Kleisli[FTC], Kleisli[M]]() {

      override private[pdbp] lazy val unaryTransformation: FTC `~U~>` M =
        new {
          @annotation.tailrec
          override private[pdbp] def apply[Z](ftcz: FTC[Z]): M[Z] = ftcz match {
            case Transform(cz) =>
              `c~u~>m`(cz)
            case Result(z) =>
              resultM(z)
            // case Bind(Transform(cy), y2ftcz) =>
            //   bindM(`c~u~>m`(cy), Thunk({ y =>
            //     apply(y2ftcz.eval(y))
            //   }))
            case Bind(Result(y), y2ftcz) =>
              apply(y2ftcz.eval(y))
            case Bind(Bind(ftcx, x2ftcy), y2ftcz) =>
              apply(Bind(ftcx, Thunk({ x =>
                Bind(x2ftcy.eval(x), y2ftcz)
              })))
            case any =>
              sys.error(
                s"Impossible, since, 'apply' eliminates the case for $any")
          }
        }

    }

    tailrecFoldingComputationMeaning

  }

}

