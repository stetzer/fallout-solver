name := "fallout-solver"

version := "0.1"

scalaVersion := "2.13.16"

nativeLinkStubs := true

enablePlugins(ScalaNativePlugin)

libraryDependencies ++= Seq(
  "org.specs2" %%% "specs2-core" % "4.20.5" % Test
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
