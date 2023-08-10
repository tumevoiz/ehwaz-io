package io.ehwaz.apispec

import io.ehwaz.EhwazTask
import io.ehwaz.auth.PlaintextPassword
import io.ehwaz.users.{GroupId, ProfileId}
import caliban.schema.*

object UserSchema:
  final case class Profile(
    id: ProfileId,
    username: String,
    birthday: Option[String],
    title: Option[String],
    primaryGroupId: GroupId
  )

  object Profile:
    given schema: Schema[Any, Profile] = Schema.gen
    given argBuilder: ArgBuilder[Profile] = ArgBuilder.gen

  final case class RegistrationInput(
    username: String,
    password: PlaintextPassword,
    email: String
  )

  object RegistrationInput:
    given schema: Schema[Any, RegistrationInput] = Schema.gen
    given argBuilder: ArgBuilder[RegistrationInput] = ArgBuilder.gen

  final case class RegistrationArgs(input: RegistrationInput)
  object RegistrationArgs:
    given schema: Schema[Any, RegistrationArgs] = Schema.gen
    given argBuilder: ArgBuilder[RegistrationArgs] = ArgBuilder.gen

  final case class RegistrationResult(message: String)
  object RegistrationResult:
    given schema: Schema[Any, RegistrationResult] = Schema.gen
    given argBuilder: ArgBuilder[RegistrationResult] = ArgBuilder.gen

  final case class AuthenticationInput(
    email: String,
    password: PlaintextPassword
  )
  object AuthenticationInput:
    given schema: Schema[Any, AuthenticationInput] = Schema.gen
    given argBuilder: ArgBuilder[AuthenticationInput] = ArgBuilder.gen

  final case class AuthenticationArgs(input: AuthenticationInput)
  object AuthenticationArgs:
    given schema: Schema[Any, AuthenticationArgs] = Schema.gen
    given argBuilder: ArgBuilder[AuthenticationArgs] = ArgBuilder.gen

  final case class AuthenticationResult(profile: Profile)
  object AuthenticationResult:
    given schema: Schema[Any, AuthenticationResult] = Schema.gen
    given argBuilder: ArgBuilder[AuthenticationResult] = ArgBuilder.gen

  final case class Mutations(
    register: RegistrationArgs => EhwazTask[RegistrationResult],
    authenticate: AuthenticationArgs => EhwazTask[AuthenticationResult]
  )