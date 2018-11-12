# Program Description Based Programming

Refactoring [pdbp.github.io](https://github.com/PDBP/pdbp.github.io)

## Issue

The (commented out) file
"computationTransformations/src/main/scala/pdbp/computation/transformation/delimiting/ControlTransformation.scala"
`programSyntax/src/main/scala/pdbp/computation/transformation/reading/ReadingTransformation.scala` 

 - is ok when building with "val dottyVersion = "0.8.0"
 - is ko when building with "val dottyVersion = "0.11.0-bin-20181111-180c8df-NIGHTLY"

 here is the compiler error

 ```scala
[error] -- Error: /opt/home/git/blog/pdbp/computationTransformations/src/main/scala/pdbp/computation/transformation/delimiting/ControlTransformation.scala:45:27
[error] 45 |  private[pdbp] case class PushProg[C[+ _], STC[+ _], A, -Z, +Y](
[error]    |  ^
[error]    |contravariant type Z occurs in covariant position in type Object with
[error]    |  pdbp.computation.transformation.delimiting.ControlTransformation.Stack[A, Z]
[error]    | with Product{...} of class PushProg
[error] 46 |      prog: Prog[C, STC, A, Z, Y],
[error] 47 |      stack: Stack[A, Y])
[error] 48 |      extends Stack[A, Z]
[error] one error found
[error] (computationTransformations / Compile / compileIncremental) Compilation failed
 ```
  
