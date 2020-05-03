package deathstar

import deathstar.DeathStarPlans._
import zio.stream.Stream

class TieFighterShop(groupSize: Int) {

  val tieFighters: Stream[Nothing, TieFighter] = Stream(TieFighter()).forever

  def deployFleet(stations: DStream): DStream =
    stations
      .zip(tieFighters.grouped(groupSize))
      .map { case (station, fleet) => station.deployFleet(fleet) }
}
