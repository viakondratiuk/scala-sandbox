package HRMS

object Inheritance extends App {
  /**
  Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Inheritance **/

  /**
  A. Hierarchy and Related Concepts
    Inheritance - basis of extandability and code reuse
   # Single Inheritance
   # Multiple Level Inheritance
   # Multiple Inheritance
   # Hierarchical Inheritance
   # Diamond

   Also about linearization. While Scala has multiple inheritance its linearized.
   There is no ambiguity in call:
   class A extends B with C
   */

  /**
  B. What problem solves? Abstract and Real
    If we need to apply polymorphism we need inheritance with overriding

   */

  /**
  C. Code Example
   */
  trait A {
    def print = {
      println("A")
    }
  }

  trait B extends A {
    override def print = {
      println("B")
      super.print
    }
  }

  trait C extends A {
    override def print = {
      println("C")
      super.print
    }
  }

  trait D extends C {
    override def print = {
      println("D")
      super.print
    }
  }

  class Child extends D with B {
    override def print = {
      println("Child")
      super.print
    }
  }

  new Child().print

  // Single Inheritance
  trait A1
  class B1 extends A1
  // Multiple Level Inheritance
  trait A2
  trait B2 extends A2
  class C2 extends B2
  // Multiple Inheritance
  trait A3
  trait B3
  class C3 extends A3 with B3
  // Hierarchical Inheritance
  trait A4
  class B4 extends A4
  class C4 extends A4
  // Diamond
  trait A5
  trait B5 extends A5
  trait C5 extends A5
  class D5 extends B5 with C5
}
