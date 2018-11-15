package examples.main.active.writing.toConsoleWriting.reading.bigint

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

import pdbp.writable.instances.toConsoleWriting.types.ToConsoleWriting

import pdbp.program.instances.types.active.writing.reading.readingWritingActiveTypes._

import pdbp.program.instances.active.writing.toConsoleWriting.reading.bigint.implicits.readingWritingProgram

import examples.mainPrograms.MainFactorial

object FactorialMain
    extends MainFactorial[`=>RWA`[BigInt, ToConsoleWriting]]() {

  override val producer = readingWritingProgram.read

  import examples.utils.implicits.convertFactorialOfBigIntReadToToConsoleWriting

  override val consumer = readingWritingProgram.write

  def main(args: Array[String]): Unit = {

    import pdbp.program.meaning.active.of.writing.toConsoleWriting.reading.bigint.implicits.readingEffectExecuting.meaning

    import pdbp.program.runners.active.reading.runner.run

    import examples.utils.implicits.bigIntEffectfullyReadFromConsole

    run(meaning(mainFactorial))

  }

}
