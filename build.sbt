name := "fallout-solver"

version := "0.1"

scalaVersion := "2.13.4"

nativeLinkStubs := true

enablePlugins(ScalaNativePlugin)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.12.0" % "test")

scalacOptions := Seq("-unchecked", "-deprecation")

scalacOptions in Test ++= Seq("-Yrangepos")
