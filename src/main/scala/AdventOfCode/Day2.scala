package AdventOfCode

import scala.annotation.tailrec

object Day2 extends App {
  def lazyRecursion(source: Iterator[String]): Int = {
    @tailrec
    def _lazyRecursion(command: String, hor: Int, depth: Int): Int = {
      if (command.isEmpty) return hor * depth

      val Array(pos, delta) = command.split(" ")
      val _command = if (source.nonEmpty) source.next() else ""

      pos match {
        case "forward" => _lazyRecursion(_command, hor + delta.toInt, depth)
        case "down"    => _lazyRecursion(_command, hor, depth + delta.toInt)
        case "up"      => _lazyRecursion(_command, hor, depth - delta.toInt)
      }
    }

    val command = source.next()
    _lazyRecursion(command, 0, 0)
  }

  // 2029815 - 2039256 is too low
  val source = scala.io.Source.fromResource("day2.txt")
  val lines = source.getLines()
  println(lazyRecursion(lines))
  source.close()
}
