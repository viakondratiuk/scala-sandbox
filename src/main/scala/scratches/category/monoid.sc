trait Monoid[A] {
  // associative binary operation: op(op(x,y), z)) == op(x, op(y,z))
  def op(a1: A, a2: A): A
  // identity: op(x, zero) == x, op(zero, x)
  def zero: A
}

// TODO: Why Im writing type: String
val stringMonoid = new Monoid[String] {
  override def op(a1: String, a2: String) = a1 + a2
  override def zero: String = ""
}

val intMonoid = new Monoid[Int] {
  override def op(a1: Int, a2: Int): Int = a1 + a2
  override def zero: Int = 0
}

def listMonoid[A] = new Monoid[List[A]] {
  override def op(a1: List[A], a2: List[A]) = a1 ++ a2
  override val zero = Nil
}

val words = List("I", "have", "a", "dream")
words.foldLeft(stringMonoid.zero)(stringMonoid.op)
words.foldRight(stringMonoid.zero)(stringMonoid.op)