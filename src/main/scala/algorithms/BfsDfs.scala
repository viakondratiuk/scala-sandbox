package algorithms
import scala.collection.mutable.Queue

class BreadthFirstSearch {
  def bfs(graph: AdjacencyList, startVertex: Int): Unit = {
    val visited = Array.fill[Boolean](graph.size)(false)
    val queue = Queue[Int]()

    visited(startVertex) = true
    queue.enqueue(startVertex)

    while (queue.nonEmpty) {
      val vertex = queue.dequeue()
      print(s"$vertex -> ")

      for (neighbor <- graph.getNeighbors(vertex)) {
        if (!visited(neighbor)) {
          visited(neighbor) = true
          queue.enqueue(neighbor)
        }
      }
    }
  }
}

class DepthFirstSearch {
  def dfs(graph: AdjacencyList, startVertex: Int): Unit = {
    val visited = Array.fill[Boolean](graph.size)(false)

    def dfsRecursive(vertex: Int): Unit = {
      visited(vertex) = true
      print(s"$vertex -> ")

      for (neighbor <- graph.getNeighbors(vertex)) {
        if (!visited(neighbor)) {
          dfsRecursive(neighbor)
        }
      }
    }

    dfsRecursive(startVertex)
  }
}



object BfsDfs extends App {
  /*
          0
        /   \
       1     2
     /   \    |
    3     4   3
     \___/

   */
  val graph = new AdjacencyList(5)
  graph.addEdge(0, 1)
  graph.addEdge(0, 2)
  graph.addEdge(1, 3)
  graph.addEdge(1, 4)
  graph.addEdge(2, 3)
  graph.addEdge(3, 4)

  val bfs = new BreadthFirstSearch()
  val dfs = new DepthFirstSearch()

  println("BFS:")
  bfs.bfs(graph, 0)

  println("\nDFS:")
  dfs.dfs(graph, 0)
}
