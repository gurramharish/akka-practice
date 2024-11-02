ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "akka-basics-v2"
  )

ThisBuild / resolvers := Seq(
  "Akka library repository".at("https://repo.akka.io/maven")
)

lazy val akkaVersion = "2.9.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  // akka-serialization-jackson is used for JSON serialization
  "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
//  "org.slf4j" % "slf4j-reload4j" % "2.0.16",
  // akka-actor-testkit-typed is used for testing
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  // add the ScalaTest dependency
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "ch.qos.logback" % "logback-classic" % "1.5.6",

)