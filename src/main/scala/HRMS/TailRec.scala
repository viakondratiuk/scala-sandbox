package HRMS

import scala.annotation.tailrec

object TailRec extends App {

  /**
   * Recursion if FP way for iteration
   * Simple Recursion uses a lot of Stacks what can result in SO
   * Use tail rec
   * Compiler can optimize tail rec to iterative and use 1 frame
   * Tail rec - last call is recursive
   */

  def sumRec(l: List[Int]): Int = l match {
    case Nil => 0
    case h :: t => h + sumRec(t)
  }

  def sumTailRec(l: List[Int]): Int = {
    @tailrec
    def _sumTailRec(l: List[Int], acc: Int): Int = l match {
      case Nil => acc
      case h :: t => _sumTailRec(t, acc + h)
    }
    _sumTailRec(l, 0)
  }

  val l = List(1,2,3,4,5)

  println(sumRec(l))
  println(sumTailRec(l))
}
