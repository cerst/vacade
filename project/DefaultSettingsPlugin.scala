import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.{HeaderLicense, HeaderLicenseStyle, headerLicense}
import sbt.Keys._
import sbt._

object DefaultSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements

  // the rationale for placing settings defs here is that they should (or can) not be updated automatically using the scala-base-sync script
  // in the following, organizationName and startYear would also be required by sbt-header to generate ready-made license headers
  override lazy val projectSettings: Seq[Def.Setting[_]] = {
    sbtHeaderSettings ++
      versionToFileTaskSettings ++
      Seq(
        organization := "com.github.cerst",
        organizationName := CommonValues.organizationName,
        resolvers ++= Dependencies.additionalResolvers,
        scalaVersion := CommonValues.scalaVersion,
        startYear := Some(CommonValues.startYear)
      )
  }

  def sbtHeaderSettings: Seq[Def.Setting[_]] = Seq(
    // keep consistent with ReleaseSettings.licenses
    headerLicense := Some(
      HeaderLicense.ALv2(CommonValues.startYear.toString, CommonValues.organizationName, HeaderLicenseStyle.SpdxSyntax)
    )
  )

  lazy val versionToFile = taskKey[Unit]("Print the version into /target/version-to-file/version")

  def versionToFileTaskSettings: Seq[Def.Setting[_]] = Seq(
    // used to read the version during release ("sbt version" causes much noise which makes extraction error-prone)
    versionToFile := {
      val file = target.value / "version-to-file" / "version"
      IO.write(file, version.value)
    }
  )

}
