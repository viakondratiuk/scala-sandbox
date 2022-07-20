package S99

import scala.annotation.tailrec

object P06_list_is_palindrome extends App {

  // WIL1: :+ List pattern matching to get tail element
  // WIL2: List(_) test List with one lement
  def isPalindromeClassic[A](l: List[A]): Boolean = {
    def reverse[A](l: List[A]): List[A] = {
      @tailrec
      def _reverse[A](l: List[A], res: List[A]): List[A] = l match {
        case Nil => res
        case h :: t => _reverse(t, h :: res)
      }
      _reverse(l, Nil)
    }

    l == reverse(l)
  }

  def isPalindrome[A](l: List[A]): Boolean = l match {
    case Nil => true
    case List(_) => true
    case (h :: rem) :+ t => (h == t) && isPalindrome(rem)
  }

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,1)
  val l5 = List(1,2,3,2,1)
  val l6 = List(1,5,3,2,1)

  assert(isPalindrome(l0))
  assert(isPalindrome(l1))
  assert(!isPalindrome(l2))
  assert(isPalindrome(l3))
  assert(isPalindrome(l5))
  assert(!isPalindrome(l6))
  println(isPalindrome(l6))
}
