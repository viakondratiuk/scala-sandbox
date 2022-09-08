package AdventOfCode

import scala.annotation.tailrec

object Day2Part2 extends App {

  def lazyRecursion(source: Iterator[String]): Int = {
    @tailrec
    def _lazyRecursion(command: String, hor: Int, depth: Int, aim: Int): Int = {
      if (command.isEmpty) return hor * depth

      val Array(pos, delta) = command.split(" ")
      val _command = if (source.nonEmpty) source.next() else ""

      pos match {
        case "down"    => _lazyRecursion(_command, hor, depth, aim + delta.toInt)
        case "up"      => _lazyRecursion(_command, hor, depth, aim - delta.toInt)
        case "forward" => _lazyRecursion(_command, hor + delta.toInt, depth + aim * delta.toInt, aim)
      }
    }

    val command = source.next()
    _lazyRecursion(command, 0, 0, 0)
  }

  val source = scala.io.Source.fromResource("day2.txt")
  val lines = source.getLines()
  println(lazyRecursion(lines))
  source.close()
}
