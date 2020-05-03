package deathstar

import deathstar.DeathStarPlans._
import zio.ZIO
import zio.clock.Clock
import zio.random.Random

class StarshipFactory(stationBodyShop: SpaceStationBodyShop,
                      propulsionEngineShop: PropulsionEngineShop,
                      forceShieldShop: ForceShieldShop,
                      tieFighterShop: TieFighterShop,
                      superLaserShop: SuperLaserShop,
                      auditor: EmpireAuditor) {

  def orderDeathStar(quantity: Int): ZIO[Random with Clock, Throwable, List[DeathStar]] =
    stationBodyShop
      .moonShapedSpaceStations
      .via(propulsionEngineShop.installEngine)
      .via(forceShieldShop.installShield)
      .via(tieFighterShop.deployFleet)
      .via(superLaserShop.installTheLaser)
      .via(auditor.inspect)
      .take(quantity)
      .runCollect
}
