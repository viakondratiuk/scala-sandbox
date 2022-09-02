package HRMS

import scala.annotation.tailrec

object Function_Method_Class_Object_Trait extends App {
  //A. What problem this concept can solve?
  //B. How it can solve it?
  //C. What is the proper place of this concept in a bigger picture?


  /*** Function ***/
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

  /*** Method ***/
  // A. Function which exists inside of an object and
  // act on data from the object is called a function
  // It available for any instance of the class
  // There is no operators in Scala, they all are methods
  // Operator symbol is a name and bound to type
  // 2 + 3 => 2.+(3) => Infix Operator Notation
  // Contains in Class, Case Classes, Traits, Enums, Objects

  // B. <class instance>.<method>[(<parameters>)]
  // Infix Operator Notation <object> <method> <parameter>
  val s = "vacation.jpg"
  val isJPEG = s.endsWith(".jpg")
  val res = 1.+(2)

  // C. One of the main structure block,
  // it tightly related to Functions

  /*** Class ***/
  // A. Class organizes data and behaviour,
  // Its a template to create object instances
  // Can have constructor(or multiple)
  // Can have params, fields, methods, default vals, aux constructors
  // Inheritance - can extend other classes
  // Polymorphism - subclasses can change behaviour of parent
  // Encapsulation - privacy control

  // B. class <identifier> [extends <identifier>] [{ fields, methods, and classes }]
  class Car(val make: String, var reserved: Boolean) {
    def reserve(r: Boolean): Unit = { reserved = r }
  }

  // C. Fundamental block, basic OOP block

  /*** Object ***/
  // A. All static functionality goes to Object
  // Instance based func is in Class and global in Object
  // Special type of class They are Singletons and instantiated when called
  // Don't have parameters

  // B. object <identifier> [extends <identifier>] [{ fields, methods, and classes }]
  object Person

  // C. Takes out static functionality from instance, main building block

  /*** Trait ***/
  // A. They share methods and fields between classes. Cant create instance from them.
  // Classes and objects can extend them. Dont have params?
  // Adds multiple inheritance.
  // In JVM linearization happens

  // B.
  trait One
  trait Two extends One

  // C. It can be used as interfaces. Defining the same concepts for classes.
  // One more method to reuse code
}
