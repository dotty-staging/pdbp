package pdbp.program.reading

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

import pdbp.program.Function
import pdbp.program.Composition

trait Reading[R, >-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] val `u>-->r`: Unit >--> R = `z>-->r`[Unit]

  private[pdbp] def `z>-->r`[Z]: Z >--> R =
    seqCompose(`z>-->u`, Thunk(`u>-->r`))

  def read[Z]: Z >--> R = `z>-->r`

}