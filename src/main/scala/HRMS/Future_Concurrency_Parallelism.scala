package HRMS

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Success, Try}

object Future_Concurrency_Parallelism extends App {
  // Concurrent - 2 or more start, run and complete in overlapping periods
  // Parallel - run at the same time on multiple cores

  val aFuture = Future {
    Thread.sleep(100)
    42
  }
  aFuture.onComplete {
    case Success(value) => println(value)
    case Failure(e) => e.printStackTrace()
  }

  val usdRate = Future {
    Thread.sleep(200)
    5
  }
  val eurRate = Future {
    Thread.sleep(200)
    1
  }

  for {
    usd <- usdRate
    eur <- eurRate
    if eur > usd
  } println(s"Amount ${usd * eur}")



  // Nonblocking
  def calc: Int = {
    Thread.sleep(100)
    42
  }
  def calcFuture = Future { calc }
  calcFuture.onComplete {
    case Success(res) => println(s"Success $res")
    case Failure(e) => e.printStackTrace()
  }
  // Blocking
  val transaction = Future {
    Thread.sleep(1000)
    "SUCCESS"
  }
  val result = Await.result(transaction, 1.seconds)
  println(result)

  // Promise
  // Promise - writes value, can complete by client, future - reads value, cant complete
  val promise = Promise[Int]() // controller over future
  val future = promise.future

//  future.onComplete {
//    case Success(r) => println(s"[consumer] received $r")
//  }

  val producer = new Thread(() => {
    println("[producer] do comps")
    Thread.sleep(500)
    promise.success(42)
    println("[producer] done")
  })
  producer.start()

  // Exercises
  /*
   1) fulfill a future IMMEDIATELY with a value
   2) inSequence(fa, fb)
   3) first(fa, fb) => new future with the first value of the two futures
   4) last(fa, fb) => new future with the last value
   5) retryUntil[T](action: () => Future[T], condition: T => Boolean): Future[T]
  */
  // 1 - fulfill immediately
  def fulfillImmediately[T](value: T): Future[T] = Future(value)
  println(fulfillImmediately(5))
  // 2 - insequence
  def inSequence[A, B](first: Future[A], second: Future[B]): Future[B] =
    first.flatMap(_ => second)

  // 3 - first out of two futures
  def first[A](fa: Future[A], fb: Future[A]): Future[A] = {
    val promise = Promise[A]

    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)

    promise.future
  }
  // 4 - last out of the two futures
  def last[A](fa: Future[A], fb: Future[A]): Future[A] = {
    // 1 promise which both futures will try to complete
    // 2 promise which the LAST future will complete
    val bothPromise = Promise[A]
    val lastPromise = Promise[A]
    val checkAndComplete = (result: Try[A]) =>
      if(!bothPromise.tryComplete(result))
        lastPromise.complete(result)

    fa.onComplete(checkAndComplete)
    fb.onComplete(checkAndComplete)

    lastPromise.future
  }

  val fast = Future {
    Thread.sleep(100)
    42
  }

  val slow = Future {
    Thread.sleep(200)
    45
  }
  first(fast, slow).foreach(f => println("FIRST: " + f))
  last(fast, slow).foreach(l => println("LAST: "  +  l))

  Thread.sleep(1000)
}
