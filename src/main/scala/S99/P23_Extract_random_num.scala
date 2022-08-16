package S99

import scala.util.Random

object P23_Extract_random_num extends App {

  def randomSelect[A](n: Int, l: List[A]): List[A] = {
    if (n > 0) {
      val idx = Random.nextInt(l.length - 1)

      val (el, ls) = l.splitAt(idx) match {
        case (l, rh :: rt) => (rh, l ::: rt)
      }
      el :: randomSelect(n - 1, ls)
    } else Nil
  }

  println("actual\t\t", randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
}
