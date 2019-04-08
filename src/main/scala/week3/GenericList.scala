package week3

import week2.Human

// only works with subtype of Human
sealed trait GenericList[+T <: Human]{

  override def toString: String =  {
    def toStringAcc(genericList: GenericList[T], acc: String): String = genericList match {
      case GenericListNode(data, tail) =>
        toStringAcc(tail, s"$acc $data")
      case GenericListEnd() =>
        acc
      case any: Any =>
        s"Unexpected $any"
    }
    toStringAcc(this, "")
  }

  def toStringHuman: String =  {
    def toStringAcc(genericList: GenericList[T], acc: String): String = genericList match {
      case GenericListNode(data, tail) =>
        toStringAcc(tail, s"$acc ${data.name}")
      case GenericListEnd() =>
        acc
      case any: Any =>
        s"Unexpected $any"
    }
    toStringAcc(this, "")
  }

  def printList(): Unit = this match {
    case GenericListNode(data, tail) =>
      print(s"$data ")
      tail.printList()
    case GenericListEnd() =>
      println()
  }
}

case class GenericListNode[+T <: Human](data: T, tail: GenericList[T]) extends GenericList[T]
case class GenericListEnd[T <: Human]() extends GenericList[T]