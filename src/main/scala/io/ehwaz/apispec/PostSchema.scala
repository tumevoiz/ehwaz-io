package io.ehwaz.apispec

import caliban.schema.*
import io.ehwaz.EhwazTask
import io.ehwaz.discussion.{PostId, TopicId}

object PostSchema:
  final case class Post(id: PostId, content: String)
  object Post:
    given schema: Schema[Any, Post] = Schema.gen
    given argBuilder: ArgBuilder[Post] = ArgBuilder.gen

  final case class AddPostInput(content: String, topicId: TopicId)
  object AddPostInput:
    given schema: Schema[Any, AddPostInput] = Schema.gen
    given argBuilder: ArgBuilder[AddPostInput] = ArgBuilder.gen

  final case class AddPostArgs(input: AddPostInput)
  object AddPostArgs:
    given schema: Schema[Any, AddPostArgs] = Schema.gen
    given argBuilder: ArgBuilder[AddPostArgs] = ArgBuilder.gen

  final case class AddPostResult(id: PostId)
  object AddPostResult:
    given schema: Schema[Any, AddPostResult] = Schema.gen
    given argBuilder: ArgBuilder[AddPostResult] = ArgBuilder.gen


  case class Mutations(
    addPost: AddPostArgs => EhwazTask[AddPostResult]
  )