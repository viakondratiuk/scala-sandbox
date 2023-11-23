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

  /*
  ### Breadth-First Search (BFS)

  #### Pros:
  1. Finds the shortest path in unweighted graphs.
  2. Useful for level-order traversal.
  3. Effective for connectivity testing.
  4. Ideal for spreading processes like networking or social graphs.

  #### Cons:
  1. More memory-intensive, especially for wide graphs.
  2. Can be slower for deep target nodes.

  #### Best Use Case:
  - Suited for finding shortest paths and situations where closer nodes are prioritized.

  ### Depth-First Search (DFS)

  #### Pros:
  1. Memory-efficient, using less space.
  2. Efficiently explores deep paths.
  3. Useful for cycle detection and topological sorting.
  4. Ideal for solving puzzles or path-finding where the path length isn't a concern.

  #### Cons:
  1. Can be trapped in deep or infinite paths.
  2. Doesn't guarantee the shortest path in unweighted graphs.
  3. More complex for cycle detection.

  #### Best Use Case:
  - Optimal for situations requiring exhaustive search or when exploring as far as possible is essential.
   */
}
