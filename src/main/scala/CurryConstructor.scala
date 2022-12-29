object CurryConstructor extends App {

  def curry(name: String)(age: Int) =
    println(s"My name is $name and I'm $age years old")

  val curried = curry("Slava")(_)
  curried(38)
  curried(25)

  class Message(val name: String) {
    def print(age: Int) =
      println(s"My name is $name and I'm $age years old")
  }
  val m = new Message("Slava")
  m.print(38)
  m.print(25)
}
