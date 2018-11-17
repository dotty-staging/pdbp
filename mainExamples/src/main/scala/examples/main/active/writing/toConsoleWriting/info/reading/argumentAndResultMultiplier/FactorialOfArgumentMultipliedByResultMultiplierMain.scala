package examples.main.active.writing.toConsoleWriting.info.reading.argumentAndResultMultiplier

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

import pdbp.program.compositionOperator._

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.writable.instances.toConsoleWriting.implicits.toConsoleWritingWritable

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.writing.toConsoleWriting.reading.twobigints.implicits.readingWritingProgram

import examples.programs.writing.info.reading.argumentAndResultMultiplier.FactorialOfArgumentMultipliedByResultMultiplier._

import examples.mainPrograms.writing.info.reading.argumentAndResultMultiplier.MainFactorialOfArgumentMultipliedByResultMultiplier

import examples.utils.implicits.infoToToConsoleWriting

object FactorialOfArgumentMultipliedByResultMultiplierMain
    extends MainFactorialOfArgumentMultipliedByResultMultiplier[ToConsoleWriting, `=>RWA`[ArgumentAndResultMultiplier, ToConsoleWriting]]() {

  import readingWritingProgram._

  override val producer = read >--> function {
      (factorialArgument, _) =>
        factorialArgument
    }

  import examples.utils.implicits.convertFactorialOfBigIntReadToToConsoleWriting

  override val consumer = readingWritingProgram.write

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.twobigints.implicits.readingImplicitEffectExecuting.meaning

    import pdbp.program.runners.active.reading.runner.run

    import examples.utils.implicits.argumentAndResultMultiplierEffectfullyReadFromConsole

    run(meaning(mainFactorialMultipliedByResultMultiplier))

  }

}
