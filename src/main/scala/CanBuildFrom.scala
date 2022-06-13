object CanBuildFrom extends App {
  // Seq -> Map[_,_]
  val seqInt = Seq(1,2,3,4,5)

  val seqString = seqInt.map(n => n.toString + "s")

  val seqAsMap: Map[Int, String] =
    seqInt.map(n => n -> n.toString)(collection.breakOut) // single ops
  val seqAsMap_longWay: Map[Int, String] =
    seqInt.map(n => n -> n.toString) // 1th ops
      .toMap                         // 2th ops
  // Array -> Vector
  val array = Array(1,2,3)

  val vector: Vector[String] =
    array.map(n => n.toString)(collection.breakOut)

  val vector_longWay: Vector[String] =
    array.map(n => n.toString)
      .toVector

}
