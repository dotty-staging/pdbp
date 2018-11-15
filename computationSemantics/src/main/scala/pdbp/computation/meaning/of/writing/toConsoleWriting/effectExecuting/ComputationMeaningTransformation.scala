package pdbp.computation.meaning.of.writing.toConsoleWriting.effectExecuting

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

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.WritingTransformation
import pdbp.computation.transformation.writing.WritingTransformation._

import pdbp.program.meaning.ProgramMeaning

import pdbp.computation.meaning.ComputationMeaning
import pdbp.computation.meaning.transformation.ImplicitComputationMeaningTransformation

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

private[pdbp] trait ComputationMeaningTransformation[
    C[+ _]: Computation, M[+ _]: Computation]
    extends ImplicitComputationMeaningTransformation[
      C,
      M,
      WritingTransformed[ToConsoleWriting, C],
      M] {

  private type WTC = WritingTransformed[ToConsoleWriting, C]

  override def apply(implicit computationMeaning: ComputationMeaning[C, M])
    : ComputationMeaning[WTC, M] = {

    import computationMeaning.{unaryTransformation => `c~u~>m`}

    val implicitComputation = implicitly[Computation[C]]

    import implicitComputation.{result => resultC, bind => bindC}

    implicit object writingComputation
        extends WritingTransformation[ToConsoleWriting, C]()
        with ComputationTransformation[C, WTC]()
        with Writing[ToConsoleWriting, Kleisli[WTC]]()

    object toConsoleWritingEffectExecutingComputationMeaning
        extends ComputationMeaning[WTC, M]()
        with ProgramMeaning[Kleisli[WTC], Kleisli[M]]() {

      override private[pdbp] lazy val unaryTransformation: WTC `~U~>` M =
        new {
          override private[pdbp] def apply[Z](wtcz: WTC[Z]): M[Z] =
            `c~u~>m`(bindC(wtcz, Thunk({
              case (ToConsoleWriting(effect), z) =>
                effect(())
                resultC(z)
            })))
        }

    }

    toConsoleWritingEffectExecutingComputationMeaning

  }

}
