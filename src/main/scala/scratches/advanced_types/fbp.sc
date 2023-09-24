// self-reference
case class Distance(meters: Int) {
  def >(other: Distance): Boolean = this.meters > other.meters
  def <(other: Distance): Boolean = this.meters < other.meters
}

val d1 = Distance(10)
val d2 = Distance(100)

d1 > d2

def insertDistance(item: Distance, rest: List[Distance]): List[Distance] =
  rest match {
    case Nil => List(item)
    case h :: t if item < h => item :: rest
    case h :: t => h :: insertDistance(item, t)
  }

def sortDistances(xs: List[Distance]): List[Distance] =
  xs match {
    case Nil => Nil
    case h :: t => insertDistance(h, sortDistances(t))
  }

sortDistances(List(
  Distance(13),
  Distance(7),
  Distance(10)
))

// recursive
// TODO: Check if its F bounded polymorphism
trait CompareT[T] {
  def >(other: T): Boolean
  def <(other: T): Boolean
}

def genInsert[T <: CompareT[T]](item: T, rest: List[T]): List[T] =
  rest match {
    case Nil => List(item)
    case h :: t if item < h => item :: rest
    case h :: t => h :: genInsert(item, t)
  }

def genSort[T <: CompareT[T]](xs: List[T]): List[T] =
  xs match {
    case Nil => Nil
    case h :: t => genInsert(h, genSort(t))
  }

case class Power(newton: Int) extends CompareT[Power] {
  def >(other: Power): Boolean = this.newton > other.newton
  def <(other: Power): Boolean = this.newton < other.newton
}

genSort(List(
  Power(13),
  Power(7),
  Power(10)
))

// problem: how can compiler force me to return not List[Animal] but List[Cat]

//trait Animal {
//  def breed: List[Animal]
//}
//
//class Cat extends Animal {
//  override def breed: List[Animal] = ??? // List[Cat]
//}
//
//class Dog extends Animal {
//  override def breed: List[Animal] = ??? // List[Dog]
//}

// solution 1, by hands, naive

//trait Animal {
//  def breed: List[Animal]
//}
//
//class Cat extends Animal {
//  override def breed: List[Cat] = ??? // List[Cat]
//}
//
//class Dog extends Animal {
////  override def breed: List[Dog] = ??? // List[Dog]
//  override def breed: List[Cat] = ??? // List[Dog]
//}

// solution 2 - FBP

//trait Animal[A <: Animal[A]] { // recursive type: F Bounded polymorphism
//  def breed: List[Animal[A]]
//}
//
//class Cat extends Animal[Cat] {
//  override def breed: List[Animal[Cat]] = ??? // List[Cat]
//}
//
//class Dog extends Animal[Dog] {
//  override def breed: List[Animal[Dog]] = ??? // List[Dog]
////  override def breed: List[Animal[Cat]] = ??? // List[Dog]
//}
//
////// problem
//class Croc extends Animal[Dog] {
//  override def breed: List[Animal[Dog]] = ???
//}

// solution 3 - FBP + self type

//trait Animal[A <: Animal[A]] { self: A =>
//  def breed: List[Animal[A]]
//}
//
//class Cat extends Animal[Cat] {
//  override def breed: List[Animal[Cat]] = ??? // List[Cat]
//}
//
//class Dog extends Animal[Dog] {
//  override def breed: List[Animal[Dog]] = ??? // List[Dog]
////    override def breed: List[Animal[Cat]] = ??? // List[Dog]
//}

// solution
//class Croc extends Animal[Croc] {
//  override def breed: List[Animal[Croc]] = ???
//}