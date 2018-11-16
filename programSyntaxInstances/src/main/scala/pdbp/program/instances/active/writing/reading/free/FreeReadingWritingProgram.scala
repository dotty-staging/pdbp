package pdbp.program.instances.active.writing.reading.free

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

import pdbp.writable.Writable

import pdbp.program.Program
import pdbp.program.reading.Reading
import pdbp.program.writing.Writing

import pdbp.computation.Computation

import pdbp.computation.transformation.ComputationTransformation
import pdbp.computation.transformation.writing.reading.free.FreeReadingWritingTransformation

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.free.freeReadingWritingActiveTypes._

private[pdbp] trait FreeReadingWritingProgram[R, W: Writable]
    extends Computation[FreeReadingWritingActive[R, W]]
    with Program[`=>FRWA`[R, W]]
    with Reading[R, `=>FRWA`[R, W]]
    with Writing[W, `=>FRWA`[R, W]]
    with ComputationTransformation[ReadingWritingActive[R, W],
                                   FreeReadingWritingActive[R, W]]
    with FreeReadingWritingTransformation[R, W, ReadingWritingActive[R, W]]
