package algorithms.dp

case class Item(value: Int, weight: Int)

object Knapsack {
  def solve(items: Array[Item], W: Int): (Int, Set[Item]) = {

    val n = items.length
    val dp = Array.ofDim[Int](n + 1, W + 1)
    val keep = Array.ofDim[Boolean](n + 1, W + 1)

    // Fill DP table
    for (i <- 1 to n) {
      for (w <- 0 to W) {
        if (items(i - 1).weight <= w) {
          if (items(i - 1).value + dp(i - 1)(w - items(i - 1).weight) > dp(i - 1)(w)) {
            dp(i)(w) = items(i - 1).value + dp(i - 1)(w - items(i - 1).weight)
            keep(i)(w) = true
          } else {
            dp(i)(w) = dp(i - 1)(w)
          }
        } else {
          dp(i)(w) = dp(i - 1)(w)
        }
      }
    }

    // Reconstruct set of items
    var includedItems = Set[Item]()
    var k = W
    for (i <- n to 1 by -1 if k >= 0) {
      if (keep(i)(k)) {
        includedItems += items(i - 1)
        k -= items(i - 1).weight
      }
    }

    (dp(n)(W), includedItems)
  }
}

object KnapsackExample extends App {
  // Define items and capacity
  val items = Array(
    Item(value = 130, weight = 10),
    Item(value = 100, weight = 20),
    Item(value = 120, weight = 30)
  )
  val maxWeight = 50

  val (maxValue, includedItems) = Knapsack.solve(items, maxWeight)
  println(s"Maximum Value: $maxValue")
  println("Items included in the knapsack for maximum value:")
  includedItems.foreach { item =>
    println(s"Item with value: ${item.value} and weight: ${item.weight}")
  }

}
