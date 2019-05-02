package account.service

import account.model.AccountState
import akka.actor.{Actor, ActorLogging, Props}

object Account {
  case object GetLogin
  case object Block
  case class CreateAccount(email: String, password: String)
  case class ChangePassword(oldPassword: String, newPassword: String)
  case class ErrorResponse(message: String)

  def props(accountId: String) = Props(new Account(accountId))
}

class Account(accountId: String) extends Actor with ActorLogging {
  import Account._

  // constructor
  var state: AccountState = AccountState.empty()

  // Any => Unit
  override def receive: Receive = neww

  def neww: Receive = {
    case CreateAccount(email, password) =>
      log.debug(s"Received CreateAccount: $password")
      state = AccountState(email, password)
      context.become(active)

    case GetLogin =>
      sender() ! ErrorResponse("Cannot handle such request while in NEW state")
  }

  def active: Receive = {
    case GetLogin =>
      log.info("Received GetLogin")

    // отправитель текущего сообщения
      sender() ! state

    case ChangePassword(oldPassword, newPassword) =>
      log.debug(s"Received ChangePassword with oldPassword: $oldPassword and newPassword: $newPassword")

    case Block =>
      log.info("Blocking current account")
  }

  def blocked: Receive = {
    case GetLogin =>
      sender() ! ErrorResponse("Current account is BLOCKED")

    case _: ChangePassword =>
      sender() ! ErrorResponse("Cannot change password of BLOCKED account")

    case any: Any =>
      sender() ! ErrorResponse(s"Cannot handle: $any while in BLOCKED state")
  }

  override def unhandled(message: Any): Unit = {
    log.warning(s"Message $message was unhandled")
  }
}
