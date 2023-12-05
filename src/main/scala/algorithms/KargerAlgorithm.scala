import scala.util.Random

case class Edge(u: Int, v: Int)
case class Graph(vertices: Set[Int], edges: List[Edge])

object Karger extends App  {

  def minCut(graph: Graph): Int = {
    var (vertices, edges) = (graph.vertices, graph.edges)

    while (vertices.size > 2) {
      val edge = edges(Random.nextInt(edges.size))
      edges = edges.filter(e => e != edge)
      println(s"Contracting edge: ${edge.u} - ${edge.v}") // Print the edge being contracted

      vertices -= edge.v
      edges = edges.map { e =>
        var (u, v) = (e.u, e.v)
        if (u == edge.v) u = edge.u
        if (v == edge.v) v = edge.u
        Edge(u, v)
      }.filter(e => e.u != e.v)

      println(s"Current vertices: $vertices") // Print current vertices
      println(s"Current edges: $edges") // Print current edges
    }

    edges.size
  }

  /*
  1 --- 2
  |   / |
  |  /  |
  | /   |
  3 --- 4
   */
  val testGraph = Graph(
    vertices = Set(1, 2, 3, 4),
    edges = List(Edge(1, 2), Edge(2, 3), Edge(3, 1), Edge(3, 4), Edge(2, 4))
  )

  /*
    1. 1-2, 2-3, 3-1
    2: 3 - 4, 2- 4
    3: 1 -2, 2 - 3, 3 - 1
   */
  println(minCut(testGraph))
}
