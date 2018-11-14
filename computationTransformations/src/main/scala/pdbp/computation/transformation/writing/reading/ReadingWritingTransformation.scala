package pdbp.computation.transformation.writing.reading

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

import pdbp.types.implicitFunctionType._
import pdbp.types.kleisli.binary.kleisliBinaryTypeConstructorType._

import pdbp.writable.Writable

import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.reading.ReadingTransformation
import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.computation.transformation.writing.ComputationWritingTransformation

private[pdbp] trait ReadingWritingTransformation[
    R, 
    W: Writable, 
    C[+ _]: Computation
          : [C[+ _]] => Writing[W, Kleisli[C]]]
    extends ReadingTransformation[R, C]
    with ComputationWritingTransformation[W, C, ReadingTransformed[R, C]]
