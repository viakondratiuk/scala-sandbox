package Meetings


object Test1 extends App {
  case class Email( sender: String,  body: String)

  case class :@ (sender: String, body: String)

  val sender3 :@ body3 = :@("sender3", "some body3")

  val Email(sender, body) = Email("sender", "some body")

  println("example 1", sender, body)
  println("@-magic", sender3, sender3)
}

object Test2 extends App {
  class Email(val sender: String, val body: String)

  object Email {
    def apply(sender: String, body: String): Email = new Email(sender, body)

    def unapply(email: Any): Option[Tuple2[String, String]] =
      email match {
        case e: Email => Some((e.sender, e.body))
        case (sender: String, body: String) => Some((sender, body))
        case _ => None
      }
  }

  val Email(sender, body) = Email("sender", "some body")
  val Email(sender2, body2) = ("sender", "some body")


  println("example 2", sender, body)
  println("example 2", sender2, body2)

  val (value1, value2) = ("a", "b")


  val List(e1, e2) = 1 :: List(2)

  println("example 2", e1, e2)

  val head :: tail = List(1, 2, 3)
  // 1 List(1, 2, 3) -> List.unapply ....
  // head :: tail -> Const(head, tail) -> List.unapply
  println("example 2", head, tail)

  val sender3 Email body3 = Email("sender", "some body")

}
