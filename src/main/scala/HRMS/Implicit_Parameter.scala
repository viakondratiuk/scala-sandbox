package HRMS

object Implicit_Parameter extends App {

  case class Color(v: String)
  case class DrawingDevice(v: String)

  def write(text: String)(implicit color: Color, by: DrawingDevice) =
    s"Writing $text in ${color.v} color by ${by.v}"

  def writeByMix(text: String)(implicit color1: Color, color2: Color, by: DrawingDevice) =
    s"Writing $text in ${color1.v} color and ${color2.v} color by ${by.v}"

  implicit val red = Color("red")
  implicit val green = Color("green")
  implicit val pen = DrawingDevice("pen")

//  println(write("A good day"))
  println(writeByMix("A good day")(red, green, pen))
}
