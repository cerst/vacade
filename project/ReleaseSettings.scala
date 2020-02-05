import sbt.Keys._
import sbt._
import xerial.sbt.Sonatype.autoImport.sonatypePublishToBundle

object ReleaseSettings {

  private lazy val library: Seq[Def.Setting[_]] = Seq(
    developers := List(Developer("cerst", "Constantin Gerstberger", "", url("https://github.com/cerst"))),
    homepage := Some(CommonValues.homepage),
    // keep consistent with DefaultSettingsPlugin.sbtHeaderSettings
    licenses += "Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0"),
    publishMavenStyle := true,
    publishTo := sonatypePublishToBundle.value,
    scmInfo := Some(ScmInfo(CommonValues.homepage, CommonValues.connection))
  )

  // https://www.lightbend.com/blog/scala-inliner-optimizer
  private def optimized(inlineFromPackage: String): Seq[Def.Setting[_]] = {
    val options = sys.env.get("RELEASE") match {
      case Some("true") =>
        Seq("-opt:l:method", "-opt:l:inline", s"-opt-inline-from:$inlineFromPackage.**", "-opt-warnings")
      case _ =>
        Seq.empty
    }
    scalacOptions ++= options
  }

  lazy val disabled = Seq(skip in publish := true)

  def libraryOptimized(inlineFromPackage: String): Seq[Def.Setting[_]] = library ++ optimized(inlineFromPackage)

}

