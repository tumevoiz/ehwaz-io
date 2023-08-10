package io.ehwaz.discussion

import caliban.schema.*
import doobie.*
import io.ehwaz.board.BoardId

import java.util.UUID

final case class TopicRow(id: TopicId, name: String, boardId: BoardId)

opaque type TopicId = UUID
object TopicId:
 def apply(uuid: UUID): TopicId = uuid
 
 def fromString(uuidString: String): TopicId =
  TopicId(UUID.fromString(uuidString))
 
 given schema: Schema[Any, TopicId] = Schema.uuidSchema
 given argBuilder: ArgBuilder[TopicId] = ArgBuilder.uuid
 
 given idGet: Get[TopicId] = Get[String].map(TopicId.fromString)
 given idPut: Put[TopicId] = Put[String].contramap(_.toString)