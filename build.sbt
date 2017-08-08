name := "scanner-endpoint-protection"
version := "1.0"
scalaVersion := "2.12.1"

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io",
  "MVN Repository" at "http://mvnrepository.com/",
  "Typesafe Repository" at "http://dl.bintray.com/typesafe/maven-releases/",
  "The New Motion Public Repo" at "http://nexus.thenewmotion.com/content/groups/public/"
)


libraryDependencies += "com.typesafe" % "config" % "1.3.1"
libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"
libraryDependencies += "net.codingwell" % "scala-guice_2.12" % "4.1.0"
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
libraryDependencies += "com.typesafe.scala-logging" % "scala-logging_2.12" % "3.5.0"