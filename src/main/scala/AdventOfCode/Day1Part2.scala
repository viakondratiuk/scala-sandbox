package AdventOfCode

object Day1Part2 extends App {
  val day1Txt = "day1.txt"
  val source = scala.io.Source.fromResource(day1Txt)
  val lines = try source.mkString.split("\n").toList.map(_.toInt) finally source.close()

//  val res1 = lines.sliding(2).map { case Seq(x, y, _*) => y - x }.count(_ > 0)
//  println(res1)

  def increase3(l: List[Int]): List[Int] = l match {
    case a1 :: a2b1 :: a3b2 :: b3 :: t => (a2b1 + a3b2 + b3) - (a1 + a2b1 + a3b2) :: increase3(a2b1 :: a3b2 :: b3 :: t)
    case _ => Nil
  }
  val res2 = increase3(lines).count(_ > 0)
  println(res2)
}
