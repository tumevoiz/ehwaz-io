package io.ehwaz.users

import caliban.schema.*
import doobie.*
import java.util.{Date, UUID}

case class ProfileRow(
  id: ProfileId,
  username: String,
  birthday: Option[Date],
  title: Option[String],
  primaryGroupId: GroupId
)

opaque type ProfileId = UUID
object ProfileId:
  def apply(uuid: UUID): ProfileId = uuid

  def fromString(uuidString: String): ProfileId =
    ProfileId(UUID.fromString(uuidString))

  given schema: Schema[Any, ProfileId] = Schema.uuidSchema
  given argBuilder: ArgBuilder[ProfileId] = ArgBuilder.uuid

  given idGet: Get[ProfileId] = Get[String].map(ProfileId.fromString)
  given idPut: Put[ProfileId] = Put[String].contramap(_.toString)