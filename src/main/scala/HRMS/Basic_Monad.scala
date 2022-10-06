package HRMS

object Basic_Monad extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Monads **/

  /**
  A. Hierarchy and Related Concepts
    Amplifier of types and obeys rules. Pattern for chaining operations
   Operations:
   - "apply, unit" takes unamplified type and converts to amplified. It could be done at apply
   - "flatMap, bind" takes monadic value, transform and return new monadic

   Operation should return the instance of the same type
   Can return same Array but with different types
   map, flatMap and filter to for comprehension
   3 rules:
   1. Left identity
   2. Right identity
   3. Associativity
   */

  /**
  B. What problem solves? Abstract and Real
   1. Use only functions as FP
   2. f(x) = 2 * x, g(x,y) = x / y, f(g(x,y))
   3. g(2,0) fails, no exceptions, return 2 kinds (Real | Nothing)
   4. Function return 1, so add boxing type
   5. f is not ready to get boxing type, so we need something to connect f and g, for comprehension
   6. To solve same problems a. use box b. add connector
   */

  /**
  C. Code Example
   */
  // not monadic
  List(1,2,3).map(x => x + 1)
  // monadic
  List(1,2,3).flatMap(x => List(x + 1))

  // not monadic
  List(1,2,3).map(x => x + 1).filter(_ != 3)
  // monadic chained
  List(1,2,3).flatMap(x => List(x + 1)).flatMap(x => if (x != 3) List(x) else Nil)
  // monadic nested
  List(1,2,3).flatMap(x => List(x + 1).flatMap(x => if (x != 3) List(x) else Nil))

  trait MonadTemplate[A] {
    def unit(value: A): MonadTemplate[A]
    def flatMap[B](f: A => MonadTemplate[B]): MonadTemplate[B]
  }

  val func = (x: Int) => Option(x + 3)
  val func2 = (x: Int) => Option(x + 5)
  val a = 3

  // 1
  assert(Option(a).flatMap(func) == func(a))
  // 2
  assert(Option(a).flatMap(Option(_)) == Option(a))
  // 3
  assert(Option(a).flatMap(func).flatMap(func2) == Option(a).flatMap(x => func(x).flatMap(func2)))
}
