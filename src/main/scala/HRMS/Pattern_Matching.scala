package HRMS

object Pattern_Matching extends App{
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Pattern Matching **/

  /**
  A. Hierarchy and Related Concepts
    We always need to shorten space of solutions, we can do it with ifs BUT
    PM is switch on steroids. We can do more with compact and verbose syntax.
   */

  /**
  B. What problem solves? Abstract and Real
    Choose from one of the branches with extended syntax
   */

  /**
  C. Code Example
   */
  // 1. Decompose values
  // case Person(n, a) => println($n, $a)
  // 2. Sealed
  // Need to match all sealed instances
  // 3. Constants
  // 4. Wildcards _ or save to $var
  // 5. Tuples
  // 6. Lists
  // 7. Types
  // 8. Multi-pattern with or |
  // 9. If guards

  // And there some implications
  // #1 try-catch about PM
//  try {
//    // code
//  } catch {
//    case e: RuntimeException => "runtime"
//    case npe: NullPointerException => "npe"
//    case _ => "something else"
//  }
  // #2 generators
  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // #3 unpacking
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  // #4 partial functions
  val list = List(1,2,3,4)
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }
}
