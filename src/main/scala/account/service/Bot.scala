package account.service

import account.model.AccountState
import akka.actor.{Actor, ActorLogging, Props}

import scala.concurrent.duration._

object Bot {
  case object Tick
}

class Bot extends Actor with ActorLogging {
  import Bot._

  log.debug("Bot created")

  import context.dispatcher
//  context.system.scheduler.schedule(10.seconds, 5.seconds, self, Tick)

  // create Account actor

  val account = context.actorOf(Account.props("account-1"), "account-1")

  account ! Account.GetLogin
  account ! Account.CreateAccount("ivan@mail.ru", "password")
  account ! Account.GetLogin

  override def receive: Receive = {
    case AccountState(email, password) =>
      log.info(s"Received AccountState with password: $password")

    case Account.ErrorResponse(msg) =>
      log.warning(msg)

    case Tick =>
      log.info("Tack")
  }

}
