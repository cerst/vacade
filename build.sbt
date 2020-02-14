lazy val root = (project in file("."))
  .aggregate(`akka-http`, avro4s, `jsoniter-scala`, `test`)
  .settings(
    // root intentionally does not contain any code, so don't publish
    ReleaseSettings.disabled,
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "vacade-root"
  )

lazy val `akka-http` = (project in file("akka-http"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.akka.http"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.`akka-http`(scalaVersion.value),
    name := "vacade-akka-http"
  )

lazy val avro4s = (project in file("avro4s"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.avro4s"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.avro4s,
    name := "vacade-avro4s"
  )

lazy val `jsoniter-scala` = (project in file("jsoniter-scala"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.jsoniter_scala"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.`jsoniter-scala`,
    name := "jsoniter-scala"
  )

// have a separate project for tests
// * allows to declare test classes only once and
// * works around the problem of not being able to use macro-code in the same compilation unit which declared them
//   (by declaring test type in src/main of this module and then having tests as usual)
//   can't put tests into the original modules as this would cause a circular dependency
lazy val `test` = (project in file("test"))
  .dependsOn(`akka-http`, avro4s, `jsoniter-scala`)
  .settings(
    ReleaseSettings.disabled,
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.`test`(scalaVersion.value),
    name := "test",
    scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 13)) => Seq("-Ymacro-annotations")
      case _             => Seq()
    })
  )
