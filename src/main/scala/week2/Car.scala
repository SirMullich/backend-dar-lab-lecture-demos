package week2

// Companion object
object Car {

  def apply(model: String, year: Int) = new Car(model, year)

  def unapply(car: Car) = (car.getModel, car.getYear)

  def newer(car1: Car, car2: Car): Boolean = car1.getYear > car2.getYear
}

class Car(model: String, year: Int) {
  def getModel = model
  def getYear = year
}
