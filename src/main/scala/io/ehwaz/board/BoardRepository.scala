package io.ehwaz.board

import io.ehwaz.EhwazIO
import io.ehwaz.external.DatabaseService

import doobie.*
import doobie.implicits.*
import zio.*
import zio.interop.catz.*

case class BoardRepository(databaseService: DatabaseService):
  def findAll(): EhwazIO[Any, List[BoardRow]] =
    sql"select * from boards"
      .query[BoardRow]
      .to[List]
      .transact(databaseService.tx)
      .orDie

  def findById(boardId: BoardId): EhwazIO[Any, Option[BoardRow]] =
    sql"select * from boards where id=${boardId}"
      .query[BoardRow]
      .option
      .transact(databaseService.tx)
      .orDie

  def findByCategoryId(id: CategoryId): EhwazIO[Any, List[BoardRow]] =
    sql"select * from boards where category_id=${id}"
      .query[BoardRow]
      .to[List]
      .transact(databaseService.tx)
      .orDie

  def create(board: BoardRow): EhwazIO[Any, BoardRow] =
    sql"""
      insert into boards (id, name, description, category_id)
      values (${board.id.toString}, ${board.name}, ${board.description}, ${board.categoryId.toString})
    """
      .update
      .run
      .map(_ => board)
      .transact(databaseService.tx)
      .tapError(e => ZIO.logError(s"BoardRepository SQL error: ${e.getMessage}"))
      .orDie

object BoardRepository:
  val layer: ZLayer[DatabaseService, Throwable, BoardRepository] =
    ZLayer.fromFunction(BoardRepository.apply _)
