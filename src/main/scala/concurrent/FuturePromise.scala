package concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Random, Success}

object UserProfileFetcher {

  // Simulating a database fetch
  def fetchFromDB(userId: Int): Future[String] = Future {
    Thread.sleep(Random.nextInt(5000)) // Simulating variable network delay
    s"Profile of user $userId from DB"
  }

  // Simulating a cache fetch
  def fetchFromCache(userId: Int): String = {
    s"Profile of user $userId from Cache"
  }

  def fetchUserProfileWithTimeout(userId: Int, timeout: Duration): Future[String] = {
    val p = Promise[String]()

    val dbFuture = fetchFromDB(userId)
    dbFuture.onComplete(p.tryComplete) // When DB fetch completes, try to complete the promise

    // Handle timeout
    Future {
      Thread.sleep(timeout.toMillis)
      p.trySuccess(fetchFromCache(userId)) // If the promise is not yet completed, complete it with the cache value
    }

    p.future
  }
}

object FuturePromise extends App {
  def fetchProfile(id: String): Future[String] = Future {
    val profiles = Map(
      "id.1" -> "Mark",
      "id.5" -> "Tim",
      "id.10" -> "Sam",
    )
    Thread.sleep(300)

    profiles(id)
  }

  val users = for {
    user1 <- fetchProfile("id.unknown").recover {
      case e: Throwable => "unknown"
    }
    user2 <- fetchProfile("id.unknown").fallbackTo(fetchProfile("id.1"))
  } yield (user1, user2)

  val (user1, user2) = Await.result(users, 10.seconds)
  println(user1, user2)


  val promise = Promise[Int]() // "controller" over a future
  val future = promise.future

  // thread 1 - "consumer"
  future.onComplete {
    case Success(r) => println("[consumer] I've received " + r)
  }

  // thread 2 - "producer"
  val producer = new Thread(() => {
    println("[producer] crunching numbers...")
    Thread.sleep(500)
    // "fulfilling" the promise
    promise.success(42)
    println("[producer] done")
  })

  producer.start()
  Thread.sleep(1000)
}
