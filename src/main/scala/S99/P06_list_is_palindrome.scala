package S99

import scala.annotation.tailrec

object P06_list_is_palindrome extends App {

  val l = List(1,2,3,3,2,1)
  println(s"isPalindrome0: ${l == l.reverse}")


  def isPalindrome1[A](l: List[A]): Boolean = {
    @tailrec
    def listReverse[A](l: List[A], r: List[A] = Nil): List[A] = l match {
      case h :: t => listReverse(t, h :: r)
      case Nil => r
    }
    l == listReverse(l)
  }
  println(s"isPalindrome1: ${isPalindrome1(l)}")
}
