import sbt._

object Dependencies {

  object Versions {
    val zio            = "1.0.0-RC18-2"
  }

  object Libraries {
    def zioM(artifact: String): ModuleID = "dev.zio" %% artifact % Versions.zio

    val zio = zioM("zio")
    val zioStreams = zioM("zio-streams")
    val zioTest = zioM("zio-test") % "test"
    val zioTestSbt = zioM("zio-test-sbt") % "test"
  }
}
