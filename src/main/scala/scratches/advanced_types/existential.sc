import scala.language.existentials

// translate Java wildcards
// existential type - there is a type but we don't know it exactly
// used to abstract over a type

def foo(x : Array[Any]) = println(x.length)
//foo(Array[String]("foo", "bar", "baz"))

def foo[T](x : Array[T]) = println(x.length)
foo(Array[String]("foo", "bar", "baz"))
// I want an Array, and I don’t care what type of things it contains
def foo(x : Array[T] forSome { type T }) = println(x.length)
foo(Array[String]("foo", "bar", "baz"))

def foo(x : Array[T] forSome { type T <: CharSequence }) = x.foreach(y => println(y.length))
foo(Array[String]("foo", "bar", "baz"))

def foo(x : Array[_ <: CharSequence]) = println(x.length)

// existential type - variable exists only on right side
// phantom type - variable exists only on left side
case class SomeClass[A](a: A)
type G[A] = SomeClass[A] // A appears on the left and right side, common case
type G = SomeClass[A] forSome { type A } // A appears only on the right, existential case


