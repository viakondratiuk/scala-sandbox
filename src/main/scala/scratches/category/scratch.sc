import scala.collection.mutable

// category is object and morphisms, composition and identity
// objects are sets and morphisms are functions

val f: List[Int] => Int = l => l.sum
val g: Int => Double = i => scala.math.sqrt(i)
val h: Double => String = s => "->" + s.toString + "<-"

val c = g compose f
c(List(1,2,3,3))

// properties of composition
val c1 = h compose (g compose f)
val c2 = (h compose g) compose f
c1(List(1,2,3,3))
c2(List(1,2,3,3))

// identity
def identity[A](a: A): A = a
identity(1)
identity(1.0)
identity("abc")
identity(List(1,2,3))

// test identity
val i1 = f compose identity[List[Int]]
i1(List(1,2,3))

// target type of one arrow should be the same as source for next
// types are sets of values
// function can map 2 elements to one, but not one to two
// bottom type as non-terminating function, halting problem

/**
 Define a higher-order function (or a function object) memoize in your favorite language.
 This function takes a pure function f as an argument and
 returns a function that behaves almost exactly like f,
 except that it only calls the original function once for every argument,
 stores the result internally, and subsequently returns this stored result every time it’s called
 with the same argument. You can tell the memoized function from the original
 by watch- ing its performance. For instance, try to memoize a function that takes
 a long time to evaluate. You’ll have to wait for the result the first time you call it,
 but on subsequent calls, with the same argument, you should get the result immediately.
***/

def memoize[A, B](f: A => B): A => B = {
  val cache = mutable.Map.empty[A, B]
  x => cache.getOrElseUpdate(x, f(x))
}

def slowFunction(x: Int) = {
  Thread.sleep(2000)
  x * x
}

val fastFunction = memoize(slowFunction)

val startTime1 = System.currentTimeMillis()
println(fastFunction(4)) // Output: 16
println(s"First call took ${System.currentTimeMillis() - startTime1} milliseconds")

val startTime2 = System.currentTimeMillis()
println(fastFunction(4)) // Output: 16
println(s"Second call took ${System.currentTimeMillis() - startTime2} milliseconds")

// Try to memoize a function from your standard library that you normally use to produce random numbers. Does it work?
scala.util.Random.nextInt()

// How many different functions are there from Bool to Bool? Can you implement them all?
def identityBool(p: Boolean): Boolean = p
def negation(p: Boolean): Boolean = !p
def const_true(p: Boolean): Boolean = true
def const_false(p: Boolean): Boolean = false


// monoid == associative and unit
// natural numbers + 0 on addition form a monoid

trait Monoid[M] {
  def mempty: M
  def mappend(m1: M, m2: M): M
}

implicit def stringMonoid: Monoid[String] = new Monoid[String] {
  override def mempty = ""
  override def mappend(m1: String, m2: String): String = m1 + m2
}

implicit def intMonoid: Monoid[Int] = new Monoid[Int] {
  override def mempty = 0
  override def mappend(m1: Int, m2: Int) = m1 + m2
}

def combineAll[A](list: List[A])(implicit monoid: Monoid[A]): A =
  list.foldLeft(monoid.mempty)(monoid.mappend)

combineAll(List(1,2,3))
combineAll(List("a", "b", "c"))


class Builder {
  var name: String = ""
  var age: Int = -1

  def setName(name: String) = {
    this.name = name
    this
  }

  def setAge(age: Int) = {
    this.age = age
    this
  }
}

val me = (new Builder).setName("Slava").setAge(39)
me.name
me.age

def funcBuilder(name: String)(age: Int) = {
  s"$name and $age"
}

val funcName = funcBuilder("Slava") _
funcName(39)