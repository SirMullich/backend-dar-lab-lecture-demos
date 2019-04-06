package week1

trait Speaker {

  // best use `def` for abstract/defined methods/parameters
  def speak(name: String) = s"Hi, $name"

  def walk(name: String): String
}
