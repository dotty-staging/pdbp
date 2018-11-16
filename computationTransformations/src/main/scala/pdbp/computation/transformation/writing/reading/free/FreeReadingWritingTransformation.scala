package pdbp.computation.transformation.writing.reading.free

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

import pdbp.writable.Writable

import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.free.FreeTransformation
import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.computation.transformation.reading.ComputationReadingTransformation
import pdbp.computation.transformation.writing.ComputationWritingTransformation

private[pdbp] trait FreeReadingWritingTransformation[
    R, W: Writable, 
    C[+ _]: Computation
          : [C[+ _]] => Reading[R, Kleisli[C]]
          : [C[+ _]] => Writing[W, Kleisli[C]]]
    extends FreeTransformation[C]
    with ComputationReadingTransformation[R, C, FreeTransformed[C]]
    with ComputationWritingTransformation[W, C, FreeTransformed[C]]

