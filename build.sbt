val pdbp = "pdbp"
val pdbpVersion = "0.0.1"
val dottyVersion = "0.11.0-bin-20181111-180c8df-NIGHTLY"

lazy val commonSettings = Seq(
    organization := pdbp,
    version := pdbpVersion,
    scalaVersion := dottyVersion
)

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

lazy val naturalTransformation = project
  .in(file("naturalTransformation"))
  .settings(
    commonSettings,
    name := "naturalTransformation"
  ).dependsOn(types) 

lazy val liftingSyntax = project
  .in(file("liftingSyntax"))
  .settings(
    commonSettings,
    name := "liftingSyntax"
  ).dependsOn(types, utils)   

lazy val programSyntax = project
  .in(file("programSyntax"))
  .settings(
    commonSettings,
    name := "programSyntax"
  ).dependsOn(types, utils, naturalTransformation, liftingSyntax) 

lazy val computationSyntax = project
  .in(file("computationSyntax"))
  .settings(
    commonSettings,
    name := "computationSyntax"
  ).dependsOn(types, utils, naturalTransformation, liftingSyntax, programSyntax)           

lazy val computationTransformations = project
  .in(file("computationTransformations"))
  .settings(
    commonSettings,
    name := "computationTransformations"
  ).dependsOn(types, utils, naturalTransformation, programSyntax, computationSyntax) 

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
  ).dependsOn(types, naturalTransformation, programSyntax, computationSyntax, computationTransformations, programSemantics) 

lazy val mainProgramUtils = project
  .in(file("mainProgramUtils"))
  .settings(
    commonSettings,
    name := "mainProgramUtils"
  ).dependsOn(types, utils, programSyntax)     

lazy val programSyntaxInstances = project
  .in(file("programSyntaxInstances"))
  .settings(
    commonSettings,
    name := "programSyntaxInstances"
  ).dependsOn(types, programSyntax, computationSyntax, computationTransformations) 

lazy val programSemanticsInstances = project
  .in(file("programSemanticsInstances"))
  .settings(
    commonSettings,
    name := "programSemanticsInstances"
  ).dependsOn(programSemantics, computationSemantics, programSyntaxInstances)

lazy val programRunners = project
  .in(file("programRunners"))
  .settings(
    commonSettings,
    name := "programRunners"
  ) 

lazy val programExampleUtils = project
  .in(file("programExampleUtils"))
  .settings(
    commonSettings,
    name := "programExampleUtils"
  ).dependsOn(types)  

lazy val programExamples = project
  .in(file("programExamples"))
  .settings(
    commonSettings,
    name := "programExamples"
  ).dependsOn(programSyntax, programExampleUtils)  

lazy val mainExampleUtils = project
  .in(file("mainExampleUtils"))
  .settings(
    commonSettings,
    name := "mainExampleUtils"
  ).dependsOn(programSyntax, mainProgramUtils)

lazy val mainExamples = project
  .in(file("mainExamples"))
  .settings(
    commonSettings,
    name := "mainExamples"
  ).dependsOn(programSyntaxInstances, programSemanticsInstances, programRunners, programExamples, mainExampleUtils)   

  


