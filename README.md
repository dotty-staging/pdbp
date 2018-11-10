# Program Description Based Programming

## description of the project

The project is a refactored version of the project [pdbp.github.io](https://github.com/PDBP/pdbp.github.io).

Two things are changed

  - *at the programming level:* the usage of lazy parameters using an, agreed, somewhat *quick-and-dirty* implementation using
    - [implicitFunctionType.scala](https://github.com/PDBP/pdbp/blob/master/types/src/main/scala/pdbp/types/implicitFunctionType.scala)
    - [implicitUnit.scala](https://github.com/PDBP/pdbp/blob/master/types/src/main/scala/pdbp/types/implicitUnit.scala)
    - [Thunk.scala](https://github.com/PDBP/pdbp/blob/master/types/src/main/scala/pdbp/types/Thunk.scala)

  - *at the project level:* modularization

Note that I already created an issue [5419](https://github.com/lampepfl/dotty/issues/5419) because putting the lazyness related code above in one file turned out to be problematic. This is a minor issue since I want to put the lazyness related code in many file anyway.

The scripts `clean.sh` and `publishLocal.sh` are used to, well, *cleanup generated stuff* and *publish modular artifacts locally*.
Note that they use a `#! /bin/bash` (you may need to change it (I have no idea if they work on MacOS)).

Do not forget to clean up your `~/.ivy2/local` folder if you run `publishLocal.sh` more than once (for example, if you experiment with the code).
I simply do `\rm -r ~/.ivy2/local ; mkdir ~/.ivy2/local` since I do not have published any other important local artifacts.

## description of the issue

Note that, although I have written more code than needed for the issue, you only need the code of

  - `types`
  - `utils`
  - `naturalTransformation`
  - `programSyntax`
  - `programInstances`

to reproduce the issue.

The issue happens when compiling `freeProgram` in [implicts.scala](https://github.com/PDBP/pdbp/blob/master/programInstances/src/main/scala/pdbp/program/instances/active/free/implicits.scala).

FYI: you may wish to comment the code, change `clean.sh` and `publishLocal.sh`, and run [SimpleFactorialMain.scala](https://github.com/PDBP/pdbp/blob/master/mainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/SimpleFactorialMain.scala) and
[FactorialMain.scala](https://github.com/PDBP/pdbp/blob/master/mainExamples/src/main/scala/examples/main/active/effectfulReadingAndWriting/FactorialMain.scala).
They should work.


Here is the error (agreed, you can reproduce it yourself, but it is provided for completeness)

```scala
exception while typing final module class freeProgram() extends Object() with 
  pdbp.computation.Computation[
    pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive
  ]
 with 
  pdbp.program.Program[
    pdbp.program.instances.types.active.free.freeActiveTypes.=$u003EFA
  ]
 with pdbp.computation.transformation.ComputationTransformation[
  pdbp.program.instances.types.active.activeTypes.Active
, pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive]()(
  pdbp.program.instances.active.implicits.program
) with pdbp.computation.transformation.free.FreeTransformation[
  pdbp.program.instances.types.active.activeTypes.Active
]()(pdbp.program.instances.active.implicits.program) { 
  override def lift0[Z]: 
    Z => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Z]
    
   = super[Computation].lift0[Z]
  override def lift1[Z, Y]: 
    (Z => Y) => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Z]
     => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Y]
    
   = super[Computation].lift1[Z, Y]
  override def lift2[Z, Y, X]: 
    (((Z, Y)) => X) => ((
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Z]
    , 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Y]
    )) => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][X]
    
   = super[Computation].lift2[Z, Y, X]
  override def lift3[Z, Y, X, W]: 
    ((((Z, Y), X)) => W) => (((
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Z]
    , 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Y]
    ), 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][X]
    )) => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][W]
    
   = super[Computation].lift3[Z, Y, X, W]
  override def result[Z]: 
    Z => 
      pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
        pdbp.program.instances.types.active.activeTypes.Active
      ][Z]
    
   = super[FreeTransformation].result[Z]
} of class class dotty.tools.dotc.ast.Trees$TypeDef # 3973
exception while typing @scala.annotation.internal.SourceFile(
  
    "/opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/active/free/implicits.scala"
  
) final module class implicits() extends Object() { 
  final implicit lazy module val freeProgram: 
    pdbp.program.instances.active.free.implicits.freeProgram
   = new pdbp.program.instances.active.free.implicits.freeProgram()
  final module class freeProgram() extends Object() with 
    pdbp.computation.Computation[
      pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive
    ]
   with 
    pdbp.program.Program[
      pdbp.program.instances.types.active.free.freeActiveTypes.=$u003EFA
    ]
   with pdbp.computation.transformation.ComputationTransformation[
    pdbp.program.instances.types.active.activeTypes.Active
  , pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive]()(
    pdbp.program.instances.active.implicits.program
  ) with pdbp.computation.transformation.free.FreeTransformation[
    pdbp.program.instances.types.active.activeTypes.Active
  ]()(pdbp.program.instances.active.implicits.program) { 
    override def lift0[Z]: 
      Z => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Z]
      
     = super[Computation].lift0[Z]
    override def lift1[Z, Y]: 
      (Z => Y) => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Z]
       => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Y]
      
     = super[Computation].lift1[Z, Y]
    override def lift2[Z, Y, X]: 
      (((Z, Y)) => X) => ((
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Z]
      , 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Y]
      )) => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][X]
      
     = super[Computation].lift2[Z, Y, X]
    override def lift3[Z, Y, X, W]: 
      ((((Z, Y), X)) => W) => (((
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Z]
      , 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Y]
      ), 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][X]
      )) => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][W]
      
     = super[Computation].lift3[Z, Y, X, W]
    override def result[Z]: 
      Z => 
        pdbp.computation.transformation.free.FreeTransformation.FreeTransformed[
          pdbp.program.instances.types.active.activeTypes.Active
        ][Z]
      
     = super[FreeTransformation].result[Z]
  }
} of class class dotty.tools.dotc.ast.Trees$TypeDef # 3975
exception while typing package pdbp.program.instances.active.free {
  final lazy module val implicits: pdbp.program.instances.active.free.implicits
     = 
  new pdbp.program.instances.active.free.implicits()
  @scala.annotation.internal.SourceFile(
    
      "/opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/active/free/implicits.scala"
    
  ) final module class implicits() extends Object() { 
    final implicit lazy module val freeProgram: 
      pdbp.program.instances.active.free.implicits.freeProgram
     = new pdbp.program.instances.active.free.implicits.freeProgram()
    final module class freeProgram() extends Object() with 
      pdbp.computation.Computation[
        pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive
      ]
     with 
      pdbp.program.Program[
        pdbp.program.instances.types.active.free.freeActiveTypes.=$u003EFA
      ]
     with pdbp.computation.transformation.ComputationTransformation[
      pdbp.program.instances.types.active.activeTypes.Active
    , pdbp.program.instances.types.active.free.freeActiveTypes.FreeActive]()(
      pdbp.program.instances.active.implicits.program
    ) with pdbp.computation.transformation.free.FreeTransformation[
      pdbp.program.instances.types.active.activeTypes.Active
    ]()(pdbp.program.instances.active.implicits.program) { 
      override def lift0[Z]: 
        Z => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Z]
        
       = super[Computation].lift0[Z]
      override def lift1[Z, Y]: 
        (Z => Y) => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Z]
         => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Y]
        
       = super[Computation].lift1[Z, Y]
      override def lift2[Z, Y, X]: 
        (((Z, Y)) => X) => ((
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Z]
        , 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Y]
        )) => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][X]
        
       = super[Computation].lift2[Z, Y, X]
      override def lift3[Z, Y, X, W]: 
        ((((Z, Y), X)) => W) => (((
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Z]
        , 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Y]
        ), 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][X]
        )) => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][W]
        
       = super[Computation].lift3[Z, Y, X, W]
      override def result[Z]: 
        Z => 
          pdbp.computation.transformation.free.FreeTransformation.
            FreeTransformed
          [pdbp.program.instances.types.active.activeTypes.Active][Z]
        
       = super[FreeTransformation].result[Z]
    }
  }
} of class class dotty.tools.dotc.ast.Trees$PackageDef # 3976
[info] exception occurred while compiling /opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/active/free/implicits.scala, /opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/active/implicits.scala, /opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/types/active/activeTypes.scala, /opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/types/active/free/freeActiveTypes.scala, /opt/home/git/blog/pdbp/programInstances/src/main/scala/pdbp/program/instances/utils/active/functionUtils.scala
[error] ## Exception when compiling 5 sources to /opt/home/git/blog/pdbp/programInstances/target/scala-0.10/classes
[error] assertion failed
[error] dotty.DottyPredef$.assertFail(DottyPredef.scala:37)
[error] dotty.tools.dotc.core.Types$MethodType.<init>(Types.scala:2980)
[error] dotty.tools.dotc.core.Types$CachedMethodType.<init>(Types.scala:2997)
[error] dotty.tools.dotc.core.Types$MethodTypeCompanion.apply(Types.scala:3065)
[error] dotty.tools.dotc.core.Types$MethodTypeCompanion.apply(Types.scala:3064)
[error] dotty.tools.dotc.core.Types$LambdaTypeCompanion.apply(Types.scala:3009)
[error] dotty.tools.dotc.core.TypeErasure.eraseInfo(TypeErasure.scala:479)
[error] dotty.tools.dotc.core.TypeErasure$.transformInfo(TypeErasure.scala:187)
[error] dotty.tools.dotc.transform.Erasure.transform(Erasure.scala:75)
[error] dotty.tools.dotc.core.Denotations$SingleDenotation.current(Denotations.scala:910)
[error] dotty.tools.dotc.core.Symbols$Symbol.recomputeDenot(Symbols.scala:480)
[error] dotty.tools.dotc.core.Symbols$Symbol.computeDenot(Symbols.scala:475)
[error] dotty.tools.dotc.core.Symbols$Symbol.denot(Symbols.scala:469)
[error] dotty.tools.dotc.core.Symbols$.toDenot(Symbols.scala:775)
[error] dotty.tools.dotc.transform.Bridges.add(Bridges.scala:120)
[error] dotty.tools.dotc.transform.Erasure$Typer.typedStats(Erasure.scala:704)
[error] dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:1593)
[error] dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:1872)
[error] dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:1935)
[error] dotty.tools.dotc.typer.ReTyper.typedUnadapted(ReTyper.scala:112)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1967)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1979)
[error] dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:1998)
[error] dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:2039)
[error] dotty.tools.dotc.transform.Erasure$Typer.typedStats(Erasure.scala:706)
[error] dotty.tools.dotc.typer.Typer.typedClassDef(Typer.scala:1593)
[error] dotty.tools.dotc.typer.Typer.typedNamed$1(Typer.scala:1872)
[error] dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:1935)
[error] dotty.tools.dotc.typer.ReTyper.typedUnadapted(ReTyper.scala:112)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1967)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1979)
[error] dotty.tools.dotc.typer.Typer.traverse$1(Typer.scala:1998)
[error] dotty.tools.dotc.typer.Typer.typedStats(Typer.scala:2039)
[error] dotty.tools.dotc.transform.Erasure$Typer.typedStats(Erasure.scala:706)
[error] dotty.tools.dotc.typer.Typer.typedPackageDef(Typer.scala:1707)
[error] dotty.tools.dotc.typer.Typer.typedUnnamed$1(Typer.scala:1914)
[error] dotty.tools.dotc.typer.Typer.typedUnadapted(Typer.scala:1936)
[error] dotty.tools.dotc.typer.ReTyper.typedUnadapted(ReTyper.scala:112)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1967)
[error] dotty.tools.dotc.typer.Typer.typed(Typer.scala:1979)
[error] dotty.tools.dotc.typer.Typer.typedExpr(Typer.scala:2050)
[error] dotty.tools.dotc.transform.Erasure.run(Erasure.scala:99)
[error] dotty.tools.dotc.core.Phases$Phase.runOn$$anonfun$1(Phases.scala:299)
[error] scala.collection.immutable.List.map(List.scala:282)
[error] dotty.tools.dotc.core.Phases$Phase.runOn(Phases.scala:301)
[error] dotty.tools.dotc.Run.runPhases$4$$anonfun$4(Run.scala:175)
[error] scala.compat.java8.JProcedure1.apply(JProcedure1.java:18)
[error] scala.compat.java8.JProcedure1.apply(JProcedure1.java:10)
[error] scala.collection.IndexedSeqOptimized.foreach(IndexedSeqOptimized.scala:32)
[error] scala.collection.IndexedSeqOptimized.foreach$(IndexedSeqOptimized.scala:29)
[error] scala.collection.mutable.ArrayOps$ofRef.foreach(ArrayOps.scala:194)
[error] dotty.tools.dotc.Run.runPhases$5(Run.scala:187)
[error] dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:195)
[error] scala.compat.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:12)
[error] dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:90)
[error] dotty.tools.dotc.Run.compileUnits(Run.scala:202)
[error] dotty.tools.dotc.Run.compileSources(Run.scala:137)
[error] dotty.tools.dotc.Run.compile(Run.scala:121)
[error] dotty.tools.dotc.Driver.doCompile(Driver.scala:31)
[error] dotty.tools.dotc.Driver.process(Driver.scala:134)
[error] xsbt.CachedCompilerImpl.run(CompilerInterface.scala:61)
[error] xsbt.CachedCompilerImpl.run(CompilerInterface.scala:51)
[error] xsbt.CompilerInterface.run(CompilerInterface.scala:35)
[error] sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
[error] sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
[error] sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
[error] java.lang.reflect.Method.invoke(Method.java:498)
[error] sbt.internal.inc.AnalyzingCompiler.call(AnalyzingCompiler.scala:237)
[error] sbt.internal.inc.AnalyzingCompiler.compile(AnalyzingCompiler.scala:111)
[error] sbt.internal.inc.AnalyzingCompiler.compile(AnalyzingCompiler.scala:90)
[error] sbt.internal.inc.MixedAnalyzingCompiler.$anonfun$compile$3(MixedAnalyzingCompiler.scala:82)
[error] scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:12)
[error] sbt.internal.inc.MixedAnalyzingCompiler.timed(MixedAnalyzingCompiler.scala:133)
[error] sbt.internal.inc.MixedAnalyzingCompiler.compileScala$1(MixedAnalyzingCompiler.scala:73)
[error] sbt.internal.inc.MixedAnalyzingCompiler.compile(MixedAnalyzingCompiler.scala:116)
[error] sbt.internal.inc.IncrementalCompilerImpl.$anonfun$compileInternal$1(IncrementalCompilerImpl.scala:307)
[error] sbt.internal.inc.IncrementalCompilerImpl.$anonfun$compileInternal$1$adapted(IncrementalCompilerImpl.scala:307)
[error] sbt.internal.inc.Incremental$.doCompile(Incremental.scala:106)
[error] sbt.internal.inc.Incremental$.$anonfun$compile$4(Incremental.scala:87)
[error] sbt.internal.inc.IncrementalCommon.recompileClasses(IncrementalCommon.scala:116)
[error] sbt.internal.inc.IncrementalCommon.cycle(IncrementalCommon.scala:63)
[error] sbt.internal.inc.Incremental$.$anonfun$compile$3(Incremental.scala:89)
[error] sbt.internal.inc.Incremental$.manageClassfiles(Incremental.scala:134)
[error] sbt.internal.inc.Incremental$.compile(Incremental.scala:80)
[error] sbt.internal.inc.IncrementalCompile$.apply(Compile.scala:67)
[error] sbt.internal.inc.IncrementalCompilerImpl.compileInternal(IncrementalCompilerImpl.scala:311)
[error] sbt.internal.inc.IncrementalCompilerImpl.$anonfun$compileIncrementally$1(IncrementalCompilerImpl.scala:269)
[error] sbt.internal.inc.IncrementalCompilerImpl.handleCompilationError(IncrementalCompilerImpl.scala:159)
[error] sbt.internal.inc.IncrementalCompilerImpl.compileIncrementally(IncrementalCompilerImpl.scala:238)
[error] sbt.internal.inc.IncrementalCompilerImpl.compile(IncrementalCompilerImpl.scala:69)
[error] sbt.Defaults$.compileIncrementalTaskImpl(Defaults.scala:1544)
[error] sbt.Defaults$.$anonfun$compileIncrementalTask$1(Defaults.scala:1518)
[error] scala.Function1.$anonfun$compose$1(Function1.scala:44)
[error] sbt.internal.util.$tilde$greater.$anonfun$$u2219$1(TypeFunctions.scala:40)
[error] sbt.std.Transform$$anon$4.work(System.scala:67)
[error] sbt.Execute.$anonfun$submit$2(Execute.scala:269)
[error] sbt.internal.util.ErrorHandling$.wideConvert(ErrorHandling.scala:16)
[error] sbt.Execute.work(Execute.scala:278)
[error] sbt.Execute.$anonfun$submit$1(Execute.scala:269)
[error] sbt.ConcurrentRestrictions$$anon$4.$anonfun$submitValid$1(ConcurrentRestrictions.scala:178)
[error] sbt.CompletionService$$anon$2.call(CompletionService.scala:37)
[error] java.util.concurrent.FutureTask.run(FutureTask.java:266)
[error] java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
[error] java.util.concurrent.FutureTask.run(FutureTask.java:266)
[error] java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
[error] java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
[error] java.lang.Thread.run(Thread.java:748)
[error] 
```