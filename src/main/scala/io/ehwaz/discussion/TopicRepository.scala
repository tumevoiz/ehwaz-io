package io.ehwaz.discussion

import doobie.*
import doobie.implicits.*
import io.ehwaz.EhwazTask
import io.ehwaz.board.BoardId
import io.ehwaz.external.DatabaseService
import zio.*
import zio.interop.catz.*

final case class TopicRepository(databaseService: DatabaseService):
  def findById(topicId: TopicId): EhwazTask[Option[TopicRow]] =
    sql"select * from topics where id=${topicId}"
      .query[TopicRow]
      .option
      .transact(databaseService.tx)
      .orDie

  def findByBoardId(boardId: BoardId): EhwazTask[List[TopicRow]]=
    sql"select * from topics where board_id=${boardId}"
      .query[TopicRow]
      .to[List]
      .transact(databaseService.tx)
      .orDie

  def create(topic: TopicRow): EhwazTask[TopicRow] =
    sql"""
         insert into topics (id, name, board_id)
         values (${topic.id}, ${topic.name}, ${topic.boardId.toString})
       """
      .update
      .run
      .map(_ => topic)
      .transact(databaseService.tx)
      .tapError(e => ZIO.logError(s"TopicRepository SQL error: ${e.getMessage}"))
      .orDie

object TopicRepository:
  val layer: ZLayer[DatabaseService, Throwable, TopicRepository] =
    ZLayer.fromFunction(TopicRepository.apply _)