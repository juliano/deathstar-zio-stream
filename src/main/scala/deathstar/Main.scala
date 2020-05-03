package deathstar

import zio._
import zio.console.putStrLn
import zio.duration._

object Main extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = {
    val shop = new SpaceStationBodyShop(1.second)

    val program = shop.moonShapedSpaceStations.take(1).runCollect

    program.foldM(
      e => putStrLn(s"Execution failed with: ${e.printStackTrace()}") *> ZIO.succeed(1),
      _ => putStrLn(s"Death Star is fully operational!") *> ZIO.succeed(0)
    )
  }
}