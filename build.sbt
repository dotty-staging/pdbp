val dottyVersion = "0.10.0-RC1"
val pdbp = "pdbp"
val pdbpVersion = "0.0.1"

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

lazy val programSyntax = project
  .in(file("programSyntax"))
  .settings(
    commonSettings,
    name := "programSyntax"
  ).dependsOn(types, utils, naturalTransformation)         

 lazy val programSemantics = project
  .in(file("programSemantics"))
  .settings(
    commonSettings,
    name := "programSemantics"
  ).dependsOn(types, naturalTransformation, programSyntax)    

lazy val programUtils = project
  .in(file("programUtils"))
  .settings(
    commonSettings,
    name := "programUtils"
  ).dependsOn(types, utils, programSyntax)   

lazy val programInstances = project
  .in(file("programInstances"))
  .settings(
    commonSettings,
    name := "programInstances"
  ).dependsOn(types, programSyntax) 

lazy val programSemanticsInstances = project
  .in(file("programSemanticsInstances"))
  .settings(
    commonSettings,
    name := "programSemanticsInstances"
  ).dependsOn(programSemantics, programInstances)

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
  ).dependsOn(programSyntax, programUtils)

lazy val mainExamples = project
  .in(file("mainExamples"))
  .settings(
    commonSettings,
    name := "mainExamples"
  ).dependsOn(programInstances, programSemanticsInstances, programRunners, programExamples, mainExampleUtils)   

  


