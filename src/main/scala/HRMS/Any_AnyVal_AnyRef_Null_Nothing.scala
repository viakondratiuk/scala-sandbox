package HRMS

object Any_AnyVal_AnyRef_Null_Nothing extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Any_AnyVal_AnyRef_Null_Nothing **/

  /**
  A. Hierarchy and Related Concepts
    Parent is Any
   2 child: AnyVal and AnyRef
   AnyVal is Java primitives, on stack or at heap, has Unit
   AnyRef is references with Null type, at JVM object
   Nothing is child of all, has no value, return when exception, cant assign to anything

   */

  /**
  B. What problem solves? Abstract and Real
    We need to store results of computation in memory but we should know where and how.
    Types tells us memory structure for those values and access methods for them
   */

  /**
  C. Code Example
   */
  // Any -> AnyVal -> Short, Int, Char, Double, Boolean, Float -> Nothing
  // Any -> AnyRef -> Iterable, Seq, List, String -> Null -> Nothing
}
