package HRMS

object Lazy_CallByName extends App {

  /**
   * Lazy val is evaluated once when called, used for caching
   *
   * By Name is evaluated every time when called, if no call - no evaluation
   *
   */

  private lazy val x = {
    println("Im calculated when called")
    42
  }
  println(x)
  println(x)

  val y = {
    println("Im calculated when compiler see me")
    56
  }
  println("Not Lazy")
  println(y)
  println(y)

  println("=============================================")

  def callByValue(x: Long): Unit = {
    println(s"by value $x")
    println(s"by value $x")
  }

  def callByName(x: => Long): Unit = {
    println(s"by name $x")
    println(s"by name $x")
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def test(x: Int, y: => Int) = println(x)
  test(5, infinite())
//  test(infinite(), 5)
}
