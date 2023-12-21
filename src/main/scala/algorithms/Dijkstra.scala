package algorithms

import scala.collection.mutable

case class EdgeD(u: String, v: String, w: Int)
case class GraphD(vertices: Set[String], edges: List[EdgeD])

/**
 * Pros
 * - Finds the shortest path in graph with non-negative edges
 * - Both for directed and undirected graphs
 * - Efficient fro sparse graphs with priority queue
 * - For routing, network, social
 * - Easy implementation
 * Cons
 * - Don't work with negative
 * - Bad for dense graphs
 * - Costs a memory
 * - Find path from single source to all nodes, for all to all it is inefficient
 * - Bad for dynamic graphs, update triggers recompute
 * - Depends on priority queue
 *
 * Can't add 100 to all nodes
 * Can't replace all nodes as 1, to use dfs
 */

object Dijkstra extends App {

  def algo(graph: GraphD, start: String, end: String): (Int, mutable.Map[String, String]) = {
    val distances = mutable.Map(graph.vertices.map(v => v -> (if (v == start) 0 else Int.MaxValue)).toSeq: _*)
    val previous = mutable.Map[String, String]()
    val queue = mutable.PriorityQueue[(String, Int)]()(Ordering.by(-_._2))

    distances.foreach { case (vertex, distance) =>
      queue.enqueue((vertex, distance))
    }

    while (queue.nonEmpty) {
      val (currentVertex, _) = queue.dequeue()

      // Relaxation process
      graph.edges.filter(_.u == currentVertex).foreach { edge =>
        val newDistance = distances(currentVertex) + edge.w
        if (newDistance < distances(edge.v)) {
          distances(edge.v) = newDistance
          previous(edge.v) = currentVertex
          // Since mutable.PriorityQueue does not support decrease-key,
          // reinsert the vertex with the updated distance.
          queue.enqueue((edge.v, newDistance))
        }
      }
    }

    (distances(end), previous)
  }

  def getPath(previous: mutable.Map[String, String], end: String): List[String] = {
    var path = List(end)
    while (previous.contains(path.head)) {
      path ::= previous(path.head)
    }
    path
  }

  val testGraph = GraphD(
    vertices = Set("A", "B", "C", "D", "E"),
    edges = List(
      EdgeD("A", "B", 6),
      EdgeD("A", "D", 1),
      EdgeD("B", "C", 5),
      EdgeD("B", "D", 2),
//      EdgeD("D", "B", 2),
      EdgeD("B", "E", 2),
      EdgeD("D", "E", 1),
      EdgeD("E", "C", 5),
    )
  )

  val (start, end) = ("A", "C")
  val (distanceToEnd, previousVertices) = algo(testGraph, start, end)
  println(s"Shortest distance from $start to $end is $distanceToEnd")
  println("Path taken: " + getPath(previousVertices, end).mkString(" -> "))
}
