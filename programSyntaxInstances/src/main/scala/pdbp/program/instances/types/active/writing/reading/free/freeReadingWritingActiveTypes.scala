package pdbp.program.instances.types.active.writing.reading.free

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

import pdbp.computation.transformation.free.FreeTransformation._

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

object freeReadingWritingActiveTypes {

  type FreeReadingWritingActive[R, W] =
    FreeTransformed[ReadingWritingActive[R, W]]

  type `=>FRWA`[R, W] = Kleisli[FreeReadingWritingActive[R, W]]

}
