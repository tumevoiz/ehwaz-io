package io.ehwaz.discussion

import caliban.RootResolver
import io.ehwaz.{EhwazTask, NotFoundError}
import io.ehwaz.apispec.PostSchema.Post
import io.ehwaz.board.{BoardId, BoardRepository}
import io.ehwaz.users.ProfileId
import zio.*

final case class TopicService(
  boardRepository: BoardRepository,
  postRepository: PostRepository,
  topicRepository: TopicRepository
):
  import io.ehwaz.apispec.TopicSchema.*

  def resolver =
    ZIO.succeed {
      RootResolver(
        Queries(
          findBoards
        ),
        Mutations(
          addTopic
        )
      )
    }

  def findBoards(topicArgs: TopicArgs): EhwazTask[List[Topic]] =
    findByBoardId(topicArgs.boardId)

  def findByBoardId(boardId: BoardId): EhwazTask[List[Topic]] =
    for
      topics <- topicRepository.findByBoardId(boardId)
      topicsWithPosts <- ZIO.collectAll(topics.map { topic =>
        for
          posts <- postRepository.findByTopicId(topic.id)
        yield (topic, posts)
      })
    yield topicsWithPosts.map { case (topic: TopicRow, posts: List[PostRow]) =>
      Topic(
        id = topic.id,
        name = topic.name,
        posts = posts.map(p => Post(
          id = p.id,
          content = p.content
        ))
      )
    }

  def addTopic(args: AddTopicArgs): EhwazTask[AddTopicResult] =
    for
      board <- boardRepository.findById(args.input.boardId)
        .someOrFail(NotFoundError(args.input.boardId.toString, "board"))
      topicUUID <- Random.nextUUID
      postUUID <- Random.nextUUID
      addedTopic <- topicRepository.create(
          TopicRow(
            id = TopicId(topicUUID),
            name = args.input.name,
            boardId = board.id
          )
        )
      // TODO confirm that topic is created. if not then rollback. maybe saga?
      _ <- postRepository.create(
        PostRow(
          id = PostId(postUUID),
          content = args.input.content,
          authorProfileId = ProfileId.fromString("24c4ecbe-6a63-4de0-9838-e01daa37ae77"), // FIXME unhardcode
          topicId = addedTopic.id
        )
      )
    yield AddTopicResult(addedTopic.id)

object TopicService:
  val layer: ZLayer[BoardRepository with PostRepository with TopicRepository, Throwable, TopicService] =
    ZLayer.fromFunction(TopicService.apply _)