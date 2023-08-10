package io.ehwaz.board

import caliban.*
import caliban.relay.*
import io.ehwaz.{EhwazTask, GenericEhwazError}
import io.ehwaz.apispec.BoardSchema.Board
import zio.{Random, ZIO, ZLayer}

final case class CategoryService(
  boardRepository: BoardRepository,
  categoryRepository: CategoryRepository
):
  import io.ehwaz.apispec.CategorySchema.*

  def resolver =
    ZIO.succeed {
      RootResolver(
        Queries(
          findAll
        ),
        Mutations(
          addCategory,
          deleteCategory
        )
      )
    }

  private def findAll(): EhwazTask[List[Category]] =
    for
      categories <- categoryRepository.findAll()
      categoriesWithBoards <- ZIO.collectAll(categories.map { category =>
        for
          boards <- boardRepository.findByCategoryId(category.id)
        yield (category, boards)
      })
    yield categoriesWithBoards.map { case (categoryRow: CategoryRow, boards: List[BoardRow]) =>
      Category(
        name = categoryRow.name,
        description = categoryRow.description,
        boards = boards.map(row => Board(
          name = row.name,
          description = row.description
        ))
      )
    }

  private def addCategory(addCategoryArgs: AddCategoryArgs): EhwazTask[AddCategoryResult] =
    for
      uuid <- Random.nextUUID
      category <- categoryRepository.create(
        CategoryRow(
          id = CategoryId(uuid),
          name = addCategoryArgs.input.name,
          description = addCategoryArgs.input.description
        )
      )
    yield AddCategoryResult(category.id, true)


  private def deleteCategory(deleteCategoryArgs: DeleteCategoryArgs): EhwazTask[DeleteCategoryResult] =
    ZIO.succeed(DeleteCategoryResult(false))

object CategoryService:
  val layer: ZLayer[BoardRepository with CategoryRepository, Throwable, CategoryService] =
    ZLayer.fromFunction(CategoryService.apply _)