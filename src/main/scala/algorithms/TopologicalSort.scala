package algorithms
import scala.collection.mutable


case class Edge(u: Int, v: Int)
case class Graph(vertices: Set[Int], edges: List[Edge])

object TopologicalSort extends App {

  def kahn(graph: Graph): List[Int] = {
    var inDegree = graph.vertices.map(v => (v, graph.edges.count(_.v == v))).toMap
    val queue = mutable.Queue[Int]()
    var result = List[Int]()

    inDegree.filter(_._2 == 0).foreach(d => queue.enqueue(d._1))

    while (queue.nonEmpty) {
      val v = queue.dequeue()
      result =  result :+ v
      graph.edges.filter(_.u == v).foreach { edge =>
        val nextVertex = edge.v
        inDegree = inDegree.updated(nextVertex, inDegree(nextVertex) - 1)
        if (inDegree(nextVertex) == 0) {
          queue.enqueue(nextVertex)
        }
      }
    }

    if (result.size != graph.vertices.size) {
      throw new IllegalArgumentException("Graph has at least one cycle")
    }

    result
  }

  def dfs(graph: Graph): List[Int] = {
    val visited = mutable.Set[Int]()
    val stack = mutable.Stack[Int]()

    def recurse(v: Int): Unit = {
      visited.add(v)

      graph
        .edges
        .filter(_.u == v)
        .foreach { edge =>
          if (!visited.contains(edge.v)) {
            recurse(edge.v)
          }
        }

      stack.push(v)
    }

    graph.vertices.foreach { v =>
      if (!visited.contains(v)) {
        recurse(v)
      }
    }

    stack.toList
  }

  /*
    1 -> 2
    |  / ^
    v /  |
    3 <- 4
   */

  val testGraph = Graph(
    vertices = Set(1, 2, 3, 4),
    edges = List(
      Edge(1, 2),
      Edge(1, 3),
      Edge(3, 2),
      Edge(4, 2),
      Edge(4, 3)
    )
  )

  println(kahn(testGraph))
  println(dfs(testGraph))
}
