package S99

object P15_Duplicate_List_Elements_Ntimes extends App {

  def duplicateN2[A](n: Int, l: List[A]): List[A] = l match {
    case Nil => Nil
    case h :: _ => List.fill(n)(h) ::: duplicateN2(n, l.tail)
  }

  def duplicateN[A](n: Int, l: List[A]): List[A] = l.flatMap(e => List.fill(n)(e))

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,2)

  assert(duplicateN(5, l0) == List())
  assert(duplicateN(3, l1) == List(1,1,1))
  assert(duplicateN(3, l2) == List(1,1,1,2,2,2))
  assert(duplicateN(1, l3) == List(1,2,2))

  println(duplicateN(1, l3))
}
