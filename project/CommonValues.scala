import sbt._

/**
  * Stores variable used in multiple places of the build
  */
object CommonValues {

  val connection = "git@github.com:cerst/vacade.git"
  val homepage = url("https://github.com/cerst/vacade")
  val organizationName = "Constantin Gerstberger"
  val scalaVersion = "2.13.5"
  val startYear = 2020

  val crossScalaVersions = List(scalaVersion, "2.12.13")

}
