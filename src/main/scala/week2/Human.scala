package week2

sealed trait Human {
  def name: String
}

case class Student(name: String, id: String) extends Human
case class Worker(name: String, work: Int) extends Human
case class Developer(name: String, position: String) extends Human
