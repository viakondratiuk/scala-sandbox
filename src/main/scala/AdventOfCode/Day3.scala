package AdventOfCode

object Day3 extends App {
  val source = scala.io.Source.fromResource("day3.txt")
  val (lines1, lines2) = source.getLines().duplicate
  val size = lines2.size

  // 3985686
  val ones = lines1
    .map(_.split("").map(_.toInt))
    .reduce((acc, cur) => acc.zip(cur).map(z => z._1 + z._2))
  val zeros = ones.map(size - _)

  val gamma = ones.zip(zeros).map(z => if (z._1 > z._2) 1 else 0)
  val epsilon = ones.zip(zeros).map(z => if (z._1 < z._2) 1 else 0)

//  println(gamma, gamma.mkString(""), epsilon, epsilon.mkString(""))

  val gDec = Integer.parseInt(gamma.mkString(""), 2)
  val eDec = Integer.parseInt(epsilon.mkString(""), 2)

  println(gDec * eDec)
}
