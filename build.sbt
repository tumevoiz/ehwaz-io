ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val calibanVersion = "2.1.0"
lazy val doobieVersion = "1.0.0-RC2"
lazy val zioVersion = "2.0.13"

lazy val root = (project in file("."))
  .settings(
    name := "ehwaz",
    libraryDependencies := Seq(
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion,

      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "io.circe" %% "circe-parser" % "0.14.1",

      "org.slf4j" % "slf4j-api" % "2.0.7",
      "ch.qos.logback" % "logback-classic" % "1.4.7",

      "com.softwaremill.sttp.tapir" %% "tapir-json-circe"     % "1.2.11",

      "org.tpolecat" %% "doobie-core" % doobieVersion,
      "org.tpolecat" %% "doobie-postgres" % doobieVersion,
      "org.tpolecat" %% "doobie-hikari" % doobieVersion,

      "io.github.nremond" %% "pbkdf2-scala" % "0.7.2",
      "com.github.jwt-scala" %% "jwt-core" % "9.2.0",

      "org.flywaydb" % "flyway-core" % "9.16.3",

      "com.mysql" % "mysql-connector-j" % "8.0.33",

      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-interop-cats" % "23.0.0.4",
      "dev.zio" %% "zio-logging" % "2.1.12",
      "dev.zio" %% "zio-logging-slf4j2" % "2.1.12",
      "dev.zio" %% "zio-http" % "0.0.5"
    )
  ).enablePlugins(CalibanPlugin)
