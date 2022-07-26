package S99

object P13_Lenghth_Encoding_Direct extends App {

  def encodeDirect[A](l: List[A]): List[(Int, A)] = {
    def _encodeDirect[B](c: Int, l1: List[B]): List[(Int, B)] = l1 match {
      case Nil => Nil
      case h :: Nil => List((c, h))
      case h :: t if h == t.head => _encodeDirect(c + 1, t)
      case h :: t => List((c, h)) ::: _encodeDirect(1, t)
    }
    _encodeDirect(1, l)
  }

  val l0 = List(1,1,1,2,1,1)
  val l1 = List(1,1,2,2,3,3)
  val l2 = List(1,2,2,2,2,2,1)
  val l3 = List(1,2,2,2,2,2,2,2)

  assert(encodeDirect(l0) == List((3, 1), (1, 2), (2, 1)))
  assert(encodeDirect(l1) == List((2, 1), (2, 2), (2, 3)))
  assert(encodeDirect(l2) == List((1, 1), (5, 2), (1, 1)))
  assert(encodeDirect(l3) == List((1, 1), (7, 2)))

  println(encodeDirect(l3))
  println(encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
}
