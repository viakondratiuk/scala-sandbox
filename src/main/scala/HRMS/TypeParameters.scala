package HRMS

object TypeParameters extends App {

  /**
   * Decrease code duplicates
   * We can use abstract type at []
   * which can be (param | return) type
   */

  def identity(a: String): String = a
  println(identity("Hello"))
  def identity(a: Int): Int = a
  println(identity(5))
//  println(identity(5.01))

  def identityA[A](a: A): A = a
  println(identityA("Hello"))
  println(identityA(123))
  println(identityA(5.01))
}
