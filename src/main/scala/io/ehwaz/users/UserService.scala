package io.ehwaz.users

import caliban.RootResolver
import io.ehwaz.{AuthenticationError, EhwazTask, RegistrationError}
import io.ehwaz.auth.{CredentialsRepository, CredentialsRow, SecurePassword}
import io.github.nremond.PBKDF2
import io.github.nremond.SecureHash
import zio.*

final case class UserService(credentialsRepository: CredentialsRepository, profileRepository: ProfileRepository):
  import io.ehwaz.apispec.UserSchema.*

  def resolver = ZIO.succeed {
    RootResolver(
      Option.empty[Unit],
      Mutations(
        register,
        authenticate
      )
    )
  }

  def register(args: RegistrationArgs): EhwazTask[RegistrationResult] =
    for
      profileUUID <- Random.nextUUID
      // TODO validate if username is free
      newProfile <- profileRepository.create(
        ProfileRow(
          id = ProfileId(profileUUID),
          username = args.input.username,
          birthday = None,
          title = Some("Newbie"), // TODO add ability to configure default title
          // TODO add ability to choose the default group & unhardcode
          primaryGroupId = GroupId.fromString("1c55a568-19f5-498a-a9b6-37ebd7ad633c")
        )
      )
      securePassword <- ZIO.attempt(SecureHash.createHash(args.input.password.toString))
      _ <- ZIO.when(
        securePassword == args.input.password.toString
      )(ZIO.fail(RegistrationError("Please try again."))) // Quick validation of password hashing
      _ <- credentialsRepository.create(
        CredentialsRow(
          password = SecurePassword(securePassword),
          email = args.input.email,
          profileId = newProfile.id
        )
      )
      // TODO check if either profile and credentials were created
      // TODO [future] send activation link
    yield RegistrationResult("Created!")

  def authenticate(args: AuthenticationArgs): EhwazTask[AuthenticationResult] =
    for
      credentials <- credentialsRepository.findByEmail(args.input.email).someOrFail(AuthenticationError())
      authenticated <- ZIO.attempt(SecureHash.validatePassword(args.input.password, credentials))
      _ <- ZIO.when(!authenticated)(ZIO.fail(AuthenticationResult()))
      // TODO generate jwt token and pass it
      profile <- profileRepository.findById(credentials.profileId).flatMap(row =>
        Profile(
          id = row.id,
          username = row.username,
          birthday = row.birthday,
          title = row.title,
          primaryGroupId = row.primaryGroupId
        )
      )
    yield AuthenticationResult(profile)