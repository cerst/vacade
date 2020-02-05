lazy val root = (project in file("."))
  .aggregate(`akka-http`, avro4s, `jsoniter-scala`, pureconfig, quill)
  .settings(
    // root intentionally does not contain any code, so don't publish
    ReleaseSettings.disabled,
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "Value Class Derivations-root"
  )

lazy val `akka-http` = (project in file("akka-http"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.akka.http"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.`akka-http`,
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

lazy val pureconfig: Project = (project in file("pureconfig"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.pureconfig"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.pureconfig,
    name := "vacade-pureconfig"
  )


lazy val quill = (project in file("quill"))
  .settings(
    ReleaseSettings.libraryOptimized("com.github.cerst.vacade.quill"),
    crossScalaVersions := CommonValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.quill,
    name := "vacade-quill"
  )
