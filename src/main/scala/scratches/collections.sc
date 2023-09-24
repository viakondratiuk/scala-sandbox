class Fruit
class Orange extends Fruit
class Apple extends Fruit


// this is because List[+A]
val aListApple = new Apple :: Nil
val aListFruit = new Orange :: aListApple

// TODO: Why object and not AnyRef?
val aListObject = "ss" :: aListFruit
val aListObject = Set(1) :: aListFruit

// TODO: Example with Nil?

// TODO: Why :: adds to start of List?

// Seq, Set, Map implements PartialFunction

val a = if (true) 1 else throw new NotImplementedError()

val aSetApple = Set(new Apple)
val aSetFruit = aSetApple + new Apple

// implement list

List

trait MyList[+T] // if Apple extends Fruit, than MyList[Apple] <: MyList[Fruit]
final case class NonEmptyList[+T](val h: T, val t: MyList[T]) extends MyList[T]
case object EmptyList extends MyList[Nothing]

val listOfInt: MyList[Int] = EmptyList
val listOfString: MyList[String] = EmptyList
val listOfFruit: MyList[Fruit] = EmptyList


// Hierarchy



// TODO: Why prepend at List?

// collections: mutable, immutable, generic
// Traversable --> foreach; has control over iterations, will push values into the function
// Iterable --> iterator; stateful, you have control and can pull values by one

// Traversable --> Iterable --> [Seq, Set, Map]
// Seq --> [IndexedSeq, LinearSeq]
// Indexed: fast apply, length
// Linear: head, tail, O(1) add in front
// Vector is between Index and Linear

// List is stack, with :: and Nil, outside functional, inside imperative

def incListStacks(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case h :: t => (h + 1) :: incListStacks(t)
}

incListStacks(List(1,2,3))
// not tail recursive, uses a lot of stack frames

def incListWithBuffer(xs: List[Int]): List[Int] = {
  val buf = new ListBuffer[Int]
  for (x <- xs) buf += x + 1
  buf.toList
}

incListWithBuffer(List(1,2,3))
// no stackoverflow

// TODO: where is concrete foreach and iterator

// Sets are Iterables without duplicates
// Maps are Iterables of key -> value pairs

val im = Set(1,2,3)
val newIm = im + 4

im eq newIm

val m = scala.collection.mutable.Set(1,2,3)
val newM = m + 4

m eq (m + 4)

// scala.collection.immutable - immutable for everyone
// scala.collection.mutable - have operations to change collection in place
// scala.collection - either mutable or immutable
// by default - immutable

// Set, Seq[Linear, Index(mutable)], Map

// String immutable and index
// Array mutable and index
// LazyList immutable and linear

// uniform return type principle
List(1,2,3).map(_ + 2)
Set(1,2,3).map(_ + 2)

val lazyList = 1 #:: 2 #:: 3 #:: Stream.empty

def fib(a: Int, b: Int): Stream[Int] = {
  a #:: fib(b, a + b)
}

val lazyFib = fib(1, 1)

val vec = Vector(1, 2, 3)
vec.updated(2, 4)
vec
