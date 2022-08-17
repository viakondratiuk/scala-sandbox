package HRMS

import scala.annotation.tailrec

object Function_Method_Class_Object_Trait extends App {
  //A. What problem this concept can solve?
  //B. How it can solve it?
  //C. What is the proper place of this concept in a bigger picture?


  // Function
  // A. Reusable block of logic, where you can specify input and output params.
  // In Scala function is named wrapper over expression
  // B. def <identifier>(<identifier>: <type>[, ... ]): <type> = <expression>
  // C. One of the main building blocks for lang, creates reusable code and support for fp

  // definition
  def aFunction(a: String, b: Int): String = {
    // expression
    a + " " + b
  }
  // call
  println(aFunction("hello", 333))

  def repeater(n: Int, s: String): String = {
    @tailrec
    def _repeater(n: Int, res: String): String = {
      if (n == 0) res
      else _repeater(n - 1, res + s)
    }
    _repeater(n, "")
  }
  println(repeater(5, "Bored..."))

  // Method
  // Class
  // Object
  // Trait
}
