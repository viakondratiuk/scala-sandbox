//class A {
//  private[this] val aPrivate = "A"
//
//  def a(aaa: A) = println(aaa.aPrivate)
//}
//
//val a1 = new A
//val a2 = new A
//
//a1.a(a2)

//val curry: (Int => Int => Int) = (x: Int) => (y: Int) = x + y
//
//
//def func(x: Int, y: Int) = {
//  x + y
//}

//for {
//  i <- List(1,2,3)
//  j <- List(1,2,3)
//  if j==i
//} yield i + j
//
//List(1,2,3).flatMap { i =>
//  List(1,2,3).filter(j => i == j).map { j => i + j}
//}

//trait MyOption[+A] {
//  def get(): A
//}
//
//class MySome[A](v: A) extends MyOption[A] {
//  override def get(): A = v
//}
//
//object MyNone extends MyOption[Nothing] {
//  override def get(): Nothing = throw NoSuchElementException
//}
//
//val myOption: MyOption[Int] = MyNone

val l: List[AnyVal] = List(1.0, 1)
1 :: l