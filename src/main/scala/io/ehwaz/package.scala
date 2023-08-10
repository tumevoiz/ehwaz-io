package io

import zio.{IO, ZIO}

import java.util.UUID
import caliban.schema.Schema
import caliban.CalibanError.ExecutionError
import caliban.schema.Schema.auto.*

package object ehwaz:
  sealed trait EhwazError:
    def message: String

  sealed trait EhwazApiError extends EhwazError

  given eaioErrorSchema[A](using s: Schema[Any, A]): Schema[Any, EhwazTask[A]] =
    Schema.customErrorEffectSchema {
      case ae @ AuthenticationError() => ExecutionError(ae.message)
      case e @ NotFoundError(_, _, _) => ExecutionError(e.message)
      case db @ DatabaseError(_) => ExecutionError("An database error has been encountered.")
      case ee @ RegistrationError(_) => ExecutionError(ee.message)
      case GenericEhwazError(message) => ExecutionError(message)
      case _ => ExecutionError("An application encountered an error.")
    }

  final case class NotFoundError(resourceId: String, resourceType: String, additionalMessage: Option[String] = None) extends EhwazError:
    override def message: String =
      s"Requested \"${resourceType}\" resource with ID \"${resourceId}\" not found." + additionalMessage.getOrElse("")

  final case class AuthenticationError() extends EhwazError:
    override def message: String = "Authentication failure. Please provide valid credentials."

  final case class DatabaseError(message: String) extends EhwazError
  final case class RegistrationError(message: String) extends EhwazError

  final case class GenericEhwazError(message: String) extends EhwazError

  type EhwazIO[R, A] = ZIO[R, EhwazError, A]
  type EhwazTask[A] = IO[EhwazError, A]
