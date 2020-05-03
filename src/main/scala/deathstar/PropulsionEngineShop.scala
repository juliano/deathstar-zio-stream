package deathstar

import deathstar.DeathStarPlans._
import zio.stream.Stream

class PropulsionEngineShop(engine: PropulsionEngine) {

  val engines: Stream[Nothing, PropulsionEngine] = Stream(engine).forever

  def installEngine(stations: DStream): DStream =
    stations
      .zip(engines)
      .map { case (station, engine) => station.installEngine(engine) }
}
