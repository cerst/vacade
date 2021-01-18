import sbt._

object Dependencies {

  val additionalResolvers: Seq[Resolver] = Seq()

  private object Version {
    val Akka = "2.6.11"
    val AkkaHttp = "10.2.2"
    val Avro4s = "4.0.4"
    val JsoniterScala = "2.6.2"
    val Newtype = "0.4.4"
    val Paradise = "2.1.1"
    val Scalatest = "3.2.3"
    val ScalatestPlusScalaCheck = "3.2.2.0"
  }

  // comment licenses for dependencies using the SPDX short identifier (see e.g. https://opensource.org/licenses/Apache-2.0)
  // rationale: double check the license when adding a new library avoid having to remove a problematic one later on when it is in use and thus hard to remove
  private object Library {
    // Apache-2.0
    val AkkaHttp = "com.typesafe.akka" %% "akka-http" % Version.AkkaHttp
    // Apache-2.0
    val AkkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % Version.AkkaHttp
    // Apache-2.0
    val AkkaStream = "com.typesafe.akka" %% "akka-stream" % Version.Akka
    // Apache-2.0
    val AkkaStreamTeskit = "com.typesafe.akka" %% "akka-stream-testkit" % Version.Akka
    // Apache-2.0
    val Avro4sCore = "com.sksamuel.avro4s" %% "avro4s-core" % Version.Avro4s
    // MIT
    val JsoniterScalaCore = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % Version.JsoniterScala
    // MIT
    // always only used for compilation
    val JsoniterScalaMacros = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % Version.JsoniterScala % Provided
    // Apache-2.0
    val Newtype = "io.estatico" %% "newtype" % Version.Newtype
    // Apache-2.0
    val Scalatest = "org.scalatest" %% "scalatest" % Version.Scalatest
    // Apache-2.0
    val ScalatestPlusScalaCheck = "org.scalatestplus" %% "scalacheck-1-14" % Version.ScalatestPlusScalaCheck
  }

  private object CompilerPlugin {
    // BSD-3-Clause
    val Paradise = compilerPlugin("org.scalamacros" % "paradise" % Version.Paradise cross CrossVersion.full)
  }

  val `akka-http`: Seq[ModuleID] = Seq(
    Library.AkkaHttp % Provided //
  )

  val avro4s: Seq[ModuleID] = Seq(
    Library.Avro4sCore % Provided //
  )

  val `jsoniter-scala`: Seq[ModuleID] = Seq(
    Library.JsoniterScalaCore % Provided //
  )

  def `test`(scalaVersionValue: String): Seq[ModuleID] = {
    val cross = Seq(
      Library.AkkaHttp,
      Library.AkkaHttpTestkit % Test,
      Library.AkkaStream % Test,
      Library.AkkaStreamTeskit % Test,
      Library.Avro4sCore,
      Library.JsoniterScalaCore,
      Library.JsoniterScalaMacros,
      Library.Newtype,
      Library.Scalatest % Test,
      Library.ScalatestPlusScalaCheck % Test
    )
    val specific = CrossVersion.partialVersion(scalaVersionValue) match {
      case Some((2, 12)) => Seq(CompilerPlugin.Paradise)
      case _             => Seq.empty
    }
    cross ++ specific
  }

}
