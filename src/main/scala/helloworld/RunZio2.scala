package helloworld

import org.checkerframework.checker.units.qual.s
import zio._
import zio.Random.nextInt
import zio.Console.printLine

object RunZio2 extends zio.App {

  val effect: ZIO[Has[Console] with Has[Random], Nothing, Unit] = for {
    _ <- printLine("hello world").orDie
    rint <- nextInt
    _ <- printLine(s"Random number $rint").orDie
  } yield ()

  //Provide Live layers
  val mainApp: ZIO[Any, Nothing, Unit] = effect.provideLayer(Console.live ++ Random.live)

  override def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = mainApp.exitCode
}
