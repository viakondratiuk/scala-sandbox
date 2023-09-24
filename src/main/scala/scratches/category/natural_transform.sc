// a way to transform one functor to another with preserving an underlying structure
// if geometrically transform one shape to another while keeping a structure

val init = List(1,2,3)
// Objects
// 1  2  3

// Categories
// 1, 2, 3

// Morphisms as action, functions, transformations between entities
val addSmile: Int => String = (x: Int) => s"$x :)"

// Functors with morphism as data, apply them
// transforms from one category to another
init.map(addSmile)

// identity
val identityMappedList = init.map(identity)
init == identityMappedList

// composition
val f: Int => Double = x => x * 2.5
val g: Double => String = x => s"Value: $x"

// Compose functions and then map
val composedMappedList = init.map(f.andThen(g))
// Map with first function and then with the second function
val sequentiallyMappedList = init.map(f).map(g)

composedMappedList == sequentiallyMappedList

// Natural Transformations, switch functors
// transforms from one container to another
def safeHead[A](l: List[A]): Option[A] = l match {
  case Nil => None
  case x :: _ => Some(x)
}

safeHead(init)

// naturality condition
safeHead(init.map(addSmile)) == safeHead(init).map(addSmile)

// def alpha[A]: F[A] => G[A]



