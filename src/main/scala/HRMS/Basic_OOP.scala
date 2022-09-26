package HRMS

object Basic_OOP extends App {
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
    Core of OOP are Classes and there instances
    Class - list of instructions
    Instance - data and methods how to manipulate on it
    Trait - interface with abstract and real methods, preferred over classes
    Polymorphism - many forms of the same method, via overloading and overriding(also Implicits)
    Inheritance - extends and with
    Encapsulation - binding data with methods and restricting access to some of them
    Abstraction - methods without body
   */

  /**
  B. What problem solves? Abstract and Real

    Gives an extended possibility to reuse code
   */

  /**
  C. Code Example
   */
  trait One
  trait Two
  class Three extends One with Two

  trait Overload {
    private def method1(): Int = 1
    protected def method1(n: Int): Int = n
  }

  trait Overwrite extends Overload {
    override def method1(n: Int): Int = n * n
  }
}
