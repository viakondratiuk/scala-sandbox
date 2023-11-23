package algorithms


class AdjacencyMatrix(size: Int) {
  private val adjMatrix: Array[Array[Int]] = Array.ofDim[Int](size, size)

  def addEdge(u: Int, v: Int): Unit = {
    adjMatrix(u)(v) = 1
  }

  def removeEdge(u: Int, v: Int): Unit = {
    adjMatrix(u)(v) = 0
  }

  def hasEdge(u: Int, v: Int): Boolean = {
    adjMatrix(u)(v) == 1
  }

  def getNeighbors(vertex: Int): List[Int] = {
    (0 until size).filter(adjMatrix(vertex)(_) == 1).toList
  }

  override def toString: String = {
    adjMatrix.map(_.mkString(" ")).mkString("\n")
  }
}


class AdjacencyList(val size: Int) {
  private val adjList: Array[List[Int]] = Array.fill(size)(Nil)

  def addEdge(u: Int, v: Int): Unit = {
    adjList(u) = v :: adjList(u)
  }

  def removeEdge(u: Int, v: Int): Unit = {
    adjList(u) = adjList(u).filterNot(_ == v)
  }

  def hasEdge(u: Int, v: Int): Boolean = {
    adjList(u).contains(v)
  }

  def getNeighbors(vertex: Int): List[Int] = {
    adjList(vertex)
  }

  override def toString: String = {
    adjList.zipWithIndex.map { case (neighbors, vertex) =>
      s"$vertex -> ${neighbors.mkString(", ")}"
    }.mkString("\n")
  }
}


object AdjMatrixList extends App {
  val graphMatrix = new AdjacencyMatrix(4)
  graphMatrix.addEdge(0, 1)
  graphMatrix.addEdge(1, 2)
  graphMatrix.addEdge(2, 3)
  graphMatrix.addEdge(3, 0)
  println(graphMatrix.toString)

  println("===================")

  val graphList = new AdjacencyList(4)
  graphList.addEdge(0, 1)
  graphList.addEdge(1, 2)
  graphList.addEdge(2, 3)
  graphList.addEdge(3, 0)
  println(graphList.toString)


  /*
  ### Adjacency List

  #### Pros:
  1. Space-efficient for sparse graphs.
  2. Faster add/remove operations for vertices/edges.
  3. Efficient for iterating over adjacent vertices.
  4. Suitable for dynamically changing graph sizes.

  #### Cons:
  1. Slower to check for the existence of a specific edge.
  2. Less efficient for very dense graphs.

  #### Best Use Case:
  - Ideal for sparse graphs and when frequent graph modifications or adjacency queries are needed.

  ### Adjacency Matrix

  #### Pros:
  1. Quick edge existence checks.
  2. More efficient for dense graphs.
  3. Simple implementation for some algorithms.
  4. Convenient for storing edge weights.

  #### Cons:
  1. Space-intensive, especially for sparse graphs.
  2. Slower for iterating over neighbors.
  3. Less efficient for adding/removing vertices.

  #### Best Use Case:
  - Optimal for dense graphs or when edge existence checks are common, and graph size is relatively static.
   */
}
