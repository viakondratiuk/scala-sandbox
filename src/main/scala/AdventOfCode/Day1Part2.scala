package AdventOfCode

import scala.annotation.tailrec

object Day1Part2 extends App {
//  val day1Txt = "day1.txt"
//  val source = scala.io.Source.fromResource(day1Txt)
//  val lines = try source.mkString.split("\n").toList.map(_.toInt) finally source.close()

//  val res1 = lines.sliding(2).map { case Seq(x, y, _*) => y - x }.count(_ > 0)
//  println(res1)

//  def increase3(l: List[Int]): List[Int] = l match {
//    case a1 :: a2b1 :: a3b2 :: b3 :: t => (a2b1 + a3b2 + b3) - (a1 + a2b1 + a3b2) :: increase3(a2b1 :: a3b2 :: b3 :: t)
//    case _ => Nil
//  }
//  val res2 = increase3(lines).count(_ > 0)
//  println(res2)

// 1 2 3 4 5 6
// (2 + 3 + 4) - (1 + 2 + 3) = 9 - 6 = 3 > 0, +1
// (3 + 4 + 5) - (2 + 3 + 4) = 12 - 9 = 3 > 0, +1

  def lazyRecursion(source: Iterator[String]): Int = {
    @tailrec
    def _lazyRecursion(n1: Int, n2: Int, n3: Int, count: Int): Int = {
      if (!source.hasNext) return count

      val n4 = source.next().toInt
      val sumFirst = n1 + n2 + n3
      val sumLast = n2 + n3 + n4

      if (sumLast > sumFirst) _lazyRecursion(n2, n3, n4, count + 1)
      else _lazyRecursion(n2, n3, n4, count)
    }

    val n1 = source.next().toInt
    val n2 = source.next().toInt
    val n3 = source.next().toInt

    _lazyRecursion(n1, n2, n3, 0)
  }

  val source = scala.io.Source.fromResource("day1.txt")
  val lines = source.getLines()
  println(lazyRecursion(lines))
  source.close()
}
