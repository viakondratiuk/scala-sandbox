import scala.collection.mutable.ListBuffer

/**
 * Vivisecting the Veil of Variance
 *
 * https://www.youtube.com/watch?v=dPbbqrhm-WY
*/


// variance improves type inference?
// show subtype relationship between type parameters affects the subtyping of the type itself
// subtype is narrowing, supertype is widening

// Sandwich subtype Food, Sandwich <: Food
// Food supertype Sandwich, Food >: Sandwich
// Liskov -> subtype can be used instead of supertype

trait Food {
  def calories: Int
}
trait Sandwich extends Food {
  def isToasted: Boolean
}

// narrowing
val hamSandwich: Sandwich = new Sandwich {
  override def isToasted = false
  override def calories = 100
}
// widening and forget part
val foodSandwich: Food = hamSandwich
// widening and forget part
def eatFood(food: Food): Unit =
  println(s"has calories ${food.calories}")

eatFood(hamSandwich)

final case class Box[+A](value: A)

val sandwichBox: Box[Sandwich] = Box(hamSandwich)
// is it true: Box[Sandwich] <: Box[Food], Sandwich <: Food
// if its true we can
val foodBox: Box[Food] = sandwichBox
// add a +, A <: B, Box[A] <: Box[B]

//val foodSandwich: Food = hamSandwich
//val foodBox: Box[Food] = sandwichBox

def eatFoodInABox(box: Box[Food]): Unit =
  println(s"has calories ${box.value.calories}")

eatFoodInABox(sandwichBox)

// why we should add plus?
// if we would have covariance by default, Box[A] <: Box[B]
class Box2[A](var value: A)
val sandwichBox2: Box2[Sandwich] = new Box2[Sandwich](hamSandwich)
val foodBox2: Box2[Food] = sandwichBox2.asInstanceOf[Box2[Food]]
println(foodBox2.value.calories)
foodBox2.value = new Food {
  override def calories = 999
}
// ClassCastException
//sandwichBox2.value.isToasted
// when we have mutable data structure it has to be invariant (Array)

class Box2[+A](val value: A) {
//  def setValue(a: A) = ???
  def getValue: A = value
}
val sandwichBox2: Box2[Sandwich] = new Box2[Sandwich](hamSandwich)
val foodBox2: Box2[Food] = sandwichBox2
//foodBox2.value = new Food {
//  override def calories = 999
//}
sandwichBox2.value.isToasted
foodBox2.value.calories

// +, produce or contain

/**
  Variance Positions in Scala, Demystified

  https://blog.rockthejvm.com/scala-variance-positions/
*/

// if Dog is Animal, is MyList[Dog] is also a MyList[Animal]
// A is subtype of B, is MyList[A] is subtype of MyList[B]

// MyList[+A], produces
// MyList[A]

// Vet[-A]: Vet[Dog] = Vet[Animal], consumes


abstract class MyList[+T] {
  def head: T
  def tail: MyList[T]
  def add[S >: T](elem: S): MyList[S]
}

class Animal
class Cat extends Animal
class Dog extends Animal

// vals in covariant position
//class Vet[-T](val favoriteAnimal: T)
//val aCat = new Cat
//val theVet: Vet[Animal] = new Vet[Animal](aCat)
//val dogVet: Vet[Dog] = theVet

// vars also in contravariant position


/////////////////////

class Animal
class Dog extends Animal
class Cat extends Animal
// Dog subtype Animal, Dog <: Animal
// Animal supertype Dog, Animal >: Dog
// if Dog is subtype of Animal, MyList[Dog] subtype MyList[Animal]

// yes, covariant, +
// no, invariant
// backwards, contravariant, -

Option
abstract class MyList[+T] {
  def head: T
  def tail: MyList[T]
  def add[S >: T](elem: S): MyList[S]
}
//val animals: MyList[Animal] = new MyList[Cat]
//animals.add(new Dog)

// vals in covariant position
//class Vet[-T](val favAnimal: T)
//val aCat = new Cat
//val aVet: Vet[Animal] = new Vet[Animal](aCat)
//val aDog: Vet[Dog] = aVet
// type conflict


// the variance question: if A extends B, should Thing[A] be a subtype of Thing[B]?
// variance possibilities as answers to the variance question: covariant (yes), invariant (no), contravariant (hell no, backwards) types val fields are in covariant position
// types of var fields are in covariant AND contravariant position
// types of method arguments are in contravariant position
// method return types are in covariant position
// we solve the “covariant type occurs in contravariant position” by “widening”: we add a type argument [S >: T] and change the argument type to S
// we solve the “contravariant type occurs in covariant position” by “narrowing”: we add a type argument [S <: T] and change the method return type to S

abstract class Vet[-T] {
  def heal[S <: T]: S
}










