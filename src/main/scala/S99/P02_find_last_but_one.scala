package S99

object P02_find_last_but_one extends App {

  // List(1, List(2, List(3, Nil))) => 2
  // WIL1: with pattern h :: List(_) I can get last elements

//  def penultimate1(l: List[Int], prev: Option[Int] = None): Option[Int] = l match {
//    case h :: t if t != Nil => penultimate1(t, Some(h))
//    case _ :: t => prev
//    case _ :: Nil => None
//    case _ => throw new NoSuchElementException
//  }

  def penultimate2[A](l: List[A]): A =  l match {
    case h :: List(_) => h
    case _ :: t => penultimate2(t)
    case _ => throw new NoSuchElementException
  }

  val empty = List()
  val one = List(1)
  val two = List(1, 2)
  val five = List(1, 2, 3, 4, 5)
  val fiveStrings = List("a", "b", "c", "d", "e")

//  println(penultimate1(empty))
//  println(penultimate2(one))
  println(penultimate2(two))
  println(penultimate2(five))

//  println(five.init.last)
}
