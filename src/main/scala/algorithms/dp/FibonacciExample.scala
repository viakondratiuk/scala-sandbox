package algorithms.dp
import scala.collection.mutable


/**
 * DP
 * - Overlapping subproblems, avoid redundant calcs
 * - Optimal substructure - if optimal solution in optimal subproblem solution
 * 63 cents, [1, 5, 10, 25] cents find optimal solutions for: (62, 58, 53, 38)
 */

/**
 * Top-Down
*  - recursive
 * - use memo
 * - value is 10, 9, 8 ...
 * Bottom-Up
 * - iterative
 * - tabulative: use array to store
 * - value is 1, 2, 3...
 */

object FibonacciTopDown {
  val memo: mutable.Map[Int, Long] = mutable.Map(0 -> 0, 1 -> 1)

  def fibonacci(n: Int): Long = memo.getOrElseUpdate(n, {
    fibonacci(n - 1) + fibonacci(n - 2)
  })
}

object FibonacciBottomUp {
  def fibonacci(n: Int): Long = {
    if (n == 0) return 0
    if (n == 1) return 1

    val fibTable = Array.ofDim[Long](n + 1)
    fibTable(0) = 0
    fibTable(1) = 1

    for (i <- 2 to n) {
      fibTable(i) = fibTable(i - 1) + fibTable(i - 2)
    }

    fibTable(n)
  }
}

object FibonacciExample extends App {
  val n = 10
  println(s"Fibonacci of $n (Top-Down): ${FibonacciTopDown.fibonacci(n)}")
  println(s"Fibonacci of $n (Bottom-Up): ${FibonacciBottomUp.fibonacci(n)}")
}
