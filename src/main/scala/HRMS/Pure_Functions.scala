package HRMS

object Pure_Functions extends App {
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

    FP is type of programming when we use pure functional and immutable values
   Pure Function -- Same output for Same input and no Side Effects

   Functions with 0 input and know return type - impure
   */

  /**
  B. What problem solves? Abstract and Real

    They easy to reason about, test, combine, debug, parallelize,
   */

  /**
  C. Code Example
   */
  def pure(n: Int): Int = n + 10
  def impure(n: Int): Unit = println(n)
}
