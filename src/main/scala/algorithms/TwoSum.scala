package algorithms

object Solutions {
  def bruteForce(l: List[Int], target: Int): List[(Int, Int)] = {
    for {
      (x, xi) <- l.zipWithIndex
      (y, yi) <- l.zipWithIndex
      if xi < yi && (x + y == target)
    } yield (xi, yi)
  }

  def sorted(l: List[Int], target: Int): List[(Int, Int)] = {
    var res = List[(Int, Int)]()
    val ls = l.sorted
    var (i, j) = (0, l.size - 1)

    while (i < j) {
      if (ls(i) + ls (j) == target) {
        res = (i, j) :: res
        j -= 1
      } else if (ls(i) + ls(j) < target) {
        i += 1
      } else {
        j -= 1
      }
    }

    res
  }

  def hash(l: List[Int], target: Int): List[(Int, Int)] = {
    val indexMap = l.zipWithIndex.groupBy(_._1).mapValues(_.map(_._2))

    l.zipWithIndex.flatMap { case (v, i) =>
      indexMap.getOrElse(target - v, List()).filter(_ > i).map(j => (i, j))
    }
  }

  // 3 sum
}

object TwoSum extends App {
  // fraud detection, portfolio rebalance

  val l = List(1, 3, 5, 7, 9, 11, 12, 12)
  val target = 16

  println(Solutions.bruteForce(l, target))
  println(Solutions.sorted(l, target))
  println(Solutions.hash(l, target))
}
