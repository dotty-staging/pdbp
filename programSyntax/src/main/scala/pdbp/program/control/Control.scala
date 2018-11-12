package pdbp.program.control

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

import pdbp.types.prompt.Prompt

import pdbp.program.Function
import pdbp.program.Composition

trait Control[A, >-->[- _, + _]] {
  this: Function[>-->] & Composition[>-->] =>

  private[pdbp] def newPrompt[Y]: Unit >--> Prompt[A, Y]

  def reset[Z, Y]: (Prompt[A, Y] => (Z >--> Y)) => (Z >--> Y)

  def shift[Z, Y, X]: ((X >--> Y) => (Z >--> Y)) => (Prompt[A, Y] => (Z >--> X))

  def abort[Z, Y, X]: (Z >--> Y) => (Prompt[A, Y] => (Z >--> X))

  def stop[Y, X]: Prompt[A, Y] => (Y >--> X)

  def control[Z, Y, X]
    : ((X >--> Y) => (Z >--> Y)) => (Prompt[A, Y] => (Z >--> X))

  def shift0[Z, Y, X]
    : ((X >--> Y) => (Z >--> Y)) => (Prompt[A, Y] => (Z >--> X))

  def control0[Z, Y, X]
    : ((X >--> Y) => (Z >--> Y)) => (Prompt[A, Y] => (Z >--> X))

}
