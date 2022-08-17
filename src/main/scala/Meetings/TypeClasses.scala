package Meetings

object TypeClasses extends App {

  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

//  object HTMLSerializer {
//    def serialize[T](value: T)(implicit serializer: HTMLSerializer[T]): String =
//      serializer.serialize(value)
//
//    def apply[T](implicit serializer: HTMLSerializer[T]) = serializer
//  }

  implicit class HTMLEnrichment[T](value: T) {
    def toHTML(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  case class User(name: String, age: Int, email: String)

  implicit object UserSerializer extends HTMLSerializer[User] {
    def serialize(user: User): String = s"<div>${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  val john = User("John", 32, "john@rockthejvm.com")
//  println(UserSerializer.serialize(john))
//  println(HTMLSerializer.serialize(john))
  println(john.toHTML)

}
