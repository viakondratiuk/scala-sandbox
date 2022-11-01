package HRMS

object Implicit_Conversion extends App {

  case class Centimeters(value: Double) extends AnyVal
  case class Meters(value: Double) extends AnyVal
  case class Kilometers(value: Double) extends AnyVal

  // convert one type to another
  implicit def meters2centimeters(meters: Meters): Centimeters =
    Centimeters(meters.value * 100)

  val centimeters: Centimeters = Meters(2.5)
  println(centimeters)

  implicit val kilometers2meters: Kilometers => Meters =
    kilometers => Meters(kilometers.value * 1000)

  val meters: Meters = Kilometers(2.5)
  println(meters)

  // extend classes
  class LengthSyntax(value: Double) {
    def centimeters = Centimeters(value)
    def meters = Meters(value)
    def kilometers = Kilometers(value)
  }

  implicit def double2richSyntax(value: Double): LengthSyntax =
    new LengthSyntax(value)

  val length: Double = 2.5
  println(length.centimeters)
  println(length.meters)
  println(length.kilometers)
}
