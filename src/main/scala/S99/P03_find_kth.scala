package S99

import scala.annotation.tailrec

object P03_find_kth extends App {

  @tailrec
  def findKth[A](k: Int, l: List[A]): A =  k match {
    case 0 => l.head
    case k if k > 0 => findKth(k - 1, l.tail)
    case _ => throw new NoSuchElementException
  }

  // WIL: 1. Tuple pattern matching
  def findKthTuples[A](k: Int, l: List[A]): A = (k, l) match {
    case (0, h::_) => h
    case (k, _::t) => findKthTuples(k-1, t)
    case _ => throw new NoSuchElementException
  }

  val l1 = List(1,2,3,4,5)

  println(findKthTuples(0, l1))
  println(findKthTuples(2, l1))
  println(findKthTuples(4, l1))
}
