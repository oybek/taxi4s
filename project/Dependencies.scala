import sbt._

object Dependencies {

  object V {
    val scalaTest = "3.0.5"
    val catsCore = "2.0.0"
    val catsEffect = "2.0.0"
  }

  val scalaTest = "org.scalatest" %% "scalatest" % V.scalaTest % Test
  val catsCore = "org.typelevel" %% "cats-core" % V.catsCore
  val catsEffect = "org.typelevel" %% "cats-effect" % V.catsEffect

  val common = Seq(scalaTest, catsCore, catsEffect)
}
