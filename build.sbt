organization := "kz.dar.lab"

name := "backend-dar-lab-lecture-demos"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.22",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.22" % Test,
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.22",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)