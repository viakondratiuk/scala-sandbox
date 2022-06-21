package S99

object P3_find_kth extends App {

  def findKth[A](k: Int, l: List[A]): A =  l match {
    case h :: _ if k == 0 => h
    case _ :: t => findKth(k - 1, t)
    case _ => throw new NoSuchElementException
  }

  val l1 = List(1,2,3,4,5)
  println(findKth(0, l1))
  println(findKth(2, l1))
  println(findKth(4, l1))
//  println(findKth(-1, l1))
//  println(findKth(6, l1))
}
