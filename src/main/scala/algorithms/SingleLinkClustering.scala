package algorithms


object SingleLinkClustering {
  def cluster(points: Int, edges: List[EdgeW], threshold: Double): Unit = {
    val uf = new UnionFind(points)
    val sortedEdges = edges.sortBy(_.weight)

    for (edge <- sortedEdges if edge.weight <= threshold) {
      if (uf.find(edge.u) != uf.find(edge.v)) {
        println(s"Merging ${edge.u} and ${edge.v} at distance ${edge.weight}")
        uf.union(edge.u, edge.v)
      }
    }
  }
}

object SingleLinkClusteringExample extends App {
  // Example usage: points labeled 0 to 4, edges with distances
  val edges = List(EdgeW(0, 1, 1), EdgeW(1, 2, 2), EdgeW(2, 3, 3), EdgeW(3, 4, 4), EdgeW(0, 4, 5))
  SingleLinkClustering.cluster(5, edges, 3.0) // Clustering with threshold 3.0
}
