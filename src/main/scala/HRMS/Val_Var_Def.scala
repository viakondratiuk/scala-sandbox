package HRMS

object Val_Var_Def extends App {
  /** Def, Val, Var, Lazy Val **/

  /**
    A. Hierarchy and Related Concepts

    Tells how we need to use those computations.

    Def(method) - lazy evaluated, immutable, returns result many times when its called
    Var - eagerly evaluated, mutable, returns result once during declaration
    Val - eagerly evaluated, immutable, returns result once during declaration
    Lazy Val - lazy evaluated, immutable, returns result once during declaration
  */

  /**
    B. What problem solves? Abstract and Real

    To get results of computations we need to get them at some point. We can do it
    with def, var and val.
    Get and Set for class params, if Case Class get already defined
  */

  /**
    C. Code Example
  */
  def method(): Int = {
    println("Im called")
    1
  }
  println("After def")
  println(method())
  println(method())
  println("====================")

  val value: Int = {
    println("Val Called")
    1
  }
  println("After val")
  println(value)
  println(value)
  println("====================")

  val variable: Int = {
    println("Var Called")
    1
  }
  println("After var")
  println(variable)
  println(variable)
  println("====================")

  lazy val lazyValue: Int = {
    println("lazyValue Called")
    1
  }
  println("After lazyValue")
  println(lazyValue)
  println(lazyValue)

  /**
    D. Bloom Taxonomy
    - Remember — list, recite, outline, define, name, match, quote, recall, identify, label, recognize
    - Understand — describe, explain, paraphrase, restate, give original examples of, summarize, contrast, interpret, discuss
    - Apply - calculate, predict, apply, solve, illustrate, use, demonstrate, determine, model, perform, present
    - Analyse — classify, break down, categorize, analyze, diagram, illustrate, criticize, simplify, associate
    - Evaluate — choose, support, relate, determine, defend, judge, grade, compare, contrast, argue, justify, support, convince, select, evaluate
    - Create — design, formulate, build, invent, create, compose, generate, derive, modify, develop


  */

}
