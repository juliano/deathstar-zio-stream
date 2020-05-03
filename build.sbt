import Dependencies.Libraries._

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    organization := "juliano",
    name := "deathstar-zio-stream",
    version := "0.0.1",
    scalaVersion := "2.13.1",
    maxErrors := 3,
    libraryDependencies ++= Seq(
        zio,
        zioStreams,
        zioInteropCats,
        zioLogging,
        logback,

        zioTestSbt
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

scalacOptions --= Seq(
    "-Xfatal-warnings"
)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
