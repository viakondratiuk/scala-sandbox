package S99

object P08_Eliminate_cons_dups extends App {

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

  // WIL1: Using foldLeft
  // WIL2: Partial functions can convert from one type to other type, cant accept several params
  // cant create partial function, because it accepts only 1 param
  def folder[A](ls: List[A], e: A): List[A] = (ls, e) match {
    case (Nil, e) => List(e)
    case (ls: List[A], e: A) if ls.last == e => ls
    case (ls: List[A], e: A) => ls :+ e
  }

  def compressFoldLeft[A](l: List[A]): List[A] = l.foldLeft(List[A]())(folder)

  val l0 = List()
  val l1 = List(1, 1, 2, 3)
  val l2 = List(1, 2, 2, 3)
  val l3 = List(1, 2, 3, 3)
  val l4 = List(1, 2, 3, 4)
  val la = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

  assert(compress(l0) == List())
  assert(compress(l1) == List(1,2,3))
  assert(compress(l2) == List(1,2,3))
  assert(compress(l3) == List(1,2,3))
  assert(compress(la) == List('a, 'b, 'c, 'a, 'd, 'e))
}
