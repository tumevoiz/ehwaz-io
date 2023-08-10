package io.ehwaz.apispec

import caliban.schema.Annotations.GQLDescription
import caliban.schema.{ArgBuilder, Schema}
import io.ehwaz.EhwazTask
import io.ehwaz.board.{BoardId, CategoryId}

import java.util.UUID

object BoardSchema:
  final case class Board(name: String, description: String)
  object Board:
    given schema: Schema[Any, Board] = Schema.gen
    given argBuilder: ArgBuilder[Board] = ArgBuilder.gen

  final case class AddBoardInput(name: String, description: String, categoryId: CategoryId)
  object AddBoardInput:
    given schema: Schema[Any, AddBoardInput] = Schema.gen
    given argBuilder: ArgBuilder[AddBoardInput] = ArgBuilder.gen

  final case class AddBoardArgs(input: AddBoardInput)
  object AddBoardArgs:
    given schema: Schema[Any, AddBoardArgs] = Schema.gen
    given argBuilder: ArgBuilder[AddBoardArgs] = ArgBuilder.gen

  final case class AddBoardResult(id: BoardId)
  object AddBoardResult:
    given schema: Schema[Any, AddBoardResult] = Schema.gen
    given argBuilder: ArgBuilder[AddBoardResult] = ArgBuilder.gen

  final case class Queries(
    boards: () => EhwazTask[List[Board]]
  )

  final case class Mutations(
    addBoard: AddBoardArgs => EhwazTask[AddBoardResult]
  )
  
  
