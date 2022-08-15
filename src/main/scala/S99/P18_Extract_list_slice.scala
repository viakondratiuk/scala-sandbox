package S99

import scala.annotation.tailrec

object P18_Extract_list_slice extends App {

  def slice[A](start: Int, end: Int, l: List[A]): List[A] = {
    @tailrec
    def _slice[B](start: Int, end: Int, buf: List[B], l: List[B]): List[B] = l match {
      case Nil => buf
      case _ :: t if start != 0 => _slice(start - 1, end - 1, buf, t)
      case h :: t if start == 0 & end != 0 => _slice(start, end - 1, buf :+ h, t)
      case _ => buf
    }

    _slice(start, end, Nil, l)
  }

  val l0 = List()
  val l1 = List(1)
  val l3 = List(1,2,3)
  val l7 = List(1,2,3,4,5,6,7)

  assert(slice(1, 3, l0) == List())
  assert(slice(3, 5, l7) == List(4, 5))
  assert(slice(1, 5, l7) == List(2, 3, 4, 5))
  assert(slice(0, 5, l7) == List(1, 2, 3, 4, 5))

  println(slice(99, 100, l7))
}
