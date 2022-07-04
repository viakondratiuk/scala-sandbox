package S99

object P07_flatten_nested_list extends App {
  /*
    List(List(1,2,3, List(4)), 5, 6) => List(1, 2, 3, 4, 5, 6)
    [[1,2,3], 4, [5,6,7]]
   */

  // List(1,2,3,5,6,7,4)
  val l = List(List(List(1,2,3), List(5,6,7)), List(4))

  def flattenList(l: List[Any], acc: List[Any] = Nil): List[Any] = l match {
    case (h : List[_]) :: t => flattenList(h, acc) ++ flattenList(t, acc)
    case h :: t => h :: acc ++ flattenList(t, acc)
    case Nil => acc
  }

  println(flattenList(l))

  // TODO: try recursive flatMap
  def flattenList1(l: List[Any]): List[Any] = l.flatMap {
    case x : List[_] => flattenList1(x)
    case x => List(x)
  }

  println(flattenList1(l))
}
