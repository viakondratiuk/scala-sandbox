package S99

import scala.annotation.tailrec

// https://www.thedigitalcatonline.com/blog/2015/04/07/99-scala-problems-01-find-last-element/
object P1_find_last extends App {

  // WIL: 1. Polymorphism with A as generic
  // WIL: 2. List(h) to test one element
  // WIL: 3. split List to head :: tail with unapply
  @tailrec
  def last[A](l: List[A]): A = l match {
    case List(h) => h
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException
  }

  val empty = List()
  val one = List(1)
  val five = List(1, 2, 3, 4, 5)
  val fiveStrings = List("a", "b", "c", "d", "e")

//  println(last(empty))
  println(last(one))
  println(last(five))
  println(last(fiveStrings))

  class -->(val head: String, val tail: String)
  object --> {
    def unapply(data: -->): Option[(String, String)] =
      Some((data.head, data.tail))
  }

  val l = new -->("big", "long")
  val fake = l match {
    case h --> t => s"Yes, my head is $h and tail is $t"
    case _ => "No Match"
  }

  println(fake)
}
