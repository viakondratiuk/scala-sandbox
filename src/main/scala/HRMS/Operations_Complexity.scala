package HRMS

import scala.collection.immutable.{SortedMap, SortedSet}

object Operations_Complexity extends App {
  // LinearSeq
  val l = List(1,2,3,4)
  println(l.head, l(2))

  // IndexedSeq
  val v = Vector(1,2,3,4)
  println(v.head, v(2))

  val a = Array(1,2,3,4)
  println(a.head, a(2))

  // Set
  val s = Set(1,2,3,4)
  println(s.head, s(2))

  val ss = SortedSet(3,4,5,1)
  println(ss)

  // Map
  val m = Map(1 -> 2, 2 -> 3, 3 -> 4)
  println(m.head, m(2))

  val sm = SortedMap(2 -> 3, 4 -> 5, 1 -> 2)
  println(sm)
}
