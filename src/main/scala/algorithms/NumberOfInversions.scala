package algorithms

object NumberOfInversions extends App {

  def n_2(l: List[Int]): Int = {
    val inversion = for {
      i <- l.indices
      j <- i until l.length
    } yield if (l(i) > l(j)) 1 else 0

    inversion.sum
  }

  def n_log_n(l: List[Int]): Int = {
    0
  }

  val l1 = List(5, 3, 1, 4, 2)
  println(n_2(l1))
}
