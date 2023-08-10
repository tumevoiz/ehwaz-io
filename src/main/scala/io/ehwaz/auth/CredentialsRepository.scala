package io.ehwaz.auth

import doobie.*
import doobie.implicits.*

import io.ehwaz.EhwazTask
import io.ehwaz.external.DatabaseService

import zio.*
import zio.interop.catz.*

final case class CredentialsRepository(databaseService: DatabaseService):
  def findByEmail(email: String): EhwazTask[Option[CredentialsRow]] =
    sql"select * from credentials where email=${email}"
      .query[CredentialsRow]
      .option
      .transact(databaseService.tx)
      .orDie

  def create(credentials: CredentialsRow): EhwazTask[CredentialsRow] =
    sql"""
         insert into credentials (email, password, profile_id)
         values (${credentials.email}, ${credentials.password}, ${credentials.profileId.toString})
       """
      .update
      .run
      .map(_ => credentials)
      .transact(databaseService.tx)
      .orDie
