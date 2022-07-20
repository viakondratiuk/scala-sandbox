package S99

import scala.annotation.tailrec

object P04_list_size extends App {

  // WIL1: How to hide extra param in nested function calls
  // WIL2: How to create tail recursion with nested functions
  // WIL3: How to count outside of recursion call
  // WIL4: folding
  @tailrec
  def lengthTail[A](l: List[A], acc: Int = 0): Int = l match {
    case Nil => acc
    case _ :: t => lengthTail(t, acc + 1)
  }

  def lengthInner[A](l: List[A]): Int = {
    def lengthTail[A](l: List[A], n: Int): Int = l match {
      case Nil => n
      case _ :: t => lengthTail(t, n + 1)
    }
    lengthTail(l, 0)
  }

  // But not tail recursion
  def lengthRecursive[A](l: List[A]): Int = l match {
    case Nil => 0
    case _ :: t => 1 + lengthRecursive(t)
  }

  def length[A](l: List[A]): Int = l.foldLeft(0) {(c, _) => c + 1}

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3)
  val l5 = List(1,2,3,4,5)

  assert(length(l0) == 0)
  assert(length(l1) == 1)
  assert(length(l2) == 2)
  assert(length(l3) == 3)
  assert(length(l5) == 5)
}
