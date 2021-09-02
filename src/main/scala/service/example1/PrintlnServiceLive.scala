package service.example1package service.example1
import zio._

import scala.concurrent.java8.FuturesConvertersImpl.P

/**
  * STEP 2 : Define dependencies and provide implementation
  *
  * Naming convention Live represents impleementation of an interface.
  */
case class PrintlnServiceLive(console: Console, clock: Clock) extends PrintlnService {
  // Create an effect that succeeds with Unit/void as return
  override def log(line: String): UIO[Unit] =
    for {
      ct <- clock.currentDateTime
      _ <- console.printLine(s"[$ct] - $line").orDie
      _ <- ZIO.succeed(print(s"[$ct] - $line"))
    } yield ()
}

/**
  * STEP 3: Define ZLayer - lift service into layer
  */
object PrintlnServiceLive {

  val layer: URLayer[Has[Console] with Has[Clock], Has[PrintlnService]] = (PrintlnServiceLive(_, _)).toLayer

}
