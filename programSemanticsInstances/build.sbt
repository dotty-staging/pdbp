val dottyVersion = "0.10.0-RC1"
val pdbp = "pdbp"
val pdbpVersion = "0.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    organization := pdbp,
    name := "programsemanticsinstances",
    version := pdbpVersion,
    scalaVersion := dottyVersion, 
    libraryDependencies += pdbp %% "programsemantics" % pdbpVersion,
    libraryDependencies += pdbp %% "programinstances" % pdbpVersion
  )