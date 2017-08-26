name := "fallout-solver"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")

scalacOptions := Seq("-unchecked", "-deprecation")

scalacOptions in Test ++= Seq("-Yrangepos")
