package deathstar

import zio.test.Assertion.equalTo
import zio.test._

object ForceShieldShopSpec extends DefaultRunnableSpec {

  def spec = suite("Force Shield shop")(
    testM("flatten shipments into a series of shields") {
      val shipmentSize = 5
      val shields = new ForceShieldShop(shipmentSize).shields.take(20)

      for {
        shieldsNum <- shields.runCount
      } yield assert(shieldsNum)(equalTo(20L))
    }
  )
}
