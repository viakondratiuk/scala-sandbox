package algorithms

import scala.collection.mutable

case class EdgeW(u: Int, v: Int, weight: Int)

case class GraphW(vertices: Set[Int], edges: List[EdgeW]) {

  def primMST(): List[EdgeW] = {
    implicit val ord: Ordering[EdgeW] = (x: EdgeW, y: EdgeW) => y.weight compare x.weight
    val pq = mutable.PriorityQueue.empty[EdgeW]

    val visited = scala.collection.mutable.Set[Int]()
    var mst = List[EdgeW]()

    def addEdge(vertex: Int): Unit = {
      visited += vertex
      edges.filter(edge => (edge.u == vertex || edge.v == vertex) && (!visited(edge.u) || !visited(edge.v)))
        .foreach(pq.enqueue(_))
    }

    addEdge(vertices.head)

    while (visited.size < vertices.size && pq.nonEmpty) {
      val edge = pq.dequeue()
      if (!visited(edge.u) || !visited(edge.v)) {
        mst = edge :: mst
        if (!visited(edge.u)) addEdge(edge.u) else addEdge(edge.v)
      }
    }
    mst
  }
}


object PrimMSTExample extends App {
  val vertices = Set(1, 2, 3, 4)
  val edges = List(EdgeW(1, 2, 1), EdgeW(2, 3, 4), EdgeW(1, 3, 3), EdgeW(3, 4, 2), EdgeW(2, 4, 5))
  val mst = GraphW(vertices, edges).primMST()

  println(s"MST Cost: ${mst.map(_.weight).sum}")
  println("MST Edges:")
  mst.foreach(edge => println(s"${edge.u} -> ${edge.v}"))
}
