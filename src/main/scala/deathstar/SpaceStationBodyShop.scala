package deathstar

import deathstar.DeathStarPlans._
import zio.duration.Duration
import zio.stream.ZStream
import zio.{Schedule, ZIO}

class SpaceStationBodyShop(buildTime: Duration) {

  val moonShapedSpaceStations: DStream =
    ZStream.repeatEffectWith(
      ZIO(UnfinishedSpaceStation()),
      Schedule.spaced(buildTime)
    )
}
