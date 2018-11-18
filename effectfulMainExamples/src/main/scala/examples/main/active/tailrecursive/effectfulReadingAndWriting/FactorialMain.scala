package examples.main.active.tailrecursive.effectfulReadingAndWriting

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

import pdbp.program.instances.types.active.free.freeActiveTypes._

import pdbp.program.instances.active.free.implicits.freeProgram

import examples.utils.EffectfulUtils

import examples.mainPrograms.MainFactorial

object FactorialMain
    extends EffectfulUtils[`=>FA`]
    with MainFactorial[`=>FA`]() {

  override val producer = effectfulReadBigIntFromConsole

  override val consumer = effectfulWriteFactorialOfBigIntToConsole

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.free.implicits.tailrecFolding.meaning

    import pdbp.program.runners.active.runner.run

    run(meaning(mainFactorial))

  }

}
