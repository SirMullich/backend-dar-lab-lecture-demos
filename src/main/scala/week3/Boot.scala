package week3

import week2.{Developer, Human, Student, Worker}

object Boot extends App {


  val intList = IntListNode(4, IntListNode(10, IntListNode(6, IntListEnd)))


  // Let's move this to IntList
  def printList(list: IntList): Unit = list match {
    case IntListNode(data, tail) =>
      print(s"$data ")
      printList(tail)
    case IntListEnd =>
      println()
  }

  intList.printList()

  printList(intList)

  println(intList.sum())
  println(intList.sumTailRecursive())

  val studentList: GenericList[Student] = GenericListNode(Student("Ivan", "123"),
    GenericListNode(Student("Makpal", "156"),
      GenericListNode(Student("Magripa", "987"),
        GenericListEnd())))

  println(studentList.toString())

  val humanList: GenericList[Human] = GenericListNode(Student("Ivan", "123"),
    GenericListNode(Worker("Sailaubek", 123),
      GenericListNode(week2.Developer("Amantai", "developer"),
        GenericListEnd())))

  println(humanList.toString())

  def studentPrint(list: GenericList[Student]): Unit = {
    println(list.toString())
  }

  studentPrint(studentList)
//  studentPrint(humanList) // error


  def humanPrint(human: Human): Unit = {
    println(human)
  }

  humanPrint(Worker("Aristarkh", 87))
  humanPrint(Student("Yessey", "0987"))

  def humanListPrint(list: GenericList[Human]): Unit = {
    println(list.toStringHuman)
  }

  humanListPrint(humanList)
  humanListPrint(studentList)

  // does NOT compile
//  val intGenericList: GenericList[Int] = GenericListNode(4, GenericListNode(5, GenericListEnd()))



  // ---------------- Wednesday ------------------

  // implicit


  implicit val config: SuperConfig = SuperConfig("root:root", "10.8.5.100:8764", "developer,23458fuafsdf198435")

  def dbSetup(name: String, config: SuperConfig) = {

  }

  def storageSetup(storage: Int, config: SuperConfig) = {

  }

  def authSetup(provider: String, config: SuperConfig) = {

  }

  def newMethod(a: String, b: String, config: SuperConfig) = {

  }

  // method with implicit parameter
  def showMethod(name: String)(implicit conf: SuperConfig) = {
    conf.connectionSetting
  }

  // no need for implicit parameters list
  showMethod("blabla")

  // explicit
  showMethod("bla-bla-bla")(SuperConfig("ho", "hooho", "hehe"))


  // DOES NOT COMPILE -- ambiguous implicit SuperConfig
//  implicit val secondConfig: SuperConfig = SuperConfig("asdf", "1345", "piasdjf")


  // bad practice
  implicit val implicitString = "implicit"


  // bad practice (for now, until we are Scala Gurus) -- implicit Conversions

  implicit def integerStringMultiply(integer: Int): Double = {
    (integer * 100).toDouble
  }

  implicit def doubleStringAdd(double: Double): String = {
    (double + 600).toString
  }

  def needsString(str: String): Unit = {
    println(str)
  }

  needsString(60.0)

  // explicit conversion -- much more readable
  needsString(doubleStringAdd(60.0))



  println(intList.lastNode.data)

  intList.map(x => x * 10).printList()

  // in Java or C#
  // intList.map(x => x * 10)  // return void
  // student.setName("Ivan")

  // Collections

  val seq: Seq[Int] = Seq(1, 2, 3, 4, 5, 6)

  val mappedSeq = seq.map(x => x * 10)

  mappedSeq.foreach { number =>
    print(s"$number ")
  }

  println()

  seq.map(x => x * 2).map(x => x + 6).map(x => x / 2).foreach(x => print(s"$x "))

  // filter

  println()
  seq.filter(x => x % 2 == 0).foreach(println(_))

  println("Primes: ")
  val isPrime: Int => Boolean = (x: Int) => {
//    (1 to x).count(num => x % num == 0) == 2
//    (2 until x).filter(num => x % num == 0).isEmpty
    !(2 until x).exists(num => x % num == 0) && x != 1
  }


  seq.filter(isPrime).foreach(println)

  if (seq.exists(x => x == 6)) println("6 is in the sequence")
  if (seq.contains(6)) println("6 is in the sequence")

  // reverse
  seq.reverse.foreach(x => print(s"$x "))
  println()

  // sum
  println(seq.sum)


  val workers = List(Worker("Vlad", 12), Worker("Oleg", 81), Worker("Anna", 15), Worker("Erzhan", 10))

  // initial acc = 0
  val workersSum = workers.foldLeft(0)((acc, worker) => acc + worker.work)
  println(s"workerSum: $workersSum")


  val superSeq: Seq[Seq[Int]] = seq.map(x => Seq(x * 2, x + 2))

  println(superSeq)

  val flattennedSeq: Seq[Int] = superSeq.flatten
  println(superSeq.flatten)

//  val doubleFlattened = flattennedSeq.flatten
//  println(superSeq.flatten.flatten)


}
