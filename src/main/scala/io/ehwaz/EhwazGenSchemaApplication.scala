package io.ehwaz

import caliban.*
import caliban.schema.Schema.auto.*
import io.ehwaz.board.{BoardRepository, BoardService, CategoryRepository, CategoryService}
import io.ehwaz.discussion.*
import io.ehwaz.external.DatabaseService
import io.ehwaz.users.UserService
import zio.logging.backend.SLF4J
import zio.{Runtime, Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZLayer}

object EhwazGenSchemaApplication extends ZIOAppDefault:
  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    (for
      _ <- ZIO.logInfo("ehwaz.io Schema Generator")
      boardService <- ZIO.service[BoardService]
      categoryService <- ZIO.service[CategoryService]
      postService <- ZIO.service[PostService]
      topicService <- ZIO.service[TopicService]
      userService <- ZIO.service[UserService]
      boardResolver <- boardService.resolver
      categoryResolver <- categoryService.resolver
      postResolver <- postService.resolver
      topicResolver <- topicService.resolver
      userResolver <- userService.resolver
      api = graphQL(boardResolver)
        |+| graphQL(categoryResolver)
        |+| graphQL(postResolver)
        |+| graphQL(topicResolver)
        |+| graphQL(userResolver)
      _ <- ZIO.log(api.render)
    yield ()).provide(
      BoardService.layer,
      BoardRepository.layer,
      CategoryRepository.layer,
      CategoryService.layer,
      DatabaseService.layer,
      PostRepository.layer,
      PostService.layer,
      TopicRepository.layer,
      TopicService.layer
    )

