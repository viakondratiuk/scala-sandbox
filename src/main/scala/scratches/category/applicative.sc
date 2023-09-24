trait Functor[F[_]] {
  def pure[A](x: A): F[A]
  def map[A,B](fa: F[A])(f: A => B): F[B]
}

trait Applicative[F[_]] extends Functor[F] {
  def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]
  def map2[A, B, Z](fa: F[A], fb: F[B])(f: (A, B) => Z): F[Z] =
    ap(map(fa)(a => (b: B) => f(a,b)))(fb)
}

trait Monad[F[_]] {
  def flatMap[A, B](f: A => F[B]): F[A] => F[B]
}

// dependent
for {
  x <- List(1,2,3)
  y <- List(4+x,5+x,6+x)
} yield y

// independent
for {
  firstName <- Some("Bob")
  lastName <- Some("Axel")
} yield firstName + " " + lastName




Applicative[Option].map2(Some("Bob"), Some("Axel"))((a,b) => a + " " + b) // Some("Bob Axel")

val maybeAddOne: Option[Int => Int] = Some((x: Int) => x + 1)
val maybeFive: Option[Int] = Some(5)

// ap allows to apply a function in a context to a value in a context
val maybeSix: Option[Int] = maybeAddOne.ap(maybeFive) // results in Some(6)

val maybeAdd: Option[(Int, Int) => Int] = Some((x: Int, y: Int) => x + y)
val maybeOne: Option[Int] = Some(1)
val maybeTwo: Option[Int] = Some(2)

val maybeThree: Option[Int] = maybeAdd.map2(maybeOne, maybeTwo) // results in Some(3)

