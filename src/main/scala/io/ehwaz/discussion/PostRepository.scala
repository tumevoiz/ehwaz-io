package io.ehwaz.discussion

import doobie.*
import doobie.implicits.*
import io.ehwaz.EhwazTask
import io.ehwaz.external.DatabaseService
import zio.*
import zio.interop.catz.*

final case class PostRepository(databaseService: DatabaseService):
  def findByTopicId(topicId: TopicId): EhwazTask[List[PostRow]] =
    sql"select * from posts where topic_id=${topicId}"
      .query[PostRow]
      .to[List]
      .transact(databaseService.tx)
      .orDie

  def create(post: PostRow): EhwazTask[PostRow] =
    sql"""insert into posts (id, content, author_profile_id, topic_id)
         values (${post.id}, ${post.content}, ${post.authorProfileId}, ${post.topicId})
       """
      .update
      .run
      .map(_ => post)
      .transact(databaseService.tx)
      .tapError(e => ZIO.logError(s"PostRepository SQL error: ${e.getMessage}"))
      .orDie

object PostRepository:
  val layer: ZLayer[DatabaseService, Throwable, PostRepository] =
    ZLayer.fromFunction(PostRepository.apply _)