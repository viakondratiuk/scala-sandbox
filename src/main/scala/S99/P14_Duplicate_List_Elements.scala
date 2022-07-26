package S99

object P14_Duplicate_List_Elements extends App {

  def duplicate2[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case h :: _ => List(h, h) ::: duplicate(l.tail)
  }

  def duplicate[A](l: List[A]): List[A] = l.flatMap(e => List(e, e))

  val l0 = List()
  val l1 = List(1)
  val l2 = List(1,2)
  val l3 = List(1,2,2)

  assert(duplicate(l0) == List())
  assert(duplicate(l1) == List(1,1))
  assert(duplicate(l2) == List(1,1,2,2))
  assert(duplicate(l3) == List(1,1,2,2,2,2))

  println(duplicate(l3))
}
