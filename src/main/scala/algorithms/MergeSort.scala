package algorithms

/**
 *
 * - Divide the problem into a number of sub problems that are smaller instances of the same problem.
 * - Conquer the sub problems by solving them recursively. If the sub problem sizes are small enough, however,
 *    just solve the sub problems in a straightforward manner.
 * - Combine the solutions to the sub problems into the solution for the original problem.
 *
 *
 */

object MergeSort extends App {

  def _merge(left: List[Int], right: List[Int]): List[Int] = (left, right) match {
    case (Nil, Nil) => Nil
    case (_, Nil) => left
    case (Nil, _) => right
    case (x :: _, y :: _) =>
      if (x > y)
        y :: _merge(left, right.tail)
      else
        x :: _merge(left.tail, right)
  }

  def _sort(l: List[Int]): List[Int] = l
    .map(List(_))
    .reduce((xs, ys) => _merge(xs, ys))

  def _sort2(l: List[Int]): List[Int] =
    if (l.length <= 1) l
    else {
      // divide
      val (left, right) = l.splitAt( l.length / 2)
      // conquer
      // combine
      _merge(_sort2(left), _sort2(right))
    }

  println(_sort(List(1, 8, 5, 6, 11, 2)))

  println(_sort2(List(1, 8, 5, 6, 11, 2)))
}
