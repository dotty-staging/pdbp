package pdbp.writable.instances.toConsoleWriting

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

import pdbp.types.product.productType._

import pdbp.writable.Writable

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

object implicits {

  implicit object toConsoleWritingWritable extends Writable[ToConsoleWriting] {

    override private[pdbp] val start: ToConsoleWriting =
      ToConsoleWriting { _ =>
        ()
      }

    override private[pdbp] val append
      : (ToConsoleWriting && ToConsoleWriting) => ToConsoleWriting = {
      (first, second) =>
        ToConsoleWriting { _ =>
          first.effect(())
          second.effect(())
        }
    }

  }

}
