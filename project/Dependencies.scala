import sbt._

object Dependencies {

  val resolvers: Seq[Resolver] = Seq(
  )

  object Version {

  }

  // comment licenses for dependencies using the SPDX short identifier (see e.g. https://opensource.org/licenses/Apache-2.0)
  // rationale: double check the license when adding a new library avoid having to remove a problematic one later on when it is in use and thus hard to remove
  object Library {

  }

  val `akka-http`: Seq[ModuleID] = Seq()

  val avro4s: Seq[ModuleID] = Seq()

  val `jsoniter-scala`: Seq[ModuleID] = Seq()

  val pureconfig: Seq[ModuleID] = Seq()

  val quill: Seq[ModuleID]= Seq()

}