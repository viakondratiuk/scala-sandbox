package S99


object P09_cons_to_sublist extends App {

  // List(1, 2, 2, 3, 4) -> List(List(1), List(2, 2), List(3), List(4))
  // List(1, 1, 2, 1, 1) -> List(List(1, 1), 2, List(1, 1))
  // List(1, 1, 1, 2, 1, 1) -> List(List(1, 1, 1), 2, List(1, 1))

  // WIL1: How to use buffer to store the current sublist
  // WIL2: How to operate with folded list
  // WIL3: How to reduce List in recursive function
  def pack[A](l: List[A], buf: List[A] = Nil): List[List[A]] = l match {
    case Nil => List(buf)
    case h :: t if buf.isEmpty => pack(t, List(h))
    case h :: t if h == buf.head => pack(t, h :: buf)
    case h :: t => buf :: pack(t, List(h))
  }

  val one = List(1)
  val two = List(1, 2)
  val dup2 = List(1, 1, 2, 1, 1)
  val dup3 = List(1, 1, 1, 2, 1, 1)
  val dup1 = List(1, 1, 1, 2, 3, 4)
  val dup4 = List(1, 1, 1, 2, 1, 1, 3, 4, 4)

  println(s"$one -> ${pack(one)}")
  println(s"$two -> ${pack(two)}")
  println(s"$dup2 -> ${pack(dup2)}")
  println(s"$dup3 -> ${pack(dup3)}")
  println(s"$dup1 -> ${pack(dup1)}")
  println(s"$dup4 -> ${pack(dup4)}")
}
