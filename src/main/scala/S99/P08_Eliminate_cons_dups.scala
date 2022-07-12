package S99

object P08_Eliminate_cons_dups extends App {
  // TODO: Think global - find patterns at this and prev tasks

  /*
   1. dup - dup - el - el
   2. el - dup - dup - el
   3. el - el - dup - dup
   4. el - el - el - el
   */

  val l0 = List()
  val l1 = List(1, 1, 2, 3)
  val l2 = List(1, 2, 2, 3)
  val l3 = List(1, 2, 3, 3)
  val l4 = List(1, 2, 3, 4)
  val la = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

  def eliminateDups[A](l: List[A], noDup: List[A] = Nil, last: Option[A] = None): List[A] =
    (l, last) match {
      case (h1 :: t1, None) => eliminateDups(t1, noDup :+ h1, Some(h1))
      case (h1 :: t1, Some(last)) if h1 != last => eliminateDups(t1, noDup :+ h1, Some(h1))
      case (h1 :: t1, Some(last)) if h1 == last => eliminateDups(t1, noDup, Some(h1))
      case (Nil, _) => noDup
    }

  def compress[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case h :: Nil => List(h)
    case h :: t if h == t.head => compress(t)
    case h :: t => h :: compress(t)
  }

  /*
  List(1,2,2,3)
  List() 1 -> List(1)
  List(1) 2 -> List(1, 2)
  List(1, 2) 2 -> List(1, 2)
  List(1, 2) 3 -> List(1, 2, 3)
   */

  // cant create partial function, because it accepts only 1 param
  def folder[A](ls: List[A], e: A): List[A] = (ls, e) match {
    case (Nil, e) => List(e)
    case (ls: List[A], e: A) if ls.last == e => ls
    case (ls: List[A], e: A) => ls :+ e
  }

  def compressFoldLeft[A](l: List[A]): List[A] = l.foldLeft(List[A]())(folder)

  println(compressFoldLeft(l0))
  println(compressFoldLeft(l1))
  println(compressFoldLeft(l2))
  println(compressFoldLeft(l3))
  println(compressFoldLeft(l4))
  println(compressFoldLeft(la))
}
