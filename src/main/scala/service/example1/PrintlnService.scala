package service.example1

import zio._

/**
  * STEP 1: Define interfacee
  *
  */
trait PrintlnService {
  def log(line: String): UIO[Unit]
}

/**
  * Provide accessor methods.
  * Use ZIO.serviceWith to define accessors inside the component.
  */
object PrintlnService {
  def log(line: String): URIO[Has[PrintlnService], Unit] = ZIO.serviceWith[PrintlnService](_.log(line))
}
