package S99

import scala.annotation.tailrec

object P19_Rotate_N_Places extends App {

  def rotate[A](n: Int, l: List[A]): List[A] = {
    @tailrec
    def _rotateWithBuff[B](k: Int, buff: List[B], rest: List[B]): List[B] = rest match {
      case h :: tail if k > 0 => _rotateWithBuff(k - 1, buff :+ h, tail)
      case _ => rest ::: buff
    }

    _rotateWithBuff(if (n >= 0) n else l.length + n, Nil, l)
  }

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3,4)
  val l4 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

  assert(rotate(5, l0) == List())
  assert(rotate(-1, l0) == List())
  assert(rotate(0, l0) == List())
  assert(rotate(3, l1) == List(1))
  assert(rotate(-3, l1) == List(1))
  assert(rotate(1, l2) == List(2,1))
  assert(rotate(-1, l2) == List(2,1))
  assert(rotate(2, l3) == List(3,4,1,2))
  assert(rotate(0, l3) == l3)

  println("actual\t\t", rotate(3, l4))
  println("expected\t", List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))

  println()

  println("actual\t\t", rotate(-2, l4))
  println("expected\t", List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i))
}