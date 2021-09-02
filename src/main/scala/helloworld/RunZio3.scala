package helloworld

import org.checkerframework.checker.units.qual.s
import zio._
import zio.Random.nextInt
import zio.Console.{printLine, ConsoleLive}

/**
  * Live layers for built-in services optional.
  * Built-in services are provided through ZEnv type alias.
  */
object RunZio3 extends zio.App {

  // Effect
  val effect: ZIO[Has[Console] with Has[Random], Nothing, Unit] = for {
    _ <- printLine("hello world").orDie
    rint <- nextInt
    _ <- printLine(s"random number is $rint").orDie
  } yield ()

  // Run
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = effect.exitCode
}
