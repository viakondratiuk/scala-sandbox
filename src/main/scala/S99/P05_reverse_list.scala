package S99

import scala.annotation.tailrec

object P05_reverse_list extends App {
  val l = List(1,2,3,4,5)
  println(l.reverse)

  val l1 = 1 :: List(2,3,4)
  println(l1)
  println("===========")

  /*
    (1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
   */

  @tailrec
  def listReverse[A](l: List[A], r: List[A] = Nil): List[A] = l match {
    case h :: t => listReverse(t, h :: r)
    case Nil => r
  }

  println(listReverse(List()))
  println(listReverse(List(1)))
  println(listReverse(List(1,2,3,4)))
  println(listReverse(List(1,2,3,4,5)))
}
