package S99

import scala.annotation.tailrec

object P17_Split_into_2_parts extends App {

  def split[A](idx: Int, l: List[A]): List[List[A]] = {
    @tailrec
    def _split[B](idx: Int, buf: List[B], l: List[B]): List[List[B]] = l match {
      case Nil => List(buf, List())
      case _ if idx == 0 => List(buf, l)
      case h :: t => _split(idx - 1, buf :+ h, t)
    }

    _split(idx, Nil, l)
  }

  val l0 = List()
  val l1 = List(1)
  val l3 = List(1,2,3)
  val l7 = List(1,2,3,4,5,6,7)

  assert(split(1, l0) == List(List(), List()))
  assert(split(1, l1) == List(List(1), List()))
  assert(split(2, l3) == List(List(1,2), List(3)))
  assert(split(3, l7) == List(List(1,2,3),List(4,5,6,7)))

  println(split(3, l7))
}
