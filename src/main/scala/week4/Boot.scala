package week4

import scala.util.{Success, Try, Failure}

object Boot extends App {

  def divideOpt(operand1: Int, operand2: Int): Option[Int] = {
    println(s"Dividing $operand1 by $operand2")
    operand2 match {
      case 0 => None
      case num: Int => Some(operand1 / operand2)
    }
  }

  println(divideOpt(4, 2))
  println(divideOpt(4, 0))
  println(divideOpt(-10, 3))

  val opt1: Option[String] = Some("Hello there")
  val opt2: Option[String] = None

  // pattern matching
  opt2 match {
    case Some(value) => println(s"opt2 has value: $value")
    case None => println("opt2 is empty")
  }

  // get or else
  println(opt2.getOrElse("This is default if empty"))
  println(opt1.getOrElse("This is default if empty"))

  println("---- get (NOT recommended) -------")
  // get (NOT recommended)
  println(opt1.get)

  if (opt2.isDefined) {
    println(opt2.get)
  }

  def addExcept100(operand1: Int, operand2: Int): Option[Int] = {
    println(s"Adding $operand1 by $operand2")
    if (operand1 == 100 || operand2 == 100) None
    else Some(operand1 + operand2)
  }

  def positiveDiff(operand1: Int, operand2: Int): Option[Int] = {
    println(s"Subtracting $operand1 by $operand2")
    val result = operand1 - operand2
    if (result < 0) None
    else Some(result)
  }

  opt1.foreach(x => println(s"Option foreach: $x"))

  opt1.filter(x => x == "my string").foreach(println)

  def calculate(operand1: Int, operand2: Int): Option[Int] =
    addExcept100(operand1, operand2).flatMap { opt1Result =>
    positiveDiff(opt1Result, 80).flatMap { opt2Result =>
      divideOpt(opt2Result, 5)
    }
  }

//  opt2Result: Int =>
//    divideOpt(opt2Result, 5): Option[Int]

  println(calculate(80, 20))  // Some(4)
  println(calculate(30, 20))  // None

  val weirdList: List[List[Option[Int]]] = List(
    List(Some(2), None, Some(5)),
    List(None, None, Some(9)),
    List(Some(10), Some(11), Some(12)))

  println("----------- Weird list -----------")
  println(weirdList.flatten)
  println(weirdList.flatten.flatten)






  // --------------- TRY ------------------/
  println("// --------------- TRY ------------------/")


  def divideTryClassic(operand1: Int, operand2: Int): Int = {
    try {
      operand1 / operand2
    } catch {
      case e: Throwable => 0
    }
  }

  def divideTry(operand1: Int, operand2: Int): Try[Int] =
    Try(operand1 / operand2)


  divideTry(10, 10) match {
    case Success(value) => println(s"Successful division: $value")
    case Failure(exception) => println(s"Failed to divide: ${exception.getMessage}")
  }

  divideTry(10, 0) match {
    case Success(value) => println(s"Successful division: $value")
    case Failure(exception) => println(s"Failed to divide: ${exception.getMessage}")
  }

  def divideTryClassicWeird(operand1: Int, operand2: Int): Int = {
    try {
      val res = operand1 / operand2

      try {
        res + 100
      } catch {
        case e: Exception => -1
      }
    } catch {
      case e: Throwable => 0
    }
  }


  // ---------------- Either ------------------

  def addExcept100Either(operand1: Int, operand2: Int): Either[String, Int] = {
    println(s"Adding $operand1 by $operand2")
    if (operand1 == 100 || operand2 == 100) Left("I don't like 100")
    else Right(operand1 + operand2)
  }

  def positiveDiffEither(operand1: Int, operand2: Int): Either[String, Int] = {
    println(s"Subtracting $operand1 by $operand2")
    val result = operand1 - operand2
    if (result < 0) Left("I don't like negative")
    else Right(result)
  }


  val eitherResult = for {
    result1 <- addExcept100Either(70, 80)
    result2 <- positiveDiffEither(result1, 200)
  } yield result2

  eitherResult match {
    case Right(value) => println(s"Either result: $value")
    case Left(error) => println(s"Error encountered: $error")
  }

  println("---------------- Monad conversions --------------")
  println(eitherResult.toOption)
  println(eitherResult.toSeq)
  println(positiveDiffEither(100, 1).toSeq)
}
