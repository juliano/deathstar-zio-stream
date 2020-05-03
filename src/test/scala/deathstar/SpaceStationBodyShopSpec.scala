package deathstar

import zio.Schedule
import zio.duration._
import zio.test.Assertion.equalTo
import zio.test._
import zio.test.environment.{Live, TestClock}

object SpaceStationBodyShopSpec extends DefaultRunnableSpec {

  val stations = new SpaceStationBodyShop(1.second).moonShapedSpaceStations.take(10)

  def spec = suite("Space Station body shop")(
    testM("builds station bodies setting time") {
      for {
        _ <- TestClock.adjust(10.seconds)
        stationBodies <- stations.runCollect
      } yield assert(stationBodies.size)(equalTo(10))
    },
    testM("builds station bodies fasting forward time") {
      for {
        _ <- fastTime(1.second, 10.millis)
        stationBodies <- stations.runCollect
      } yield assert(stationBodies.size)(equalTo(10))
    }
  )

  private def fastTime(testIntervals: Duration, liveIntervals: Duration) = {
    Live.withLive(TestClock.adjust(testIntervals))(
      _.repeat(Schedule.spaced(liveIntervals))).fork
  }
}
