package pdbp.program.instances.types.active.writing.reading

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

import pdbp.computation.transformation.reading.ReadingTransformation._

import pdbp.program.instances.types.active.writing.writingActiveTypes._

object readingWritingActiveTypes {

  type ReadingWritingActive[R, W] = ReadingTransformed[R, WritingActive[W]]

  type `=>RWA`[R, W] = Kleisli[ReadingWritingActive[R, W]]

}