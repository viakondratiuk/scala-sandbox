package HRMS

object Inheritance2 extends App {
  // single class inheritance, multiple traits

  sealed class Animal(name: String) {
    def sound = "None"
  }
  class Cat(name: String) extends Animal(name: String) {
    override def sound = "mur"
  }
  class Dog(name: String) extends Animal(name: String) {
    override def sound = "gav"
  }

  val animals: Seq[Animal] = Seq(new Dog("Mahaon"), new Cat("Blackcky"), new Dog("Grrr"))
  animals.foreach(a => println(a.sound))

  // multiple inheritance
  trait Bottom {
    def callMe = {
      println("Im from Bottom")
    }
  }

  trait Left extends Bottom {
    override def callMe = {
      println("Im from Left")
      super.callMe
    }
  }

  trait Right1 extends Bottom {
    override def callMe = {
      println("Im from Right1")
      super.callMe
    }
  }

  trait Right2 extends Right1 {
    override def callMe = {
      println("Im from Right2")
      super.callMe
    }
  }

  class Top extends Left with Right2 {
    override def callMe = {
      println("Im from Top")
      super.callMe
    }
  }

  val top = new Top()
  top.callMe

  println("--------------")
  // https://teepika-r-m.medium.com/multiple-inheritance-in-scala-d206493e83
  class A {
    def foo() = "A"
  }
  trait B extends A {
    override def foo() = "B" + super.foo()
  }
  trait C extends B {
    override def foo() = "C" + super.foo()
  }
  trait D extends A {
    override def foo() = "D" + super.foo()
  }

  var d = new A with D with C with B;
  println(d.foo()) // CBDA????
}
