object Generics extends App {

  class Container[Subtype](var value: Subtype) {
    //  val value = valueArgs
  }

  val integer = new Container[Int](1)

  //val s2: Container[String] = s1
  val string = new Container[String]("1")


  def plus1(s: Container[Int]) = s.value + 2
  def plus2(s: Container[String]) = s.value + 2

  println(plus1(integer))
  println(plus2(string))

  val s: Seq[Int] = Seq(1, 2)

  def pow2[T <: AnyVal](s: T) = {
    s match {
      case sInt: Int => sInt * sInt
      case sLong: Long => sLong * sLong
      case _ => 0L
    }

  }

  println(pow2(3))
  println(pow2(2L))
  println(pow2(true))
  println(pow2(()))
  println(pow2(4.4))
  println(pow2('a'))
}
