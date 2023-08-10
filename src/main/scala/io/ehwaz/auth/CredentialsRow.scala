package io.ehwaz.auth

import caliban.schema.*
import doobie.*
import io.ehwaz.users.ProfileId

final case class CredentialsRow(password: SecurePassword, email: String, profileId: ProfileId)

opaque type SecurePassword = String
//object EncryptedPassword:
//  def apply(password: String): EncryptedPassword = password
//
//  given idGet: Get[EncryptedPassword] = Get[String].map(EncryptedPassword.apply)
//  given idPut: Put[EncryptedPassword] = Put[String].contramap(identity)

opaque type PlaintextPassword = String
object PlaintextPassword:
  given schema: Schema[Any, PlaintextPassword] = Schema.stringSchema
  given argBuilder: ArgBuilder[PlaintextPassword] = ArgBuilder.string