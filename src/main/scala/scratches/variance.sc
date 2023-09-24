// problem
// Dog is subtype of Animal, MyList[Dog] subtype MyList[Animal]

// subtypes
// Dog <: Animal, Animal >: Dog

// types of variance
// yes, covariance, +
// no, in,
// backward, contr, -
class Animal
class Cat extends Animal
class Dog extends Animal

abstract class MyList[T](val a: T) {
  def head: T
  def tail: MyList[T]
  def add[S >: T](elem: S): MyList[S]
}

//val aList: MyList[Animal] = new MyList[Cat] {
//  override def head = ???
//
//  override def tail = ???
//
//  override def add(elem: Animal) = ???
//}
//aList.add(new Dog)

// contr in cov, Vet Dog fav a Cat
//class Vet[-T](val favAnimal: T)
//val aCat = new Cat
//val aVet: Vet[Animal] = new Vet[Animal](aCat)
//val aDog: Vet[Dog] = aVet
//aDog.favAnimal

// cov in contr, List Animal add Dog

// var in both, so inv

// retrieve or produces, acts on or consumes

// wide

// narrow

abstract class Vet[-T] {
  def heal[S <: T](a: S): S
}


// List, ListBuffer, Function1
Option