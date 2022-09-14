package HRMS

import scala.annotation.tailrec

object Option_Recursion extends App {
  /** Option **/

  /**
  A. Hierarchy and Related Concepts

  FP - series of algebra operations, no null or exception, but uses Option/Some/None
   Container with 1 or 0 elements
   Extract values with map, flatMap, filter, foreach
   Instead of null/"" use Option
   Option is Trait with 2 child - Some/None
   */

  /**
  B. What problem solves? Abstract and Real

    Safe getting of value, don't break functional chains
   */

  /**
  C. Code Example
   */
  case class Person(name: String, salary: Option[Int])
  val jack = Person("Jack", Some(1000))
  val mike = Person("Mike", None)

  jack.salary.foreach(println)
  mike.salary.foreach(println)

  /**
  D. Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */

  /** Recursion **/

  /**
  A. Hierarchy and Related Concepts

  FP approach is to use recursion. Its when function calls itself.
    Head ant tail recursion. Head can have SO, tail is last call, don't have stack.
   Supports immutability and can be run in parallel
   */

  /**
  B. What problem solves? Abstract and Real

  FP approach while loop is imperative
   Solves problems when we need iterative solutions, next result depends on prev
   */

  /**
  C. Code Example
   */

  val l = List(1,2,3,4)
  @tailrec
  def sumList(l: List[Int], acc: Int): Int = l match {
    case Nil => acc
    case h :: t => sumList(t, acc + h)
  }
  println(sumList(l, 0))

  /**
  D. Bloom Taxonomy
	- Remember, Understand, Apply, Analyse, Evaluate, Create

	- Apply is interpretation, sketching where the concept fits
	- Analyze is comparing and contrasting other concepts and facts to new information
	- Evaluate is discriminating which connections between concepts are the most important.
   */
}
