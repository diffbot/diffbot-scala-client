organization := "com.diffbot"

name := "diffbot-scala-api"

organizationHomepage := Some(new URL("http://diffbot.com/"))

version := "1.0"

scalaVersion := "2.10.3"

scalacOptions := Seq(
      "-encoding", "utf8",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.6",
      "-Xlint"
    )

resolvers ++= Seq("spray repo" at "http://repo.spray.io",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= Seq("io.spray" % "spray-can" % "1.2.0",
  "io.spray" % "spray-http" % "1.2.0",
  "io.spray" %% "spray-json" % "1.2.5",
  "com.typesafe.akka" %% "akka-actor" % "2.2.3")

exportJars := true
