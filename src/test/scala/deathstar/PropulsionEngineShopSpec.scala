package deathstar

import deathstar.DeathStarPlans._
import zio.stream.ZStream
import zio.test.Assertion.isTrue
import zio.test._

object PropulsionEngineShopSpec extends DefaultRunnableSpec {

  def spec = suite("Propulsion Engine shop")(
    testM("installs requested engine") {
      val stations = ZStream(UnfinishedSpaceStation())
      val shop = new PropulsionEngineShop(HyperDriveEngine)

      val stream = stations.via(shop.installEngine).take(5)
      for {
        stationWithEngine <- stream.runCollect
      } yield assert(
        stationWithEngine.forall(_.engine == Some(HyperDriveEngine))
      )(isTrue)
    }
  )
}
