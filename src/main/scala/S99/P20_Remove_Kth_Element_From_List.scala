package S99

import scala.annotation.tailrec

object P20_Remove_Kth_Element_From_List extends App {

  def remove[A](n: Int, l: List[A]): Either[Exception, (List[A], A)] = {
    @tailrec
    def removeAt[B](k: Int, buff: List[B], rest: List[B]): (List[B], B) = rest match {
      case h :: tail if k > 0 => removeAt(k - 1, buff :+ h, tail)
      case h :: tail => (buff ::: tail, h)
    }

    if (n + 1 > l.length) Left(new NoSuchElementException())
    else Right(removeAt(n, Nil, l))
  }

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3,4)
  val l4 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

  assert(remove(0, l0).isLeft)
  assert(remove(5, l0).isLeft)
  assert(remove(0, l1) == Right((List(), l1.head)))
  assert(remove(1, l2) == Right((List(1), 2)))
  assert(remove(2, l3) == Right((List(1,2, 4), 3)))
  assert(remove(l3.length + 10, l3).isLeft)

  println("actual\t\t", remove(1, List('a, 'b, 'c, 'd)).right.get)
  println("expected\t", (List('a, 'c, 'd),'b))
}
