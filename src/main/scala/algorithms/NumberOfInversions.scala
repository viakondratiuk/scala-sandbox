package algorithms

import scala.collection.mutable.ArrayBuffer

object NumberOfInversions extends App {

  // [5, 3, 6, 4, 1, 8, 2, 7]
  // [_, 3, 6, 4, 1, 8, 2, 7]
  def n_2(l: List[Int]): Int = {
    val inversion = for {
      i <- l.indices
      j <- i until l.length
    } yield if (l(i) > l(j)) 1 else 0

    inversion.sum
  }

  // [5, 3, 6, 4, 1, 8, 2, 7]
  // [3, 4, 5, 6]  [1, 2, 8, 7]
  // [4, 3, 2, 1, 3, 2, 1]
  def n_log_n(input: Array[Int]): (Array[Int], Int) = {

    def mergeSort(input: Array[Int]): (Array[Int], Int) =
      if (input.length <= 1) (input, 0)
      else {
        val (left, right) = input.splitAt(input.length / 2)

        val (sortedLeft, invLeft) = mergeSort(left)
//        println(s"[${sortedLeft.mkString(",")}], $invLeft")
        val (sortedRight, invRight) = mergeSort(right)
//        println(s"[${sortedRight.mkString(",")}], $invRight")
        val (sorted, invSplit) = merge(sortedLeft, sortedRight)
//        println(s"[${sorted.mkString(",")}], $invLeft, $invRight, $invSplit")

        (sorted, invLeft + invRight + invSplit)
      }

    def merge(left: Array[Int], right: Array[Int]): (Array[Int], Int) = {
      var (i, j) = (0, 0)
      var inv = 0
      val sorted = ArrayBuffer[Int]()

      while (i < left.length && j < right.length) {
        if (left(i) > right(j)) {
          sorted += right(j)
          j += 1
          inv += left.length - i
        } else {
          sorted += left(i)
          i += 1
        }
      }

      sorted ++= left.drop(i)
      sorted ++= right.drop(j)

      (sorted.toArray, inv)
    }

    mergeSort(input)
  }

  val l13 = List(5, 3, 6, 4, 1, 8, 2, 7)
  val l4 = List(2, 1, 4, 6, 3, 5)
  println(s"n_2: ${n_2(l4)}")

  val a13 = Array(5, 3, 6, 4, 1, 8, 2, 7)
  val a1 = Array(2, 1, 4, 6, 3, 5)
  val (sorted, inv) = n_log_n(a13)
  println(s"sorted: ${sorted.mkString(", ")}")
  println(s"n_log_n: $inv")
}
