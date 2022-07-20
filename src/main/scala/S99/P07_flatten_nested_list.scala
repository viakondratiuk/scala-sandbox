package S99

object P07_flatten_nested_list extends App {
  /*
    List(List(1,2,3, List(4)), 5, 6) => List(1, 2, 3, 4, 5, 6)
   */
  // WIL1: Type erasure because of JVM, it doesnt keep type info for collections
  // WIL2: using flatMap to recursively flatten
  def flatten(l: List[Any], acc: List[Any] = Nil): List[Any] = l match {
    case (h : List[_]) :: t => flatten(h, acc):::flatten(t, acc)
    case h :: t => h :: acc ::: flatten(t, acc)
    case Nil => acc
  }

  def flattenFlatMap(l: List[Any]): List[Any] = l.flatMap {
    case x : List[_] => flattenFlatMap(x)
    case x => List(x)
  }

  val l0 = List(List())
  val l1 = List(List(1))
  val l2 = List(List(1),List(2))
  val l3 = List(List(1),List(2),List(1),List(2))
  val l5 = List(List(List(1,2,3),List(5,6,7)),4)

  assert(flatten(l0) == List())
  assert(flatten(l1) == List(1))
  assert(flatten(l2) == List(1,2))
  assert(flatten(l3) == List(1,2,1,2))
  println(flatten(l5))
  assert(flatten(l5) == List(1,2,3,5,6,7,4))
}
