package pdbp.program.state

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

trait State[S, >-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] val `u>-->s`: Unit >--> S

  private[pdbp] val `s>-->u`: S >--> Unit

  def readState[Z]: Z >--> S =
    seqCompose(`z>-->u`, Thunk(`u>-->s`))

  def writeState[Z]: Z >--> Unit =
    seqCompose(readState, Thunk(`s>-->u`))

  def readWrittenState[Z]: Z >--> S =
    seqCompose(writeState, Thunk(readState))

  def modifyStateWith[Z]: (S => S) => (Z >--> Unit) = { `s=>s` =>
    seqCompose(readState,
               Thunk(seqCompose(function(`s=>s`), Thunk(writeState))))
  }

  def readStateModifiedWith[Z]: (S => S) => (Z >--> S) = { `s=>s` =>
    seqCompose(modifyStateWith[Z](`s=>s`), Thunk(readState))
  }

}
