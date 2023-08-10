package io.ehwaz.external

import doobie.util.transactor.Transactor
import zio.*
import zio.interop.catz.*

case class DatabaseService():
  def tx = Transactor.fromDriverManager[Task](
    "com.mysql.jdbc.Driver",
    "jdbc:mysql://localhost:3306/ehwaz",
    "root",
    "example"
  )

object DatabaseService:
  val layer: ZLayer[Any, Throwable, DatabaseService] =
    ZLayer.fromFunction(DatabaseService.apply _)