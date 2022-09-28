package HRMS

object CaseClass_UnApply_Equal_Hash extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Case Class base functionality **/

  /**
  A. Hierarchy and Related Concepts
    Simple Way to represent data, easier than classes and have default functionality

   */

  /**
  B. What problem solves? Abstract and Real
    Represent simple immutable data with better interface for props naming

   Has companion object with:
   - apply
   - unapply
   - hashCode
   - equals - compare by structure instead of refs
   - toString
   - copy - if we want to modify(only shallow)
   */

  /**
  C. Code Example
   */
  case class Person(name: String, age: Int)

  // apply
  val slava = Person("Slava", 45)
  // unapply
  val age = slava match {
    case Person(n, a) if a > 38 => s"$n More than 38"
    case p @ Person(name, age) if p.age < 10 => s"Age is ${p.age}"
    case _ => "Unknown"
  }
  println(age)

  // equals
  val slava1 = Person("Slava", 45)
  val slava2 = Person("Slava", 5)
  println(slava == slava1)
  println(slava == slava2)

  // hashCode
  println(slava.hashCode())
  println(slava1.hashCode())
  println(slava2.hashCode())

  // toString
  println(slava.toString)
  println(slava1.toString)
  println(slava2.toString)
}
