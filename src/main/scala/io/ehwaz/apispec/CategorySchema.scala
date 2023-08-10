package io.ehwaz.apispec

import caliban.relay.*
import caliban.schema.*
import caliban.schema.Annotations.{GQLDescription, GQLInputName, GQLName}
import io.ehwaz.EhwazTask
import io.ehwaz.apispec.BoardSchema.Board
import io.ehwaz.board.CategoryId

import java.util.UUID

object CategorySchema:
  final case class Category(name: String, description: String, boards: List[Board])
  object Category:
    given schema: Schema[Any, Category] = Schema.gen
    given argBuilder: ArgBuilder[Category] = ArgBuilder.gen

  final case class AddCategoryInput(name: String, description: String)
  object AddCategoryInput:
    given schema: Schema[Any, AddCategoryInput] = Schema.gen
    given argBuilder: ArgBuilder[AddCategoryInput] = ArgBuilder.gen

  final case class AddCategoryArgs(input: AddCategoryInput)
  object AddCategoryArgs:
    given schema: Schema[Any, AddCategoryArgs] = Schema.gen
    given argBuilder: ArgBuilder[AddCategoryArgs] = ArgBuilder.gen

  final case class DeleteCategoryInput(id: CategoryId)
  object DeleteCategoryInput:
    given schema: Schema[Any, DeleteCategoryInput] = Schema.gen
    given argBuilder: ArgBuilder[DeleteCategoryInput] = ArgBuilder.gen

  final case class DeleteCategoryArgs(input: DeleteCategoryInput)
  object DeleteCategoryArgs:
    given schema: Schema[Any, DeleteCategoryArgs] = Schema.gen
    given argBuilder: ArgBuilder[DeleteCategoryArgs] = ArgBuilder.gen

  final case class AddCategoryResult(id: CategoryId, created: Boolean)
  object AddCategoryResult:
    given schema: Schema[Any, AddCategoryResult] = Schema.gen
    given argBuilder: ArgBuilder[AddCategoryResult] = ArgBuilder.gen

  final case class DeleteCategoryResult(deleted: Boolean)
  object DeleteCategoryResult:
    given schema: Schema[Any, DeleteCategoryResult] = Schema.gen
    given argBuilder: ArgBuilder[DeleteCategoryResult] = ArgBuilder.gen

  final case class Queries(
    categories: () => EhwazTask[List[Category]]
  )

  final case class Mutations(
    addCategory: AddCategoryArgs => EhwazTask[AddCategoryResult],
    deleteCategory: DeleteCategoryArgs => EhwazTask[DeleteCategoryResult]
  )

