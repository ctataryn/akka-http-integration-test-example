name := "akka-http-client-test-example"

version := "0.1"

scalaVersion := "2.12.9"

fork in Test := true

val akkaVersion = "2.5.24"
val akkaHttpVersion = "10.1.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

  "ch.qos.logback"          %    "logback-classic"              %    "1.2.3",
  "org.slf4j"               %    "slf4j-api"                    %    "1.7.25",
  "org.slf4j"               %    "jcl-over-slf4j"               %    "1.7.25",
  "org.slf4j"               %    "jul-to-slf4j"                 %    "1.7.25",
  
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "org.specs2" %% "specs2-core" % "4.6.0" % Test,
  "org.specs2" %% "specs2-mock" % "4.6.0" % Test
  
)