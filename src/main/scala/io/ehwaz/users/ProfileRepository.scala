package io.ehwaz.users

import doobie.*
import doobie.implicits.*
import io.ehwaz.EhwazTask
import io.ehwaz.external.DatabaseService
import zio.*
import zio.interop.catz.*

final case class ProfileRepository(databaseService: DatabaseService):
  def findById(profileId: ProfileId): EhwazTask[Option[ProfileRow]] =
    sql"select * from profiles where id=${profileId}"
      .query[ProfileRow]
      .option
      .transact(databaseService.tx)
      .orDie

  def create(profile: ProfileRow): EhwazTask[ProfileRow] =
    sql"""
         insert into profiles (id, username, birthday, title, primary_group_id)
         values (${profile.id}, ${profile.username}, ${profile.birthday}, ${profile.title}, ${profile.primaryGroupId.toString})
       """
      .update
      .run
      .map(_ => profile)
      .transact(databaseService.tx)
      .tapError(e => ZIO.logError(s"TopicRepository SQL error: ${e.getMessage}"))
      .orDie