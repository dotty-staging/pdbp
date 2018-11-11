package pdbp.computation.transformation.writing

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

import pdbp.writable.Writable

import pdbp.naturalTransformation.unary.`~U~>`

import pdbp.program.Program
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation

private[pdbp] object WritingTransformation {

  private[pdbp] type WritingTransformed[W, C[+ _]] = [+Z] => C[W && Z]

}

import WritingTransformation._

private[pdbp] trait WritingTransformation[W: Writable, C[+ _]: Computation]
    extends ComputationTransformation[C, WritingTransformed[W, C]]
    with Writing[W, Kleisli[WritingTransformed[W, C]]] {

  private type WTC = WritingTransformed[W, C]
  private type `=>WTC` = Kleisli[WTC]

  private val implicitComputation = implicitly[Computation[C]]

  import implicitComputation.{bind => bindC, result => resultC}

  private val implicitWritable = implicitly[Writable[W]]

  import implicitWritable._

  override private[pdbp] val transform: C `~U~>` WTC = new {
    override private[pdbp] def apply[Z](cz: C[Z]): WTC[Z] =
      bindC(cz, Thunk({ z =>
        resultC((start, z))
      }))
  }

  override private[pdbp] def bind[Z, Y]
    : (WTC[Z] && Thunk[Z => WTC[Y]]) => WTC[Y] = { (wtcz, `z=>wtcy`) =>
    bindC(wtcz, Thunk({ (leftW, z) =>
      bindC(`z=>wtcy`.eval(z), Thunk({ (rightW, y) =>
        resultC(append(leftW, rightW), y)
      }))
    }))
  }

  private[pdbp] override val `w>-->u`: W `=>WTC` Unit = { w =>
    resultC((w, ()))
  }

}
