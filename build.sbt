lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    // root intentionally does not contain any code, so don't publish
    ReleaseSettings.disabled,
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "Value Class Derivations-root"
  )

lazy val core = (project in file("core"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.coreLibraries,
    name := "Value Class Derivations"
  )
