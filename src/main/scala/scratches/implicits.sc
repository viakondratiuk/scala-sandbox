// How it works?
// a way to say compiler that you should try to find a type for me in some places

// Implicit parameter injection
// compiler will look for a value with the same type and put it there

class B(val value: Int)
class C(val value: Int)

implicit val b = new B(5)
implicit val c = new C(10)

def func(a: Int)(implicit b: B, c: C) = a + b.value + c.value
func(10)


// Implicit type conversion

case class Car(name: String, age: Int)

implicit def str2Car(s: String): Car = Car(s, 10)

"Mazda".age
val car: Car = "Volvo"


// Implicit extension methods

case class Person(name: String, age: Int)

implicit class RichPerson(person: Person) {
  def addYears(y: Int) = s"In $y you would be ${person.age + y}"
  def doubleName = s"${person.name}  ${person.name}"
}

val slava = Person("Slava", 38)
slava.addYears(7)
slava.doubleName


// type class, ad-hoc polymorphism

trait Adder[T] {
  def sum(l: List[T]): String
}

implicit object IntAdder extends Adder[Int] {
  override def sum(l: List[Int]): String = l.sum.toString
}

implicit object strAdder extends Adder[String] {
  override def sum(l: List[String]): String = l.mkString(" + ")
}

def adder1[T](l: List[T])(implicit add: Adder[T]): String = {
  add.sum(l)
}

adder1(List(1,2,3))
adder1(List("I", "am", "human"))
//adder1(List(1.0, 2.5))


// context bound
def adder2[T: Adder](l: List[T]): String = {
  val add = implicitly[Adder[T]]
  add.sum(l)
}

adder2(List(3,5,7))

// TODO: How it works?
// https://intellias.udemy.com/course/scala-advanced-part-1-the-scala-type-system/learn/lecture/9097570#overview
implicit def listsAdder[T: Adder] = new Adder[List[T]] {
  override def sum(l: List[List[T]]): String = {
    val adder = implicitly[Adder[T]]
    adder.sum(l.flatten)
  }
}

adder1(List(List(1,2), List(3,4)))
adder1(List(List("hello"), List("there")))


// Where to search

/*
  Implicits(used as implicit parameters):
    - val/var
    - object
    - defs with no parentheses
 */

/*
  Implicits scope:
    - local scope
    - imported scope
    - companions of all types involved
 */

case class Student(name: String, age: Int)

object Student {
  implicit val ageOrdering: Ordering[Student] = (x: Student, y: Student) => y.age - x.age
}

implicit val nameOrdering: Ordering[Student] = (x: Student, y: Student) => x.name.length - y.name.length

val students = List(
  Student("Slava", 38),
  Student("Olga", 32)
)

students.sorted



//
trait JSONWrite[T] {
  def toJsonString(item: T): String
}

def jsonify[T: JSONWrite](item: T): String =
  implicitly[JSONWrite[T]].toJsonString(item)

implicit object StrJSONWrite extends JSONWrite[String] {
  override def toJsonString(item: String): String = s""""$item""""
}

implicit object DoubleJSONWrite extends JSONWrite[Double] {
  override def toJsonString(item: Double): String = item.toString
}

implicit def listJSONWrite[T: JSONWrite] = new JSONWrite[List[T]] {
  override def toJsonString(items: List[T]): String = {
    val json = implicitly[JSONWrite[T]]
    items.map(x => json.toJsonString(x)).mkString("[", ", ", "]")
  }
}

StrJSONWrite.toJsonString("hello")
jsonify("hi")
jsonify(2.0)
jsonify(List("hi", "there"))
jsonify(List(2.3, 4.5))