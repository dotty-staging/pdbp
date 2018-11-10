package pdbp.program.meaning

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

import pdbp.program.Program

import pdbp.naturalTransformation.binary.`~B~>`

private[pdbp] trait ProgramMeaning[
    `>-P->`[- _, + _]: Program, `>-M->`[- _, + _]: Program] {

  private[pdbp] lazy val binaryTransformation: `>-P->` `~B~>` `>-M->`

  lazy val meaning: `>-P->` `~B~>` `>-M->` = binaryTransformation

}