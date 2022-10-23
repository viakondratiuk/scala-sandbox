package HRMS

object Implicits extends App {
  //https://stackoverflow.com/questions/10375633/understanding-implicit-in-scala
  // a way to extends not your code

  // 1. Implicit conversion
  implicit def intToString(i: Int) = i.toString

  val i: String = 10
  println(i)

  // conversion to the target type when needed
  def duplicate(s: String) = s + " " + s
  println(duplicate(100))

  // Converting the receiver
  class Car {
    val isFast = false
  }

  class Chair {
    val isComfortable = true
  }

  val car = new Car()
  implicit def carToChair(c: Car) = new Chair()
  println(car.isComfortable)


  // 2. Implicit parameters
  def plus(implicit k: Double) = k + k
  implicit val k = 2.0
  println(plus)

  implicit class MeterToCm(meter: Int) {
    def meterToCm = meter * 100
  }

  println(1.meterToCm)
}
