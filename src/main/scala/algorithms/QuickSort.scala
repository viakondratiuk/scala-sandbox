package algorithms

object QuickSort extends App {

  def partition(a: Array[Int], start: Int, end: Int): Int = {
    val pivot = a(end)
    var i = start - 1

    for (j <- start until end) {
      if (a(j) < pivot) {
        i += 1
        val tmp = a(i)
        a(i) = a(j)
        a(j) = tmp
      }
    }

    val tmp = a(i + 1)
    a(i + 1) = a(end)
    a(end) = tmp

    i + 1
  }

  def sort(a: Array[Int]): Array[Int] = {
    def _sort(start: Int, end: Int): Unit = {
      if (start < end) {
        val pivotIndex = partition(a, start, end)
        _sort(start, pivotIndex - 1)
        _sort(pivotIndex + 1, end)
      }
    }

    _sort(0, a.length - 1)
    a
  }

  def functional(a: Array[Int]): Array[Int] = {
    if (a.length == 1) a
    else {
      val pivot = a(0)
      val (l, r) = a.tail.partition(_ < pivot)
      functional(l) ++ Array(pivot) ++ functional(r)
    }
  }

  val a9 = Array(5, 3, 6, 4, 1, 8, 2, 7, 9)
  val a8 = Array(5, 3, 6, 4, 1, 8, 2, 7)

//  println(s"quick sort: ${sort(a9).mkString(", ")}")
//  println(s"quick sort: ${sort(a8).mkString(", ")}")

  println(s"quick sort func: ${sort(a8).mkString(", ")}")
  println(s"quick sort func: ${sort(a9).mkString(", ")}")
}
