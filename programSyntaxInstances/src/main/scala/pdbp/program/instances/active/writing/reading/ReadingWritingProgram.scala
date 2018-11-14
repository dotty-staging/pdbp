package pdbp.program.active.writing.reading

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
import pdbp.computation.transformation.writing.reading.ReadingWritingTransformation

import pdbp.program.instances.types.active.writing.writingActiveTypes._
import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

private[pdbp] trait ReadingWritingProgram[R, W: Writable]
    extends Computation[ReadingWritingActive[R, W]]
    with Program[`=>RWA`[R, W]]
    with Reading[R, `=>RWA`[R, W]]
    with Writing[W, `=>RWA`[R, W]]
    with ComputationTransformation[WritingActive[W], ReadingWritingActive[R, W]]
    with ReadingWritingTransformation[R, W, WritingActive[W]]
