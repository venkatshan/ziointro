package helloworld

import zio._
import zio.Console._

object RunZio1 extends zio.App {

  val effect: ZIO[Has[Console], Nothing, Unit] = printLine("hello world").orDie
  val mainApp: ZIO[Any, Nothing, Unit] = effect.provideLayer(Console.live)

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = mainApp.exitCode

}
