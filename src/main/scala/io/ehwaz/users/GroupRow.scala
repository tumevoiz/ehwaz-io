package io.ehwaz.users

import doobie.*
import caliban.schema.*
import java.util.UUID

case class Group(id: GroupId, name: String)
opaque type GroupId = UUID
object GroupId:
  def apply(uuid: UUID): GroupId = uuid

  def fromString(uuidString: String): GroupId =
    GroupId(UUID.fromString(uuidString))

  given schema: Schema[Any, GroupId] = Schema.uuidSchema
  given argBuilder: ArgBuilder[GroupId] = ArgBuilder.uuid

  given idGet: Get[GroupId] = Get[String].map(GroupId.fromString)
  given idPut: Put[GroupId] = Put[String].contramap(_.toString)