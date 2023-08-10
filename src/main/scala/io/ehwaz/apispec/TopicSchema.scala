package io.ehwaz.apispec

import caliban.schema.*
import io.ehwaz.EhwazTask
import io.ehwaz.apispec.PostSchema.Post
import io.ehwaz.board.BoardId
import io.ehwaz.discussion.TopicId

object TopicSchema:
  final case class Topic(id: TopicId, name: String, posts: List[Post])
  object Topic:
    given schema: Schema[Any, Topic] = Schema.gen
    given argBuilder: ArgBuilder[Topic] = ArgBuilder.gen

  final case class TopicArgs(boardId: BoardId)
  object TopicArgs:
    given argBuilder: ArgBuilder[TopicArgs] = ArgBuilder.gen

  final case class AddTopicInput(name: String, content: String, boardId: BoardId)
  object AddTopicInput:
    given schema: Schema[Any, AddTopicInput] = Schema.gen
    given argBuilder: ArgBuilder[AddTopicInput] = ArgBuilder.gen

  final case class AddTopicArgs(input: AddTopicInput)
  object AddTopicArgs:
    given schema: Schema[Any, AddTopicArgs] = Schema.gen
    given argBuilder: ArgBuilder[AddTopicArgs] = ArgBuilder.gen

  final case class AddTopicResult(id: TopicId)
  object AddTopicResult:
    given schema: Schema[Any, AddTopicResult] = Schema.gen
    given argBuilder: ArgBuilder[AddTopicResult] = ArgBuilder.gen

  case class Queries(
    topics: TopicArgs => EhwazTask[List[Topic]]
  )

  case class Mutations(
    addTopic: AddTopicArgs => EhwazTask[AddTopicResult]
  )

