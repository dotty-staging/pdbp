/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

val pdbp = "pdbp"
val pdbpVersion = "0.0.1"
// val dottyVersion = "0.8.0"
// val dottyVersion = "0.11.0-bin-20181111-180c8df-NIGHTLY"
val dottyVersion = "0.11.0-bin-20181114-7a86d5b-NIGHTLY"

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val commonSettings = Seq(
    organization := pdbp,
    version := pdbpVersion,
    scalaVersion := dottyVersion
)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val types = project
  .in(file("types"))
  .settings(
    commonSettings,
    name := "types"
  ) 

lazy val utils = project
  .in(file("utils"))
  .settings(
    commonSettings,
    name := "utils"
  ).dependsOn(types)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val kleisli = project
  .in(file("kleisli"))
  .settings(
    commonSettings,
    name := "kleisli"
  ) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val naturalTransformation = project
  .in(file("naturalTransformation"))
  .settings(
    commonSettings,
    name := "naturalTransformation"
  ).dependsOn(kleisli) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// syntax

lazy val liftingSyntax = project
  .in(file("liftingSyntax"))
  .settings(
    commonSettings,
    name := "liftingSyntax"
  ).dependsOn(types, utils)   

lazy val writableSyntax = project
  .in(file("writableSyntax"))
  .settings(
    commonSettings,
    name := "writableSyntax"
  ).dependsOn(types, liftingSyntax)    

lazy val programSyntax = project
  .in(file("programSyntax"))
  .settings(
    commonSettings,
    name := "programSyntax"
  ).dependsOn(types, utils, writableSyntax) 

lazy val computationSyntax = project
  .in(file("computationSyntax"))
  .settings(
    commonSettings,
    name := "computationSyntax"
  ).dependsOn(types, utils, kleisli, liftingSyntax, programSyntax)  

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val computationTransformations = project
  .in(file("computationTransformations"))
  .settings(
    commonSettings,
    name := "computationTransformations"
  ).dependsOn(types, utils, naturalTransformation, programSyntax, computationSyntax) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// semantics

lazy val programSemantics = project
  .in(file("programSemantics"))
  .settings(
    commonSettings,
    name := "programSemantics"
  ).dependsOn(naturalTransformation, programSyntax)    

 lazy val computationSemantics = project
  .in(file("computationSemantics"))
  .settings(
    commonSettings,
    name := "computationSemantics"
  ).dependsOn(naturalTransformation, computationSyntax, computationTransformations, programSemantics, writableSyntaxInstances) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// syntax instances  

lazy val writableSyntaxInstances = project
  .in(file("writableSyntaxInstances"))
  .settings(
    commonSettings,
    name := "writableSyntaxInstances"
  ).dependsOn(types, writableSyntax)

lazy val programSyntaxInstances = project
  .in(file("programSyntaxInstances"))
  .settings(
    commonSettings,
    name := "programSyntaxInstances"
  ).dependsOn(programSyntax, computationSyntax, computationTransformations, writableSyntaxInstances) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// semantics instances

lazy val programSemanticsInstances = project
  .in(file("programSemanticsInstances"))
  .settings(
    commonSettings,
    name := "programSemanticsInstances"
  ).dependsOn(programSemantics, computationSemantics, programSyntaxInstances)

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

lazy val programRunners = project
  .in(file("programRunners"))
  .settings(
    commonSettings,
    name := "programRunners"
  ).dependsOn(types) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// program examples

lazy val programExampleUtils = project
  .in(file("programExampleUtils"))
  .settings(
    commonSettings,
    name := "programExampleUtils"
  ).dependsOn(types, programSyntax)  

lazy val programExamples = project
  .in(file("programExamples"))
  .settings(
    commonSettings,
    name := "programExamples"
  ).dependsOn(programSyntax, programExampleUtils)    

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// main program examples, effectfree I/0

lazy val mainExampleUtils = project
  .in(file("mainExampleUtils"))
  .settings(
    commonSettings,
    name := "mainExampleUtils"
  ).dependsOn(programSyntax, writableSyntaxInstances, programExampleUtils, effectfulMainProgramUtils)

lazy val mainExamples = project
  .in(file("mainExamples"))
  .settings(
    commonSettings,
    name := "mainExamples"
  ).dependsOn(programSyntaxInstances, programSemanticsInstances, programRunners, programExamples, mainExampleUtils)   

  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// main program examples, effectul I/O

lazy val effectfulMainProgramUtils = project
  .in(file("effectfulMainProgramUtils"))
  .settings(
    commonSettings,
    name := "effectfulMainProgramUtils"
  ).dependsOn(types, utils, programSyntax)

lazy val effectfulMainExampleUtils = project
  .in(file("effectfulMainExampleUtils"))
  .settings(
    commonSettings,
    name := "effectfulMainExampleUtils"
  ).dependsOn(programSyntax, effectfulMainProgramUtils)

lazy val effectfulMainExamples = project
  .in(file("effectfulMainExamples"))
  .settings(
    commonSettings,
    name := "effectfulMainExamples"
  ).dependsOn(programSyntaxInstances, programSemanticsInstances, programRunners, programExamples, effectfulMainExampleUtils) 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
