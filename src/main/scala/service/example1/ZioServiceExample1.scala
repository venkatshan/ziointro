package service.example1
import zio.logging.log
import zio.{Clock, Console, ExitCode, Has, URIO, ZIO}

object ZioServiceExample1 extends zio.App {
  import PrintlnService.log

  val app: ZIO[Has[PrintlnService], Nothing, Unit] = log("Zio Print service")
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    app.inject(PrintlnServiceLive.layer, Console.live, Clock.live).exitCode

}
