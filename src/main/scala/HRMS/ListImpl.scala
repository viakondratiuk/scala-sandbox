package HRMS

object ListImpl extends App {

  val l: List[Int] = List(1, 2, 3)

  class Car()
  class BMW() extends Car()
  class Audi() extends Car()

  val l1: List[Car] = List(new BMW(), new Car())

  val l2 = 4 :: List(1,2,3)
  val l3 = List(1,2,3).::(4)
  println(l2)
  println(l3)

  val h4 :: t4 = 0 +: List(1,2,3) :+ 4
  println(h4)
  println(t4)
}
