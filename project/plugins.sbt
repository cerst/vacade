// http://get-coursier.io/
// fast(er) dependency resolution and fetching
// useful commands:
//    coursierDependencyTree - render a dependency tree including eviction information (printed yellow)
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.3")

// https://github.com/dwijnand/sbt-dynver
// derive and set project versions based on Git meta data
addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.0.0")

// https://github.com/sbt/sbt-header
// generate and update source code license headers
// useful commands:
//    headerCreate
addSbtPlugin("de.heikoseeberger" % "sbt-header" % "5.6.0")

// https://github.com/sbt/sbt-pgp
// pgp-sign releases
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.1")

// https://scalameta.org/scalafmt/
// thorough code formatting (recommendation: use Intellij plugin configured as format-on-save)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.0")

// https://github.com/xerial/sbt-sonatype
// publish artifacts to Sonatype
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.3")

// https://github.com/rtimush/sbt-updates
// check for new releases of plugins and dependencies
// when updating this version in the scala-base repo,
// don't forget to change <project-root>/project/project/sbt-updates.sbt as well
// useful commands
//    dependencyUpdates - list possible updates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.1")
