package io.ehwaz.board

import caliban.relay.*
import caliban.schema.{ArgBuilder, Schema}
import doobie.{Get, Put}

import java.util.UUID

case class BoardRow(id: BoardId, name: String, description: String, categoryId: CategoryId)

opaque type BoardId = UUID
object BoardId:
	def apply(uuid: UUID): BoardId = uuid

	def fromString(uuidString: String): BoardId =
		BoardId(UUID.fromString(uuidString))

	given schema: Schema[Any, BoardId] = Schema.uuidSchema
	given argBuilder: ArgBuilder[BoardId] = ArgBuilder.uuid

	given idGet: Get[BoardId] = Get[String].map(BoardId.fromString)
	given idPut: Put[BoardId] = Put[String].contramap(_.toString)
