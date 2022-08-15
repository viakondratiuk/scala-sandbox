package S99

object P16_Drop_every_nth extends App {

  def drop[A](idx: Int, l: List[A]): List[A] = {
    def _drop[B](tmp: Int, l: List[B]): List[B] = l match {
      case Nil => Nil
      case _ :: t if tmp == 0 => _drop(idx - 1, t)
      case h :: t => h :: _drop(tmp - 1, t)
    }

    _drop(idx - 1, l)
  }

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3)
  val l7 = List(1,2,3,4,5,6,7)

  assert(drop(1, l0) == List())
  assert(drop(3, l1) == List(1))
  assert(drop(3, l2) == List(1,2))
  assert(drop(3, l3) == List(1,2))
  assert(drop(3, l7) == List(1,2,4,5,7))

  println(drop(3, l7))
}
