package S99

object P10_Run_length_encoding extends App {

  // List(1, 1, 1, 2, 1, 1) -> List((3, 1), (1, 2), (2, 1))
  def encode[A](l: List[A]): List[(Int, A)] = {

    def pack[B](l: List[B], buf: List[B] = Nil): List[List[B]] = l match {
      case Nil => List(buf)
      case h :: t if buf.isEmpty => pack(t, List(h))
      case h :: t if h == buf.head => pack(t, h :: buf)
      case h :: t => buf :: pack(t, List(h))
    }

    pack(l).map(p => (p.size, p.head))
  }

  val l0 = List(1,1,1,2,1,1)
  val l1 = List(1,1,2,2,3,3)
  val l2 = List(1,2,2,2,2,2,1)
  val l3 = List(1,2,2,2,2,2,2,2)

  assert(encode(l0) == List((3, 1), (1, 2), (2, 1)))
  assert(encode(l1) == List((2, 1), (2, 2), (2, 3)))
  assert(encode(l2) == List((1, 1), (5, 2), (1, 1)))
  assert(encode(l3) == List((1, 1), (7, 2)))

  println(encode(l3))
}
