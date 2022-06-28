package S99

import scala.annotation.tailrec

object P4_list_size extends App {

  val l = List(1,2,3,4)
  println(l.size)

  @tailrec
  def listSize[A](l: List[A], acc: Int = 0): Int = l match {
    case Nil => 0
    case _ :: t => listSize(t, acc + 1)
  }

  println(listSize(List()))
  println(listSize(List(1)))
  println(listSize(List(1,2)))
  println(listSize(List(1,2,3,4,5)))
}
