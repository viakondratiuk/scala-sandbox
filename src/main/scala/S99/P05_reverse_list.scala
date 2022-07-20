package S99

import scala.annotation.tailrec

object P05_reverse_list extends App {

  // WIL1: ::: - method of List, prepends list to another List(1,2):::List(3,4)=>List(1,2,3,4)
  // WIL2: :: - method of List which prepends an element or pattern matching to split o h and tail
  // WIL3: folding for reverse
  // WIL4: how to write tailrec with nested function
  def reverseRecursive[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case h :: t => reverseRecursive(t) ::: List(h)
  }

  def reverseTail[A](l: List[A]): List[A] = {
    @tailrec
    def _reverse[A](l: List[A], res: List[A]): List[A] = l match {
      case Nil => res
      case h :: t => _reverse(t, h :: res)
    }
    _reverse(l, Nil)
  }

  def reverse[A](l: List[A]): List[A] = l.foldLeft(List[A]()) { case(a, el) => el :: a}

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3)
  val l5 = List(1,2,3,4,5)

  assert(reverse(l0) == List())
  assert(reverse(l1) == List(1))
  assert(reverse(l2) == List(2,1))
  assert(reverse(l3) == List(3,2,1))
  assert(reverse(l5) == List(5,4,3,2,1))
  println(reverse(l5))
}
