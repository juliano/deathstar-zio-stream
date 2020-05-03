import sbt._

object Dependencies {

  object Versions {
    val zio            = "1.0.0-RC18-2"
    val zioInteropCats = "2.0.0.0-RC12"
    val zioLogging     = "0.2.7"
    val logback        = "1.2.3"
  }

  object Libraries {
    def zioM(artifact: String): ModuleID = "dev.zio" %% artifact % Versions.zio

    val zio = zioM("zio")
    val zioStreams = zioM("zio-streams")
    val zioInteropCats = "dev.zio" %% "zio-interop-cats" % Versions.zioInteropCats
    val zioLogging = "dev.zio" %% "zio-logging" % Versions.zioLogging

    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback

    val zioTest = zioM("zio-test") % "test"
    val zioTestSbt = zioM("zio-test-sbt") % "test"
  }
}
