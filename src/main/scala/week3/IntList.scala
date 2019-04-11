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

  // x => x * 2   (Int) => (Int)
//  def mapTemp(func: Int => Int): IntList = {
//
//    def internalMap(list: IntList, acc: IntList)(f: Int => Int): IntList = list match {
//      case IntListNode(data, tail) =>
//        val result: Int = f(data)
//
//        IntListNode(result, tail.map(func))
//
//      case IntListEnd =>
//        acc
//    }
//
//  }

  def map(func: Int => Int): IntList = this match {
    case IntListNode(data, tail) =>
      IntListNode(func(data), tail.map(func))

    case IntListEnd => IntListEnd
  }

  def lastNode: IntListNode = this match {
    case intNode @ IntListNode(data, tail) =>
      tail match {
        case tailNode: IntListNode => tailNode.lastNode
        case IntListEnd => intNode
      }

    case IntListEnd => throw new IllegalStateException("Last node of End")
  }


}

case class IntListNode(data: Int, tail: IntList) extends IntList

// singleton (only one)
case object IntListEnd extends IntList
