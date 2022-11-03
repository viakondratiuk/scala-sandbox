package HRMS

object CaseClass_Inheritance_Mutable_Unapply extends App {

  trait A {
    def a = println("A")
  }

  class B {
    def b = println("B")
  }

  case class C(name: String) extends B with A
  //  case class D(override val name: String) extends C(name)

  val c = C("C")
  c.a
  c.b

  //  val d = D("D")
  //  d.a
  //  d.b

  case class Copy(a: String, b: String, c: String)

  val copy1 = Copy("a", "b", "c")
  val copy2 = copy1.copy(b = "b2")
  println(copy2)

  case class Mutable(var a: String, var b: String)

  val m = Mutable("a", "b")
  m.a = "a2"
  m.b = "b2"
  println(m)

  sealed class Animal
  case class Dog(color: String) extends Animal
  case class Cat(name: String, age: Int) extends Animal
  case class Fish(fromSea: Boolean) extends Animal

  def patternMatch(a: Animal) = a match {
    case Dog(c) => println(s"Dog $c")
    case Cat(n, a) => println(s"Cat $n and $a")
  }

  val dog: Animal = Dog("black")
  patternMatch(dog)

  sealed trait Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String = {
    notification match {
      case Email(sender, title, _) =>
        s"You got an email from $sender with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
    }
  }
}
