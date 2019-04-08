package week3

sealed trait IntList {

  def sum(): Int = this match {
    case IntListNode(data, tail) => data + tail.sum
    case IntListEnd => 0
  }

  def sumTailRecursive(): Int = {

    def sumAcc(intList: IntList, acc: Int): Int = intList match {
      case IntListNode(data, tail) =>
        sumAcc(tail, acc + data)
      case IntListEnd => acc
    }

    sumAcc(this, acc = 0)
  }

  def printList(): Unit = this match {
    case IntListNode(data, tail) =>
      print(s"$data ")
      tail.printList()
    case IntListEnd =>
      println()
  }


}

case class IntListNode(data: Int, tail: IntList) extends IntList

// singleton (only one)
case object IntListEnd extends IntList
