package io.ehwaz.board

import doobie.*
import doobie.implicits.*
import io.ehwaz.external.DatabaseService
import io.ehwaz.{EhwazIO, EhwazTask}
import zio.*
import zio.interop.catz.*

case class CategoryRepository(databaseService: DatabaseService):
  def findAll(): EhwazIO[Any, List[CategoryRow]] =
    sql"select * from categories"
      .query[CategoryRow]
      .to[List]
      .transact(databaseService.tx)
      .orDie

  def findById(id: CategoryId): EhwazIO[Any, Option[CategoryRow]] =
    sql"select * from categories where id = ${id.toString}"
      .query[CategoryRow]
      .option
      .transact(databaseService.tx)
      .orDie

  def create(category: CategoryRow): EhwazIO[Any, CategoryRow] =
    sql"""
      insert into categories (id, name, description)
      values (${category.id}, ${category.name}, ${category.description})
    """
      .update
      .run
      .map(_ => category)
      .transact(databaseService.tx)
      .tapError(e => ZIO.logError(s"CategoryRepository SQL error: ${e.getMessage}"))
      .orDie

  def delete(id: CategoryId): EhwazIO[Any, Int] =
    sql"delete from categories where id=${id.toString}"
      .update
      .run
      .transact(databaseService.tx)
      .orDie

object CategoryRepository:
  val layer: ZLayer[DatabaseService, Throwable, CategoryRepository] =
    ZLayer.fromFunction(CategoryRepository.apply _)