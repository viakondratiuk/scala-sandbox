package HRMS

object Monads_In_Scala extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** CONCEPT **/

  /**
  A. Hierarchy and Related Concepts


   */

  /**
  B. What problem solves? Abstract and Real

   Collections supporting map and flatMap are referred to as monadic.
  Most Scala collections are monadic, and operating on them using map and flatMap operations,
  or using for-comprehensions is referred to as monadic-style.
  People will often refer to the monadic nature of a collection (or other container)
  using the word monad, eg. the “List monad”.

   */

  /**
  C. Code Example
   */
  val x = Some(10)

  // 1. left identity
  // unit(x).flatMap(f) = f(x)
  // Some(x).flatMap(f) = f(x)
  println(x.flatMap(v => Some(v * 10)))

  val fx = (v: Option[Int]) => v match {
    case Some(v) => Some(v * 10)
    case _ => None
  }
  println(fx(x))

  // 2. right identity
  // Some(x).flatMap(x => Option(x)) = Option(x) = Some(x)
  println(x.flatMap(v => Option(v)))

  // 3. associativity
  // Some(x).flatMap(f).flatMap(g) = Some(x).flatMap(v = f(v).flatMap(g))
  println(x.flatMap(v => Some(v * 10)).flatMap(v => Some(v + 10)))
  println(x.flatMap(v => Some(v * 10).flatMap(v => Some(v + 10))))
}
