import scala.language.higherKinds

trait Functor[F[_]] {
  def map[A,B](fa: F[A])(f: A => B): F[B]
}

trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]
  def flatMap[A,B](ma: F[A])(f: A => F[B]): F[B]

  override def map[A, B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(a => unit(f(a)))
}

val optionMonad = new Monad[Option] {
  override def unit[A](a: => A): Option[A] = Some(a)
  override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma.flatMap(f)
}

// associative law
def one(x: Int) = x + 1
def two(x: Int) = x + 2
// x.flatMap(f).flatMap(g) = x.flatMap(a => f(a).flatMap(g))
Some(1).flatMap(x => Some(one(x))).flatMap(x => Some(two(x))) ==
  Some(1).flatMap(x => Some(x + 1).flatMap(x => Some(x + 2)))

// unit law
Some(1).flatMap(x => Some(one(x))) == Some(one(1))
Some(1).flatMap(x => Some(x)) == Some(1)


///////////////

val list1 = List(1, 2, 3)
val list2 = List("a", "b", "c")

var resultList = List[(Int, String)]()

for (i <- list1) {
  for (j <- list2) {
    resultList = resultList :+ (i, j)
  }
}

resultList

val list11 = List(1, 2, 3)
val list22 = List("a", "b", "c")

val resultListMonad = for {
  i <- list11
  j <- list22
} yield (i, j)

println(resultListMonad)