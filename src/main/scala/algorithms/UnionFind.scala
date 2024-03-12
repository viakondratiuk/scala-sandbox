package algorithms

class UnionFind(n: Int) {
  private val parent = Array.tabulate(n)(identity)

  // Find the root of the set in which element `x` is located
  def find(x: Int): Int = {
    if (parent(x) != x) {
      // Path compression: make the found root the direct parent of `x`
      parent(x) = find(parent(x))
    }
    parent(x)
  }

  def union(x: Int, y: Int): Unit = {
    val rootX = find(x)
    val rootY = find(y)
    if (rootX != rootY) {
      // Attach the tree of `rootY` to the tree of `rootX`
      parent(rootY) = rootX
    }
    println(parent.mkString(", "))
  }
}

object UnionFindExample extends App {
  val uf = new UnionFind(10) // Create a Union-Find structure for 10 elements

  uf.union(2, 3)
  uf.union(3, 4)
  uf.union(5, 6)

  // Find root of elements
  println(uf.find(4)) // Output: 2, because 4 is connected to 2 through 3
  println(uf.find(5)) // Output: 5, because 5's root is 5 itself

  println(uf.find(2) == uf.find(4)) // Output: true, because their root is the same
  println(uf.find(2) == uf.find(5)) // Output: false, different roots
}
