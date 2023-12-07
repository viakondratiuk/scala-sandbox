package algorithms

import scala.annotation.tailrec

object TopologicalFunctional extends App {

  def topologicalSort[N](predecessors: Map[N, Set[N]])(implicit ordering: Ordering[N]): Seq[N] = {
    @tailrec
    def rec(p: Map[N, Set[N]], sorted: Seq[N]): Seq[N] = {
      if (p.isEmpty) sorted
      else {
        val next = p.filter(_._2.isEmpty).keys.toSeq.sorted
        require(next.nonEmpty, "Detected a cycle in the given graph")
        rec((p -- next).mapValues(_ -- next), sorted ++ next)
      }
    }

    rec(predecessors, Seq.empty)
  }

  val graph1 = Map[Char, Set[Char]](
    'A' -> Set('B', 'C'),
    'B' -> Set('D'),
    'C' -> Set('D'),
    'D' -> Set()
  )

  val result1 = topologicalSort(graph1)
  println(result1)  // Expected Output: Seq('D', 'B', 'C', 'A') or any valid topological order

  val graph2 =  Map[Char, Set[Char]](
    'A' -> Set('B'),
    'B' -> Set('C'),
    'C' -> Set('A')
  )

  try {
    val result2 = topologicalSort(graph2)
    println(result2)
  } catch {
    case e: IllegalArgumentException => println(e.getMessage)
  }

  val graph3 =  Map[Char, Set[Char]](
    'A' -> Set(),
    'B' -> Set('A'),
    'C' -> Set('B'),
    'D' -> Set('B', 'C'),
    'E' -> Set('D')
  )

  val result3 = topologicalSort(graph3)
  println(result3) // Expected Output: A valid topological order, such as Seq('A', 'B', 'C', 'D', 'E')

  val graph4 =  Map[Char, Set[Char]](
    'A' -> Set(),
    'B' -> Set('A'),
    'C' -> Set(),
    'D' -> Set()
  )

  val result4 = topologicalSort(graph4)
  println(result4) // Expected Output: A valid order including all nodes, such as Seq('A', 'B', 'C', 'D')
}
