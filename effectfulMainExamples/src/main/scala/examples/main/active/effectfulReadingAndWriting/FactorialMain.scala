package examples.main.active.effectfulReadingAndWriting

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

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.active.implicits.program

import examples.mainPrograms.MainFactorial

import examples.utils.EffectfulUtils

object FactorialMain
    extends EffectfulUtils[`=>A`]()
    with MainFactorial[`=>A`]() {

  override val producer = effectfulReadBigIntFromConsole

  override val consumer = effectfulWriteFactorialOfBigIntToConsole

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.active.implicits.identity.meaning

    import pdbp.program.runners.active.runner.run

    run(meaning(mainFactorial))

  }

}
