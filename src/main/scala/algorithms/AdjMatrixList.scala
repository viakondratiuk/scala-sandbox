package algorithms

object AdjMatrixList extends App {
  class AdjacencyMatrix(size: Int) {
    private val matrix: Array[Array[Int]] = Array.ofDim[Int](size, size)

    def addEdge(u: Int, v: Int): Unit = {
      matrix(u)(v) = 1
    }

    def removeEdge(u: Int, v: Int): Unit = {
      matrix(u)(v) = 0
    }

    def hasEdge(u: Int, v: Int): Boolean = {
      matrix(u)(v) == 1
    }

    def getNeighbors(vertex: Int): List[Int] = {
      (0 until size).filter(matrix(vertex)(_) == 1).toList
    }

    override def toString: String = {
      matrix.map(_.mkString(" ")).mkString("\n")
    }
  }

  class AdjacencyList(size: Int) {
    private val list: Array[List[Int]] = Array.fill(size)(Nil)

    def addEdge(u: Int, v: Int): Unit = {
      list(u) = v :: list(u)
    }

    def removeEdge(u: Int, v: Int): Unit = {
      list(u) = list(u).filterNot(_ == v)
    }

    def hasEdge(u: Int, v: Int): Boolean = {
      list(u).contains(v)
    }

    def getNeighbors(vertex: Int): List[Int] = {
      list(vertex)
    }

    override def toString: String = {
      list.zipWithIndex.map { case (neighbors, vertex) =>
        s"$vertex -> ${neighbors.mkString(", ")}"
      }.mkString("\n")
    }
  }

  val graphMatrix = new AdjacencyMatrix(4)
  graphMatrix.addEdge(0, 1)
  graphMatrix.addEdge(1, 2)
  graphMatrix.addEdge(2, 3)
  graphMatrix.addEdge(3, 0)
//  println(graphMatrix.getNeighbors(0))
  println(graphMatrix.toString)

  println("===================")

  val graphList = new AdjacencyList(4)
  graphList.addEdge(0, 1)
  graphList.addEdge(1, 2)
  graphList.addEdge(2, 3)
  graphList.addEdge(3, 0)
  println(graphList.toString)


}
