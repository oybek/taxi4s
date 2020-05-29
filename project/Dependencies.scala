import sbt._

object Dependencies {

  object V {
    val scalaTest = "3.0.5"
    val catsCore = "2.0.0"
    val catsEffect = "2.0.0"
    val circe = "0.13.0"
    val http4s = "0.21.3"
    val logback = "1.2.3"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % V.scalaTest % Test
  val catsCore = "org.typelevel" %% "cats-core" % V.catsCore
  val catsEffect = "org.typelevel" %% "cats-effect" % V.catsEffect
  val circeCore = "io.circe" %% "circe-core" % V.circe
  val circeGeneric = "io.circe" %% "circe-generic" % V.circe
  val circeGenericExtras = "io.circe" %% "circe-generic-extras" % V.circe
  val http4s = Seq(
    "org.http4s" %% "http4s-blaze-client" % V.http4s,
    "org.http4s" %% "http4s-dsl" % V.http4s,
    "org.http4s" %% "http4s-circe" % V.http4s,
    "org.http4s" %% "http4s-okhttp-client" % "0.21.0" % Test
  )
  val logger = Seq("ch.qos.logback" % "logback-classic" % V.logback)
  val scalaMock = "org.scalamock" %% "scalamock" % "4.4.0" % Test

  val common = Seq(
    scalaTest,
    catsCore,
    catsEffect,
    circeCore,
    circeGeneric,
    circeGenericExtras,
    scalaMock
  ) ++ http4s ++ logger
}
