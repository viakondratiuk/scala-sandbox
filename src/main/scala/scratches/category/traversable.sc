// traversable inverts 2 nested functors F[G[A]] => G[F[A]]

// Problem
// List[Either[A, B]]: A - error, B - value => Either[A, List[B]]

//def traverse[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]

def traverseOptionWithList(strings: List[Option[String]]): Option[List[String]] = {
  strings.foldRight[Option[List[String]]](Some(Nil)) { (optString, acc) =>
    for {
      s <- optString
      list <- acc
    } yield s :: list
  }
}

val input1 = List(Some("a"), Some("b"), Some("c"))
val input2 = List(Some("a"), None, Some("c"))

println(traverseOptionWithList(input1))  // Some(List(a, b, c))
println(traverseOptionWithList(input2))  // None

// val result: Option[List[Int]] = list.traverse(parseInt)
