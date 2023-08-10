package io.ehwaz.discussion

import caliban.RootResolver
import io.ehwaz.{EhwazTask, NotFoundError}
import io.ehwaz.users.ProfileId
import zio.*

final case class PostService(
  postRepository: PostRepository,
  topicRepository: TopicRepository
):
  import io.ehwaz.apispec.PostSchema.*

  def resolver =
    ZIO.succeed {
      RootResolver(
        Option.empty[Unit],
        Mutations(
          addPost
        )
      )
    }

  def addPost(args: AddPostArgs): EhwazTask[AddPostResult] =
    for
      topicRow <- topicRepository.findById(args.input.topicId)
        .someOrFail(NotFoundError(args.input.topicId.toString, "topic"))
      postUUID <- Random.nextUUID
      addedPost <- postRepository.create(
          PostRow(
            id = PostId(postUUID),
            content = args.input.content,
            authorProfileId = ProfileId.fromString("24c4ecbe-6a63-4de0-9838-e01daa37ae77"), // FIXME unhardcode
            topicId = topicRow.get.id
          )
      )
    yield AddPostResult(addedPost.id)

object PostService:
  val layer: ZLayer[PostRepository with TopicRepository, Throwable, PostService] =
    ZLayer.fromFunction(PostService.apply _)