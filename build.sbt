ThisBuild / version := "0.1"
ThisBuild / organization := "io.github.oybek"

val settings = Compiler.settings ++ Seq()

lazy val ytaxi4s = (project in file("."))
  .settings(name := "ytaxi4s")
  .settings(libraryDependencies ++= Dependencies.common)
