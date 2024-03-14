package algorithms

// Edge and Graph definitions
case class GraphK(vertices: Int, edges: List[EdgeW]) {

  // Union-Find Data Structure
  class UnionFind(n: Int) {
    private val parent = Array.tabulate(n)(identity)
    private val rank = Array.fill(n)(0)

    def find(x: Int): Int = {
      if (parent(x) != x) parent(x) = find(parent(x))
      parent(x)
    }

    def union(x: Int, y: Int): Boolean = {
      val xRoot = find(x)
      val yRoot = find(y)
      if (xRoot == yRoot) {
        return false
      }

      if (rank(xRoot) < rank(yRoot)) parent(xRoot) = yRoot
      else if (rank(xRoot) > rank(yRoot)) parent(yRoot) = xRoot
      else {
        parent(yRoot) = xRoot
        rank(xRoot) += 1
      }
      true
    }
  }

  def kruskalMST(): List[EdgeW] = {
    var mst = List[EdgeW]()
    val sortedEdges = edges.sortBy(_.weight)
    val uf = new UnionFind(vertices)

    for (edge <- sortedEdges) {
      if (uf.union(edge.u, edge.v)) {
        mst = edge :: mst
      }
    }
    mst
  }
}

object KruskalMSTExample extends App {
  val vertices = 4
  val edges = List(
    EdgeW(0, 1, 10),
    EdgeW(0, 2, 6),
    EdgeW(0, 3, 5),
    EdgeW(1, 3, 15),
    EdgeW(2, 3, 4)
  )
  val graph = GraphK(vertices, edges)
  val mst = graph.kruskalMST()

  println("Edges in the MST:")
  mst.foreach(edge => println(s"${edge.u} - ${edge.v} with weight ${edge.weight}"))
}
