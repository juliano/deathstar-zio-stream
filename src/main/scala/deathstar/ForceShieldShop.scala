package deathstar

import deathstar.DeathStarPlans._
import zio.UIO
import zio.stream.Stream

class ForceShieldShop(shipmentSize: Int) {

  case class ShieldShipment(shields: List[ForceShield])

  val shipments: Stream[Nothing, ShieldShipment] = Stream.repeatEffect(
    UIO(ShieldShipment(List.fill(shipmentSize)(ForceShield())))
  )

  val shields: Stream[Nothing, ForceShield] = shipments.mapConcat(_.shields)

  def installShield(stations: DStream): DStream =
    stations
      .zip(shields)
      .map { case (station, shield) => station.installShield(shield) }
}
