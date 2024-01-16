package algorithms

import scala.collection.mutable
import scala.io.Source


class MedianMaintenance {
  private val lowerHalf = mutable.PriorityQueue.empty[Int]
  private val upperHalf = mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)

  private def rebalanceHeaps(): Unit = {
    if (lowerHalf.size > upperHalf.size + 1) {
      upperHalf.enqueue(lowerHalf.dequeue())
    } else if (upperHalf.size > lowerHalf.size) {
      lowerHalf.enqueue(upperHalf.dequeue())
    }
  }

  def addNumber(num: Int): Unit = {
    if (lowerHalf.isEmpty || num <= lowerHalf.head) {
      lowerHalf.enqueue(num)
    } else {
      upperHalf.enqueue(num)
    }

    rebalanceHeaps()
  }

  def findMedian(): Double = {
    if (lowerHalf.size == upperHalf.size) {
      (lowerHalf.head + upperHalf.head) / 2.0
    } else {
      lowerHalf.head.toDouble
    }
  }
}


object MedianMaintenanceTest extends App {
  val mm = new MedianMaintenance()

  val source = Source.fromResource("numbers.txt")
  try {
    source.getLines().foreach { line =>
      val num = line.toInt
      mm.addNumber(num)
      println(s"Added: $num, Median: ${mm.findMedian()}")
    }
  } finally {
    source.close() // Make sure to close the source to avoid resource leaks
  }
}
