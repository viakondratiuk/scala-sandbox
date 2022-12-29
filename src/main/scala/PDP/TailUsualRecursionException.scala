package PDP

object TailUsualRecursionException extends App {

  def usualWithStack(n: Int): Int = {
    if (n < 1) throw new Exception("usual!")
    else usualWithStack(n - 1) + 1
  }

  def tailWithoutStack(n: Int): Int = {
    if (n < 1) throw new Exception("tail!")
    else tailWithoutStack(n - 1)
  }

  usualWithStack(100)
//  tailWithoutStack(100)
}
