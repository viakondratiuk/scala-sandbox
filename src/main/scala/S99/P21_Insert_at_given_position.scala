package S99


object P21_Insert_at_given_position extends App {

  def insertAt[A](el: A, n: Int, l: List[A]): List[A] = l.splitAt(n) match {
    case (l, r) => l ::: el :: r
  }

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,3,4)
  val l4 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

  println(insertAt(10, 5, l0))
  println(insertAt(10, 0, l3))
  println(insertAt(10, 1000, l3))

  println("actual\t\t", insertAt('new, 1, List('a, 'b, 'c, 'd)))
  println("expected\t", List('a, 'new, 'b, 'c, 'd))
}
