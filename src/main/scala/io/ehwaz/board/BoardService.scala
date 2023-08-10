package io.ehwaz.board

import caliban.RootResolver
import caliban.wrappers.ApolloTracing.Resolver

import io.ehwaz.*

import zio.{IO, Random, UIO, ZIO, ZLayer}

import java.util.UUID

case class BoardService(
  boardRepository: BoardRepository,
  categoryRepository: CategoryRepository
):
  import io.ehwaz.apispec.BoardSchema.*

  def resolver =
    ZIO.succeed {
      RootResolver(
        Queries(
          listBoards
        ),
        Mutations(
          addBoard
        )
      )
    }

  private def listBoards(): EhwazTask[List[Board]] =
    for
      boards <- boardRepository.findAll()
    yield boards.map(row => Board(row.name, row.description))

  private def addBoard(addBoardArgs: AddBoardArgs): EhwazTask[AddBoardResult] =
    for
      category <- categoryRepository.findById(addBoardArgs.input.categoryId)
        .someOrFail(NotFoundError(addBoardArgs.input.categoryId.toString, "category"))
      uuid <- Random.nextUUID
      addedBoard <- boardRepository.create(
        BoardRow(
          id = BoardId(uuid),
          name = addBoardArgs.input.name,
          description = addBoardArgs.input.description,
          categoryId = category.get.id
        )
      )
    yield AddBoardResult(addedBoard.id)

object BoardService:
  val layer: ZLayer[BoardRepository with CategoryRepository, Throwable, BoardService] =
    ZLayer.fromFunction(BoardService.apply _)