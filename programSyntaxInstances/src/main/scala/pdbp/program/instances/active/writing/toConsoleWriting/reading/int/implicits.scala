// package pdbp.program.instances.active.writing.toConsoleWriting.reading.int

// //       _______         __    __        _______
// //      / ___  /\       / /\  / /\      / ___  /\
// //     / /__/ / / _____/ / / / /_/__   / /__/ / /
// //    / _____/ / / ___  / / / ___  /\ /____  / /
// //   / /\____\/ / /__/ / / / /__/ / / \___/ / /
// //  /_/ /      /______/ / /______/ /     /_/ /
// //  \_\/       \______\/  \______\/      \_\/
// //                                           v1.0
// //  Program Description Based Programming Library
// //  author        Luc Duponcheel        2017-2018

// import pdbp.types.active.activeTypes._
// import pdbp.types.active.writing.writingActiveTypes._
// import pdbp.types.active.writing.reading.readingWritingActiveTypes._
// import pdbp.types.toConsole.ToConsole

// import pdbp.writable.toConsole.implicits.toConsoleWritable

// import pdbp.program.Program

// import pdbp.program.reading.Reading

// import pdbp.program.writing.Writing

// import pdbp.program.active.writing.reading.ReadingWritingProgram

// import pdbp.program.active.writing.toConsole.implicits.writingProgram

// import pdbp.computation.Computation

// import pdbp.computation.transformation.ComputationTransformation
// import pdbp.computation.transformation.reading.ReadingTransformation

// import pdbp.computation.transformation.writing.reading.ReadingWritingTransformation

// import pdbp.computation.transformation.writing.ComputationWritingTransformation

// object implicits {

//   implicit object readingWritingProgram
//       extends ReadingWritingProgram[BigInt, ToConsole]()
//       with Computation[ReadingWritingActive[BigInt, ToConsole]]()
//       with Program[`=>RWA`[BigInt, ToConsole]]()
//       with Reading[BigInt, `=>RWA`[BigInt, ToConsole]]()
//       with Writing[ToConsole, `=>RWA`[BigInt, ToConsole]]()
//       with ComputationTransformation[WritingActive[ToConsole],
//                                      ReadingWritingActive[BigInt, ToConsole]]()
//       with ReadingTransformation[BigInt, WritingActive[ToConsole]]()
//       with ComputationWritingTransformation[
//         ToConsole, 
//         WritingActive[ToConsole], 
//         ReadingWritingActive[BigInt, ToConsole]]()       
//       with ReadingWritingTransformation[BigInt,
//                                         ToConsole,
//                                         WritingActive[ToConsole]]()
// }
