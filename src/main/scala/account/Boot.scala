package account

import account.service.Bot
import akka.actor.{ActorLogging, ActorSystem, Props}

import scala.util.{Failure, Success}

object Boot extends App {

  // actor system
  val system = ActorSystem()

  // create Bot actor
  // val bot = new Bot()         // WRONG, IS NOT ACTOR
  val bot = system.actorOf(Props[Bot], "bot") // creates Bot actor


  // for Future
//  implicit val ec = system.dispatcher
//
//  scala.io.StdIn.readLine()
//  system.terminate().onComplete {
//    case Success(value) =>
//      println("system shutdown")
//    case Failure(exception) =>
//      println(exception.getMessage)
//  }
}
