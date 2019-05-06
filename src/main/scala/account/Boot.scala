package account

import account.model.AccountState
import account.service.{Account, Bot}
import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.slf4j.LoggerFactory
import akka.pattern.ask
import akka.util.Timeout
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Boot extends App {

  // actor system
  implicit val system = ActorSystem()

  val log = LoggerFactory.getLogger("Boot")

  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val timeout: Timeout = Timeout(5.seconds)

  // create Bot actor
  // val bot = new Bot()         // WRONG, IS NOT ACTOR
  val bot = system.actorOf(Props[Bot], "bot") // creates Bot actor

  val id = "test-123"
  val accActor = system.actorOf(Account.props(id), id)


  // JSON support
  implicit val acceptedFormat = jsonFormat2(Account.Accepted)
  implicit val createAccountFormat = jsonFormat2(Account.CreateAccount)
  implicit val accountStateFormat = jsonFormat2(AccountState.apply)


  // DSL - domain specific language
  val route =
    path("healthcheck") {
      get {
        complete("OK")
      }
    } ~
    pathPrefix("account") {
      pathEndOrSingleSlash {
        post {
          entity(as[Account.CreateAccount]) { cmd =>
            complete {
              (accActor ? cmd).mapTo[Account.Accepted]
            }
          }
        } ~
        get {
          parameter("id".as[String]) { accountId =>
            complete {
              (accActor ? Account.GetLogin).mapTo[AccountState]
            }
          }
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  log.info(s"Server online at http://localhost:8080/ ")

  // NOT NEEDED as we don't shutdown server
//  bindingFuture
//    .flatMap(_.unbind()) // trigger unbinding from the port
//    .onComplete(_ => system.terminate()) // and shutdown when done

}
