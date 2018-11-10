val dottyVersion = "0.10.0-RC1"
val pdbp = "pdbp"
val examples = "examples"
val pdbpVersion = "0.0.1"
val examplesVersion = pdbpVersion

lazy val root = project
  .in(file("."))
  .settings(
    organization := examples,
    name := "mainexampleutils",
    version := examplesVersion,
    scalaVersion := dottyVersion, 
    libraryDependencies += pdbp %% "programsyntax" % pdbpVersion,
    libraryDependencies += pdbp %% "programutils" % pdbpVersion
  )