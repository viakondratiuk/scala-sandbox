package algorithms

import scala.util.Random

class Graph(var adjList: Map[Int, List[Int]]) {

  def contract(edge: (Int, Int)): Unit = {
    val (u, v) = edge

    // Step 1: Update the adjacency list of u by merging the list of v into u
    adjList = adjList.updated(u, adjList(u) ++ adjList(v).filter(_ != u))

    // Step 2: Remove v from the graph
    adjList -= v

    // Step 3: Update all references of v to u in the entire adjacency list
    //         and remove any self-loops created in the process
    adjList = adjList.map { case (vertex, neighbors) =>
      val updatedNeighbors = neighbors.map {
        case `v` => u
        case other => other
      }.filter(_ != vertex)  // Removing self-loops
      (vertex, updatedNeighbors)
    }

    // Step 4: Optional - Remove duplicate edges if you don't want multi-edges
    adjList = adjList.mapValues(_.distinct).toMap
  }

  def randomEdge(): (Int, Int) = {
    val u = adjList.keys.toList(Random.nextInt(adjList.size))
    val v = adjList(u)(Random.nextInt(adjList(u).size))
    (u, v)
  }

  def numberOfEdges: Int = adjList.values.flatten.size / 2
}


object KargerAlgorithm extends App {
  val adjList = Map(
    1 -> List(2, 3, 4),
    2 -> List(1, 3),
    3 -> List(1, 2, 4),
    4 -> List(1, 3)
  )

  // 1 -- 2
  // | \  |
  // |  \ |
  // 4 -- 3

  // 1-2
  // | \
  // |  \
  // 4 -- 3

  // 1-2-3
  //     |
  //     4

  // 1-2 -- 3-4

  def kargerMinCut(graph: Graph): Int = {
    while (graph.adjList.size > 2) {
      val edge = graph.randomEdge()
      graph.contract(edge)
    }
    graph.numberOfEdges
  }


  val graph = new Graph(adjList)
  val minCut = kargerMinCut(graph)
  println(s"The minimum cut has $minCut edges.")
  println(s"The graph is ${graph.adjList}.")
}
