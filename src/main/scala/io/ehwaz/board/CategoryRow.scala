package io.ehwaz.board

import caliban.relay.*
import caliban.schema.{ArgBuilder, Schema}
import doobie.*

import java.util.UUID

case class CategoryRow(id: CategoryId, name: String, description: String)

opaque type CategoryId = UUID
object CategoryId:
  def apply(uuid: UUID): CategoryId = uuid

  def fromString(uuidString: String): CategoryId =
    CategoryId(UUID.fromString(uuidString))

  given schema: Schema[Any, CategoryId] = Schema.uuidSchema
  given argBuilder: ArgBuilder[CategoryId] = ArgBuilder.uuid

  given idGet: Get[CategoryId] = Get[String].map(CategoryId.fromString)
  given idPut: Put[CategoryId] = Put[String].contramap(_.toString)