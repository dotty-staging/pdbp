val dottyVersion = "0.10.0-RC1"
val pdbp = "pdbp"
val pdbpVersion = "0.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    organization := pdbp,
    name := "utils",
    version := pdbpVersion,
    scalaVersion := dottyVersion, 
    libraryDependencies += pdbp %% "types" % pdbpVersion
  )

