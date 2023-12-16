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

  // hash

  // 3 sum
}

object TwoSum extends App {
  // fraud detection, portfolio rebalance

  val l = List(1, 3, 5, 7, 9, 11, 12, 12)
  val target = 13

  println(Solutions.bruteForce(l, target))
  println(Solutions.sorted(l, target))
}
