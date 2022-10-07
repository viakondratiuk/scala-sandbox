package HRMS

object Option_Nothing_Sealed_CaseObject_List {
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

    Option - Monad, Container, Scala way to handle refs, Some or None,
  designed to solve problem with null pointers, creates safe API
    Nothing - bottom type, on which Any is everything and None is Nothing,
  None, Nil - are Nothing type
    Sealed - final - cant extend, public - extend everywhere, sealed - extend only at this file,
  helps compiler.
    Case Object - when we know there would be one instance and adds Serializable interface,
  better String
    List - is Linear Seq with: head, tail, isEmpty O(1), Nil and ::
   */

  /**
  C. Code Example
   */
  sealed abstract class Option[A]
  case object None extends Option[Nothing]
  case class Some[A](v: A) extends Option[A]

  sealed abstract class List[A]
  case object Nil extends List[Nothing]
  case class ::[A](head: A, tail: List[A]) extends List[A]
}
