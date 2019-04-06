package week1

object Boot extends App with Speaker {
  println("Hello world!")

  // Compile time vs Runtime

//  val string: Int = "asdf"   // compile time


  // method divide
  def divide(x: Int, y:Int): Int = x / y

  val input = Console.readInt()
  val input3: String = "qwerqwer"

  val input2: Int = Console.readInt()

  println(divide(input, input2))  // Runtime error if input2 = 0




  // values
  // input = 56    // error because input is immutable
  // input = input + 1 // immutable


  var variable = 0

  variable = variable + 10 // MUTABLE




  // FUNCTIONS

  val add: (Int, Int) => Int = (x: Int, y: Int) => x + y

  println(add(8, 7))


  // multiple parameter list
  def addThenMultiply(x: Int, y: Int)(multiplier: Int) = {
    multiplier * (x + y)
  }



  // Classes
  class Greeter(prefix: String, suffix: String, surname: String = "Ivanov") {
    def greet(name: String): Unit =         // void
      println(prefix + name + suffix)

//    def setPrefix(pref: String): Unit = {
//      prefix = pref
//    }
  }

  val greeter = new Greeter("heh", "oho")
  val greeter2 = new Greeter("heh", "oho")

  println(s"Two Greeters are: ${greeter == greeter2}")

  // not accessible because Class
  // greeter.prefix

  println(greeter)

  // Case class
  // does not need 'new' keyword
  case class GreeterCase(prefix: String, suffix: String, surname: String = "Ivanov") {
    def greet(name: String): Unit =         // void
      println(prefix + name + suffix)

    //    def setPrefix(pref: String): Unit = {
    //      prefix = pref
    //    }
  }

  val greeterCase = GreeterCase("asfd", "poqwer")
  val greeterCase2 = GreeterCase("asfd", "poqwer")

  // accessible because case class
  greeterCase.prefix

  println(s"Two GreeterCases are: ${greeterCase == greeterCase2}")

  println(greeterCase)



  // Traits

  println(speak("Ivan"))

  // overriding abstract method
  override def walk(name: String) = s"$name is walking"

  println(walk("Ivan"))






  // Boxing and Unboxing
  def fastAlgo(num: java.lang.Object): java.lang.Integer = {

    // type cast (unbox)
    num.asInstanceOf[java.lang.Integer] + 1
  }

  println(fastAlgo(new java.lang.Integer(5)))








  // Unified types
  val list: List[Any] = List(
    "a string",
    732,  // an integer
    'c',  // a character
    true, // a boolean value
    () => "an anonymous function returning a string"
  )

  list.foreach(element => println(element))


  def typeCasting1(num: Double) = num + 10
  def typeCasting2(num: Int) = num + 10

  println(typeCasting1(3.toShort))
//  println(typeCasting2(4.5D))  // Error

}
