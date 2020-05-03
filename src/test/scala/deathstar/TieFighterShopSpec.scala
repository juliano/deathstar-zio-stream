package deathstar

import deathstar.DeathStarPlans._
import zio.stream.ZStream
import zio.test.Assertion.isTrue
import zio.test._

object TieFighterShopSpec extends DefaultRunnableSpec {

  def spec = suite("Tie Fighter shop")(
    testM("deploy fleet given a group size") {
      val groupSize = 100
      val stations = ZStream(UnfinishedSpaceStation())
      val shop = new TieFighterShop(groupSize)

      val stream = stations.via(shop.deployFleet).take(5)
      for {
        stationWithFleet <- stream.runCollect
      } yield assert(stationWithFleet.forall(_.fleet.size == groupSize))(isTrue)
    }
  )
}
