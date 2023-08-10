package io.ehwaz.discussion

import caliban.schema.*
import doobie.*
import io.ehwaz.users.ProfileId

import java.util.UUID

final case class PostRow(id: PostId, content: String, authorProfileId: ProfileId, topicId: TopicId)

opaque type PostId = UUID
object PostId:
  def apply(uuid: UUID): PostId = uuid

  def fromString(uuidString: String): PostId =
    PostId(UUID.fromString(uuidString))

  given schema: Schema[Any, PostId] = Schema.uuidSchema
  given argBuilder: ArgBuilder[PostId] = ArgBuilder.uuid

  given idGet: Get[PostId] = Get[String].map(PostId.fromString)
  given idPut: Put[PostId] = Put[String].contramap(_.toString)