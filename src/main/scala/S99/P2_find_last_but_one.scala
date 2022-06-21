package S99

object P2_find_last_but_one extends App {

  // List(1, List(2, List(3, Nil)))
  // last

  def lastMinus1(l: List[Int], prev: Option[Int] = None): Option[Int] = {
    l match {
      case head :: tail if tail != Nil => lastMinus1(tail, Some(head))
      case _ :: tail if tail == Nil => prev
      case _ :: Nil => None
      case _ => throw new NoSuchElementException
    }
  }

  def listMunus1V2[A](l: List[A]): A = {
    l match {
      case h :: List(_) => h
      case _ :: t => listMunus1V2(t)
      case _ => throw new NoSuchElementException
    }
  }

  val l1 = List(1,2,3,4)
  println(listMunus1V2(l1))

  val l2 = List(1,2)
  println(listMunus1V2(l2))

//  val l3 = List(1)
//  println(listMunus1V2(l3))

//  val l4 = List()
//  println(lastMinus1(l4))

  println(l1.init.last)
}
