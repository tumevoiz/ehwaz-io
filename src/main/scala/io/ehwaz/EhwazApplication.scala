package io.ehwaz

import caliban.*
import caliban.schema.Schema.auto.*
import io.ehwaz.apispec.*
import io.ehwaz.board.*
import io.ehwaz.discussion.*
import io.ehwaz.external.DatabaseService
import io.ehwaz.users.UserService
import sttp.tapir.json.circe.*
import zio.http.*
import zio.*
import zio.logging.backend.SLF4J

object EhwazApplication extends ZIOAppDefault {
  private def app: ZIO[BoardRepository
    with BoardService
    with CategoryRepository
    with CategoryService
    with DatabaseService
    with PostRepository
    with PostService
    with TopicRepository
    with TopicService
    with Server, Throwable, Nothing] =
    for
      _ <- ZIO.logInfo("ehwaz.io")
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
      apiInterpreter <- api.interpreter
      httpApp = Http.collectRoute[Request] {
        case _ -> !! / "api" / "graphql" => ZHttpAdapter.makeHttpService(apiInterpreter)
        case _ -> !! / "ws" / "graphql" => ZHttpAdapter.makeWebSocketService(apiInterpreter)
      }.withDefaultErrorResponse
      server <- Server.serve(httpApp) <* ZIO.logInfo("GraphQL service running.")
    yield server

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j

  override def run: ZIO[Any, Throwable, Nothing] =
    app.provide(
      Server.default,
      BoardRepository.layer,
      BoardService.layer,
      CategoryRepository.layer,
      CategoryService.layer,
      DatabaseService.layer,
      PostRepository.layer,
      PostService.layer,
      TopicRepository.layer,
      TopicService.layer
    )
}
