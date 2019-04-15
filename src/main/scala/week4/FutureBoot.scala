package week4

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object FutureBoot extends App {

  def add(x: Int, y: Int): Future[Int] = {
    Future {
      Thread.sleep(5000)
      x + y
    }
  }

  def divide(x: Int, y: Int) = Future(x / y)

  def multiply(x: Int, y: Int) = Future {
    Thread.sleep(2000)
    x * y
  }
  def subtract(x: Int, y: Int) = Future{
    println(s"Subtracting $y from $x")
    x - y
  }


  println("Add started...")
  add(20, 30).onComplete {
    case Success(value) => println(s"Result of future add: $value")
    case Failure(fail) => println(s"Failed to add: ${fail.getMessage}")
  }

  println("After add...")

  // stop and wait (BLOCKING)
//  println(Await.result(multiply(10, 2), 20.seconds))



  add(40, 20).flatMap { result1 =>
    multiply(result1, 2).flatMap { result2 =>
      divide(result2, 0).flatMap { result3 =>
        subtract(result3, 4)
      }
    }
  }.onComplete {
    case Success(value) => println(s"Complex operation succeded: $value")
    case Failure(fail) => println(s"Complex operation failed: ${fail.getMessage}")
  }


  Thread.sleep(10000)
  println("Program finished")

}
