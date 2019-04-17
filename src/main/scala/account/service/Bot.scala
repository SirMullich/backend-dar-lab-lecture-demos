package account.service

import akka.actor.{Actor, ActorLogging, Props}
import scala.concurrent.duration._

object Bot {
  case object Tick
}

class Bot extends Actor with ActorLogging {
  import Bot._

  log.debug("Bot created")

  import context.dispatcher
  context.system.scheduler.schedule(10.seconds, 5.seconds, self, Tick)

  // create Account actor
  val account = context.actorOf(Props[Account], "account")

  account ! Account.GetLogin

  override def receive: Receive = {
    case login: String =>
      log.info(s"I'm bot, just received login: $login")
      log.info(s"Sending Crash to account now, he-he")
      account ! Account.Crash

    case Tick =>
      log.info("Tack")
  }

}
