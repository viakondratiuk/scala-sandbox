package algorithms

object RandomizedSelection extends App {

  def find_kth(a: Array[Int], k: Int): Int = {
    def partition(a: Array[Int]): Int = {
      def swap(a: Array[Int], i: Int, j: Int) = {
        val tmp = a(i)
        a(i) = a(j)
        a(j) = tmp
      }

      val pivot = a(a.length - 1)
      var i = -1
      for (j <- 0 until a.length - 1) {
        if (a(j) < pivot) {
          i += 1
          swap(a, i, j)
        }
      }

      i += 1
      swap(a, a.length - 1, i)

      i
    }

    val pivot = partition(a)
    if (k - 1 == pivot) a(pivot)
    else if (k - 1 < pivot) find_kth(a.slice(0, pivot), k)
    else find_kth(a.slice(pivot + 1, a.length), k - pivot - 1)
  }


  val l7 = Array(3, 5, 1, 8, 7 , 9, 4)
  val k = 6
  val v = find_kth(l7, k)
  println(s"$k element is $v")
}
