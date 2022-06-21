package S99

// https://www.thedigitalcatonline.com/blog/2015/04/07/99-scala-problems-01-find-last-element/
object P1_find_last extends App {

  def last[A](l: List[A]): A = l match {
    case h :: Nil => h
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException
  }

  println(last(List(1,2,3,4)))
  println(last(List("a", "b", "c")))

  List(1,2,3,4) match {
    case h :: t => println(s"head is $h and tail is $t")
  }
}
