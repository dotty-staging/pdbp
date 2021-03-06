package pdbp.writable.instances.toConsoleWriting.utils

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

import pdbp.utils.effectfulFunctionUtils._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

object toConsoleWritingUtils {

  def toToConsoleWritingWithMessageLine[Z](
      message: String): Z => ToConsoleWriting = { z =>
    ToConsoleWriting({ _ =>
      effectfulWriteToConsoleWithMessageLineFunction(message)(z)
    })
  }

}
