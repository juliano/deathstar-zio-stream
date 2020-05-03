package deathstar

import java.util.UUID

import zio.clock.Clock
import zio.stream.ZStream

object DeathStarPlans {

  type DStream = ZStream[Clock, Throwable, UnfinishedSpaceStation]

  case class UnfinishedSpaceStation(engine: Option[PropulsionEngine] = None,
                                    shield: Option[ForceShield] = None,
                                    fleet: List[TieFighter] = List.empty,
                                    laser: Option[SuperLaser] = None) {

    def installEngine(engine: PropulsionEngine): UnfinishedSpaceStation =
      copy(engine = Some(engine))

    def installShield(shield: ForceShield): UnfinishedSpaceStation =
      copy(shield = Some(shield))

    def deployFleet(fleet: List[TieFighter]): UnfinishedSpaceStation =
      copy(fleet = fleet)

    def installLaser(superLaser: SuperLaser): UnfinishedSpaceStation =
      copy(laser = Some(superLaser))
  }

  case class SerialNumber(value: UUID = UUID.randomUUID())

  sealed trait PropulsionEngine

  case object HyperDriveEngine extends PropulsionEngine

  case object InfiniteImprobabilityDrive extends PropulsionEngine

  case class ForceShield()

  case class TieFighter()

  case class SuperLaser(serialNumber: SerialNumber = SerialNumber())

  case class DeathStar(engine: PropulsionEngine,
                       shield: ForceShield,
                       fleet: List[TieFighter],
                       laser: SuperLaser)

}