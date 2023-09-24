// monad transformer - taken one monad, returns as a result other monad,
// adding a layer of computation to the original monad
// used to combine multiple monads

/*
 A monad transformer T[M[_], A] is a type constructor that:

  1. Takes a monad M as an argument.
  2. Provides a new monadic structure that combines the effects of T and M.
  3. Offers a mechanism (usually a lift function) to bring or inject the effects of M into the combined T[M, A] structure.
 */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

// monads are chained but not composable

val oa = Some(1)
val ob = Some(2)
for {
  a <- oa
  b <- ob
} yield a + b

// monad transformer
case class User(id: Int, name: String)
case class Address(city: String, street: String)

//def findUserById(id: Long): Future[User] = ???
//def findAddressByUser(user: User): Future[Address] = ???
//
//def findAddressByUserId(id: Long): Future[Address] =
//  for {
//    user    <- findUserById(id)
//    address <- findAddressByUser(user)
//  } yield address

def findUserById(id: Long): Future[Option[User]] = ???
def findAddressByUser(user: User): Future[Option[Address]] = ???
//
//def findAddressByUserId(id: Long): Future[Option[Address]] =
//  for {
//    user    <- findUserById(id)
//    address <- findAddressByUser(user)
//  } yield address
//
//def findAddressByUserId(id: Long): Future[Option[Address]] =
//  findUserById(id).flatMap {
//    case Some(user) => findAddressByUser(user)
//    case None       => Future.successful(None)
//  }

// We can compose Functors: A[B[X]] but cant for Monads A[B[X]]
// Monads doesnt compose generically
// Monads chained

case class FutOpt[A](value: Future[Option[A]]) {
  def map[B](f: A => B): FutOpt[B] = FutOpt(value.map(optA => optA.map(f)))

  def flatMap[B](f: A => FutOpt[B]): FutOpt[B] =
    FutOpt(value.flatMap {
      case Some(a) => f(a).value
      case None => Future.successful(None)
    })
}

//def findAddressByUserId(id: Long): Future[Option[Address]] =
//  (for {
//    user    <- FutOpt(findUserById(id))
//    address <- FutOpt(findAddressByUser(user))
//  } yield address).value

case class ListOpt[A](value: List[Option[A]]) {
  def map[B](f: A => B): ListOpt[B] = ListOpt(value.map(optA => optA.map(f)))

  def flatMap[B](f: A => ListOpt[B]): ListOpt[B] =
    ListOpt(value.flatMap {
      case Some(a) => f(a).value
      case None => List(None)
    })
}

// no need to know specific about outer monad, only inner

//def findAddressByUserId(id: Long): Future[Option[Address]] =
//  (for {
//    user    <- OptionT(findUserById(id))
//    address <- OptionT(findAddressByUser(user))
//  } yield address).value