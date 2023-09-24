// product adt, *
case class Person(name: String, age: Int)

// coproduct adt
// True + False == Boolean
sealed trait Boolean
case object True extends Boolean
case object False extends Boolean

// Red + Green + Blue = RGB
sealed trait RGB
case object Red extends RGB
case object Green extends RGB
case object Blue extends RGB
