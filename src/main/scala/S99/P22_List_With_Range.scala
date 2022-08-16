package S99

object P22_List_With_Range extends App {

  def range(start: Int, end: Int): List[Int] = {
    if (start > end) throw new IllegalArgumentException("start > end")

    if (start < end) start :: range(start + 1, end)
    else List(end)
  }

//  val l0 = List()
//  val l1 = List(1)
//  val l2 = List(1,2)
//  val l3 = List(1,2,3,4)
//  val l4 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

  println(range(0, 5))
  println(range(5, 5))
//  println(range(10, 5))

  println("actual\t\t", range(4, 9))
  println("expected\t", List(4, 5, 6, 7, 8, 9))
}
