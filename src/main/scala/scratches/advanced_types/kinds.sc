import scala.language.higherKinds

// abstraction over type which is abstracted over another type
// we can reduce code duplication
// creates polymorphic container

// KINDS

// level 0, type attached to a value
val num: Int = 42
case class Person(name: String, age: Int)
val person = Person("Slava", 39)

// level 1
class LinkedList[T]
val list: LinkedList[Int] = new LinkedList[Int] // level 0 type

// level 2
class Functor[F[_]]
val functorList = new Functor[List] // level 0

class HashMap[K, V] // level 1

// Implementation

trait Collection[T[_]] {
  def wrap[A](a: A): T[A]
  def first[B](b: T[B]): B
}

val listCollection = new Collection[List] {
  override def wrap[A](a: A): List[A] = List(a)
  override def first[B](b: List[B]): B = b.head
}

listCollection.wrap("Some value")
listCollection.first(List(1))

val seqCollection = new Collection[Seq] {
  override def wrap[A](a: A): Seq[A] = Seq(a)
  override def first[B](b: Seq[B]): B = b.head
}

seqCollection.wrap("wrapped")
seqCollection.first(Seq(1.0))

// Real world problem

// Step 1

trait NaiveList[T] {
  def flatMap[B](f: T => B): NaiveList[B]
}

trait NaiveOption[T] {
  def flatMap[B](f: T => B): NaiveOption[B]
}

// combine, List(1,2) x List(a, b) = List(1a, 1b, 2a, 2b)

//def naiveMultiply[A, B](listA: List[A], listB: List[B]): List[(A, B)] =
//  for {
//    a <- listA
//    b <- listB
//  } yield (a, b)
//
//def naiveMultiply[A, B](listA: Option[A], listB: Option[B]): Option[(A, B)] =
//  for {
//    a <- listA
//    b <- listB
//  } yield (a, b)

// to use multiply in general we can use HKT

trait HKT[F[_], A] { // higher-kinded type class
  def flatMap[B](f: A => F[B]): F[B]
  def map[B](f: A => B): F[B]
}

implicit class HKTList[A](list: List[A]) extends HKT[List, A] {
  override def flatMap[B](f: A => List[B]): List[B] = list.flatMap(f)
  override def map[B](f: A => B): List[B] = list.map(f)
}

implicit class HKTOption[A](option: Option[A]) extends HKT[Option, A] {
  override def flatMap[B](f: A => Option[B]): Option[B] = option.flatMap(f)
  override def map[B](f: A => B): Option[B] = option.map(f)
}

// we implemented map and flatMap, so we can write for comprehension
def multiply[F[_], A, B](implicit ha: HKT[F, A], hb: HKT[F, B]): F[(A, B)] =
  for {
    a <- ha
    b <- hb
  } yield (a, b)

multiply(new HKTList(List(1,2)), new HKTList(List("a", "b")))
multiply(new HKTOption(Some(1)), new HKTOption(Some("b")))

multiply(List(1,2), List("a", "b"))
multiply(Some(1), Some("b"))