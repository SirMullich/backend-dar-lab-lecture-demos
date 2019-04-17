package account.service

import account.model.AccountState
import akka.actor.{Actor, ActorLogging}

object Account {
  case object GetLogin
  case object Crash
}

class Account extends Actor with ActorLogging {
  import Account._

  log.debug("Account created")

  // constructor
  val state: AccountState = AccountState.empty()

  // Any => Unit
  override def receive: Receive = {
    case GetLogin =>
      log.info("Received GetLogin")

      // отправитель текущего сообщения
      sender() ! state.login

    case Crash =>
      log.warning("Received Crash... Crashing...")
      40 / 0
  }
}
