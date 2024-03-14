package algorithms


case class HuffmanNode(char: Option[Char], frequency: Int, left: Option[HuffmanNode], right: Option[HuffmanNode])

object HuffmanCode {
  def buildTree(charFreqs: Map[Char, Int]): Option[HuffmanNode] = {
    implicit val ordering: Ordering[HuffmanNode] = Ordering.by(-_.frequency)
    val pq = scala.collection.mutable.PriorityQueue(
      charFreqs.map { case (c, f) => HuffmanNode(Some(c), f, None, None) }.toSeq: _*
    )

    while (pq.size > 1) {
      val left = pq.dequeue()
      val right = pq.dequeue()
      pq.enqueue(HuffmanNode(None, left.frequency + right.frequency, Some(left), Some(right)))
    }

    if (pq.nonEmpty) Some(pq.dequeue()) else None
  }

  def generateCodes(node: Option[HuffmanNode], prefix: String = ""): Map[Char, String] = node match {
    case Some(HuffmanNode(Some(char), _, None, None)) => Map(char -> prefix)
    case Some(HuffmanNode(_, _, left, right)) =>
      generateCodes(left, prefix + "0") ++ generateCodes(right, prefix + "1")
    case None => Map.empty
  }
}

object HuffmanCodeExample extends App {
  val testString = "this is an example for huffman encoding"
//  val testString = "aaaabbbcd"
  val charFreqs = testString.groupBy(identity).mapValues(_.length)

  val tree = HuffmanCode.buildTree(charFreqs)
  val codes = HuffmanCode.generateCodes(tree)

  println("Character codes:")
  codes.foreach { case (char, code) =>
    println(s"$char: $code")
  }
}
