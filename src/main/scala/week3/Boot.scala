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
      GenericListNode(Developer("Amantai", "developer"),
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


}
