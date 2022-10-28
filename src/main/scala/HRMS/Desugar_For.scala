package HRMS

object Desugar_For extends App {

  case class Result[A](result: A) {
    def foreach(f: A => Unit): Unit = f(result)
    def map[B](f: A => B): Result[B] = Result(f(result))
    def flatMap[B](f: A => Result[B]): Result[B] = f(result)
    def withFilter(f: A => Boolean): Result[_] = if (f(result)) this else EmptyResult
  }
  object EmptyResult extends Result[Null](null)


  val result = Result(42)
  val anotherResult = Result(100)
  for {
    res <- result
    if res == 42
  } yield res + 4

}
