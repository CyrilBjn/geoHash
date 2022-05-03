ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.14"
lazy val sparkVersion = "3.2.1"
name := "geoHash"
version := "1.0"
scalaVersion := "2.12.14"
lazy val root = (project in file("."))
  .settings(
    name := "geoHash",
    idePackagePrefix := Some("octo.mentoring.project")
  )
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.11" % Test,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion)
compile / run / mainClass := Some("octo.mentoring.project.Main")