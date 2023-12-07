package algorithms
import scala.collection.mutable

object SccKosaraju extends App {

  case class Edge(u: Int, v: Int)
  case class Graph(vertices: Set[Int], edges: List[Edge]) {
    val adjList: Map[Int, List[Int]] = edges.groupBy(_.u).mapValues(_.map(_.v)).toMap

    def transpose(): Graph = {
      val transposedEdges = edges.map(edge => Edge(edge.v, edge.u))
      Graph(vertices, transposedEdges)
    }
  }

  object Kosaraju {
    private val stack = mutable.Stack[Int]()
    private var visited: Array[Boolean] = _
    private var visitedTransposed: Array[Boolean] = _

    def fillOrder(graph: Graph, v: Int): Unit = {
      visited(v) = true
      graph.adjList.getOrElse(v, List()).foreach { u =>
        if (!visited(u)) fillOrder(graph, u)
      }
      stack.push(v)
    }

    def dfs(graph: Graph, v: Int, scc: mutable.ListBuffer[Int]): Unit = {
      visitedTransposed(v) = true
      scc += v
      graph.adjList.getOrElse(v, List()).foreach { u =>
        if (!visitedTransposed(u)) dfs(graph, u, scc)
      }
    }

    def printSCC(graph: Graph): Unit = {
      visited = Array.fill(graph.vertices.size)(false)
      visitedTransposed = Array.fill(graph.vertices.size)(false)

      graph.vertices.foreach { v =>
        if (!visited(v)) fillOrder(graph, v)
      }

      val transposed = graph.transpose()

      while (stack.nonEmpty) {
        val v = stack.pop()
        if (!visitedTransposed(v)) {
          val scc = mutable.ListBuffer[Int]()
          dfs(transposed, v, scc)
          println("SCC: " + scc.mkString(", "))
        }
      }
    }
  }

  // Example usage
  val vertices = Set(0, 1, 2, 3, 4, 5, 6, 7)
  val edges = List(Edge(0, 1), Edge(1, 2), Edge(2, 3), Edge(2, 4), Edge(3, 0), Edge(4, 5), Edge(5, 6), Edge(6, 4), Edge(6, 7))
  val graph = Graph(vertices, edges)

  println("Strongly Connected Components:")
  Kosaraju.printSCC(graph)
}
