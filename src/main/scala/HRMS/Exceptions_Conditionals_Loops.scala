package HRMS

object Exceptions_Conditionals_Loops extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Exceptions **/

  /**
  A. Hierarchy and Related Concepts
    In coding we have situations when its no possible to continue further code execution
    In such case we can throw exception and recover from it in some place
   */

  /**
  B. What problem solves? Abstract and Real
    It prevents program from crush
    Gives us a possibility to keep clean state of execution env
   */

  /**
  C. Code Example
   */
  try {
    // might throw exception
  } catch {
    // recover from exception
    case e: Exception => "Some text"
  } finally {
    // executed anyway
  }

  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Conditionals **/

  /**
  A. Hierarchy and Related Concepts
    Its branching, selecting one branch by some condition
    And its expression, with last line returning a result
   */

  /**
  B. What problem solves? Abstract and Real
    Shortening the field of possible solutions
    by taking only the part which matches condition
   */

  /**
  C. Code Example
   */
  val value = if (9 == 10) "Equal" else "Not Equal"

  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Loops **/

  /**
  A. Hierarchy and Related Concepts
    Scala supports for and while loops, but they related to side effects, so its needed to watch out :)
    Its about repeating a computation many times
    For comprehension is syntax sugar for flatMap, map, filter and foreach
   */

  /**
  B. What problem solves? Abstract and Real
    Repeat computations
   */

  /**
  C. Code Example
   */
  val colorList = Seq("R", "G", "B")
  for {
    c1 <- colorList
    c2 <- colorList
    if c2 != c1
    c3 <- colorList
    if c3 != c2 && c3 != c1
  } {
    print(s"$c1$c2$c3 ")
  }
}
