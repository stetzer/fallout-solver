name := "fallout-solver"

version := "0.1"

scalaVersion := "2.13.16"

nativeLinkStubs := true

enablePlugins(ScalaNativePlugin, JmhPlugin)

libraryDependencies ++= Seq(
  "org.specs2" %%% "specs2-core" % "4.20.5" % Test,
  "org.openjdk.jmh" % "jmh-core" % "1.37" % Test,
  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.37" % "provided"
)

testFrameworks := Seq(TestFrameworks.Specs2)

Test / testOptions ++= Seq(
  Tests.Argument(TestFrameworks.Specs2, "console"),
  Tests.Argument(TestFrameworks.Specs2, "showtimes")
)

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings"
)

scalacOptions in Test ++= Seq("-Yrangepos")

lazy val root = (project in file("."))
  .enablePlugins(JmhPlugin)
  .settings(
    name := "fallout-solver",
    Jmh / sourceDirectory := (Test / sourceDirectory).value,
    Jmh / classDirectory := (Test / classDirectory).value,
    Jmh / dependencyClasspath := (Test / dependencyClasspath).value
)
