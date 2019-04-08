package week2

object Boot {

  def main(args: Array[String]): Unit = {
    // Tuples

    val tuple1: (Int, Int, Int) = (1, 2, 5)

    println(tuple1._1) // 1
    println(tuple1._3) // 5

    trait A
    trait B extends A
    trait C

    case class D() extends C with B // D is C, B, A

    // Higher order functions

    def add(x: Int, y: Int): Int = x + y
    val subtract: (Int, Int) => Int = (x: Int, y: Int) => x - y
    val multiply = (x: Int, y: Int) => x * y
    def divide(x: Int, y: Int) = x / y

    // higher-order
    def calculate(x: Int, y: Int)(operation: (Int, Int) => Int) = {
      // apply operation to x and y
      operation(x, y) // return
    }

    def test(f: () => Unit) = {
      // do some stuff
      f()
    }

    println(calculate(3, 4)(add))
    println(calculate(3, 4)(multiply))
    println(calculate(3, 4)(divide))




    // Nested methods
    def factorial(x: Int): Int = {
      def fact(x: Int, accumulator: Int): Int = {
        if (x <= 1) accumulator
        else fact(x - 1, x * accumulator)
      }
      fact(x, 1)
    }

    factorial(5)
    // factorial.fact is not accessible



    // Case classes
    case class Account(id: String, balance: Double)

    // mutable variable
    var ivanAccount = Account("1", 2000)
    var kaisarAccount = Account("2", 5000)

    // add 1000
//    ivanAccount = Account("1", 2000 + 1000) // not good

//    ivanAccount = ivanAccount.copy(balance = 2000 + 1000) // not so good
    ivanAccount = ivanAccount.copy(balance = ivanAccount.balance + 1000)
    println(ivanAccount.balance)

    // Disclaimer: functional programming is USUALLY slower than procedural programming


    // Pattern matching: better switch

    def divide2(x: Int, y: Int) = {
      y match {
        case 0 => 0 // instead of Infinity
        case num: Int => x / num
      }
    }

//    case class Taxist(name: String) extends Human      // error because Human is SEALED

    val student = Student("bla", "2")
    val worker = Worker("bla-bla", 9)
    val developer = Developer("developer", "developer")
    val developer2 = Developer("developer", "junior")

    def greetHuman(human: Human) = human match {
      case Student(name, id) => println(s"Hi Student: $name")
      case worker: Worker => println(s"Hi Worker: ${worker.name}")

//      case worker @ Worker => println("")
      case Developer(name, position) if position == "developer" =>
        println("Hi Developer with position developer")

      case Developer(name, _) => println(s"Hi Developer: $name")

      case any: Any => println(s"Unexpected input: $any")
    }

    greetHuman(student)
    greetHuman(worker)
    greetHuman(developer)
    greetHuman(developer2)

    lazy val example = 9

    // Companion Objects

    println(Car.newer(new Car("Camry", 2010), new Car("Mustang", 2000)))

    println(Car.apply("Nexia", 2018))
    println(Car("Nexia", 2018))

    val car1 = Car("Camry", 2010)
    val car2 = Car("Nexia", 2018)

    println(Car.unapply(car1))


    var count: Int = 0

    for (i <- 1 to 10) {
      count = count + i
    }
    println(count)

    val list = List(1, 2, 3, 4, 6, 7)

    val temp = for {
      i <- list
    } yield 5 * i

    println(temp)









  }

}
