name := "akka-persistence-knolx"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.24"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.24" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-persistence" % "2.5.24",
  "org.iq80.leveldb"            % "leveldb"          % "0.7",
  "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
)