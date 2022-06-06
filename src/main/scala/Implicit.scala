object Implicit extends App {

  object Converter {
    implicit def intToString(a: Int): String =
      s"$a converted toString"
  }

  import Converter.intToString
  val a: Int = 1
  val b: String = a
  println(b)

  // WHY? Bad case
  def magic(s: String): String = s
  magic(a)

  // example with Class
  case class RichInteger(integer: Int) {
    def multiply: Int = integer * 10
  }

  implicit def toRichInteger(integer: Int): RichInteger =
    new RichInteger(integer)

  val a1 = a + 1
  val a2 = a1 * 2
  val a3 = a2.multiply // needed here, but it is still `int`
  println(a3)
}
