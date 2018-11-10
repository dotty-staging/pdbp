val dottyVersion = "0.10.0-RC1"
val pdbp = "pdbp"
val examples = "examples"
val pdbpVersion = "0.0.1"
val examplesVersion = pdbpVersion

lazy val root = project
  .in(file("."))
  .settings(
    organization := examples,
    name := "mainexamples",
    version := examplesVersion,
    scalaVersion := dottyVersion, 
    libraryDependencies += pdbp %% "programinstances" % pdbpVersion,
    libraryDependencies += pdbp %% "programsemanticsinstances" % pdbpVersion,
    libraryDependencies += pdbp %% "programrunners" % pdbpVersion,
    libraryDependencies += examples %% "programexamples" % examplesVersion,
    libraryDependencies += examples %% "mainexampleutils" % examplesVersion
  )