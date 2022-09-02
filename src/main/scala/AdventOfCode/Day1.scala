package AdventOfCode

import scala.annotation.tailrec


object Day1 extends App {
//  val source = scala.io.Source.fromResource("day1.txt")
//  val listOfLines = try source.mkString.split("\n").toList.map(_.toInt) finally source.close()
//
//  val res1 = listOfLines.sliding(2).map { case Seq(x, y, _*) => y - x }.count(_ > 0)
////  println(res1)
//
//  def increase(l: List[Int]): List[Int] = l match {
//    case p :: c :: t => c - p :: increase(c :: t)
//    case _ => Nil
//  }
//  val res2 = increase(listOfLines).count(_ > 0)
////  println(res2)
//
//  def lazyIncrease(source: BufferedSource): Int = {
//    var count = 0
//    var prev = source.getLines().next()
//    for (curr <- source.getLines()) {
//      if (curr.toInt > prev.toInt) count += 1
//      prev = curr
//    }
//
//    count
//  }

  def lazyRecursion(source: Iterator[String]): Int = {
    @tailrec
    def _lazyRecursion(prev: Int, count: Int): Int = {
      if (!source.hasNext) return count
      val cur = source.next().toInt

      if (cur > prev) _lazyRecursion(cur, count + 1)
      else _lazyRecursion(cur, count)
    }

    val prev = source.next().toInt
    _lazyRecursion(prev, 0)
  }

  val source = scala.io.Source.fromResource("day1.txt")
  val source1 = source.getLines()
//  println(lazyIncrease(source))
  println(lazyRecursion(source1))
  source.close()

  // TODO: Read diff between BufferedSource and Iterator
}
