/*
  Real-World Scenario Combining Terms:
  Imagine an airport check-in system where passengers (threads) check in to their flights:

  - Each check-in counter (synchronized block) can serve one passenger at a time.
  - A display board (volatile variable) shows the latest flight information,
  and all check-in counters read from it.
  - When a flight is full, passengers for that flight are asked to wait in a lounge (wait).
  When a seat becomes available due to a cancellation, one passenger from the lounge is notified
  (notify) to check in.
  - The seat allocation mechanism ensures that a seat is allocated to a passenger
  only if it's currently free, using a CAS operation.
  - The number of checked-in passengers for each flight is maintained using atomic integers
  to ensure that the count is accurate even with multiple counters operating concurrently.
 */

def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")

// Chapter 2

def thread(body: => Unit): Thread = {
  val t = new Thread {
    override def run(): Unit = body
  }
  t.start()
  t
}

// join would put main thread into waiting state

val t = thread {
  Thread.sleep(1000)
  log("New thread running.")
  Thread.sleep(1000)
  log("Still running.")
  Thread.sleep(1000)
  log("Completed.")
}
t.join()
log("New thread joined.")

// threads rewrites var
var result: String = null
val t = thread {
  result = "\nTitle\n" + "=" * 5
}
t.join()
log(result)

// SYNCHRONIZED

// Race condition - the output of a concurrent program depends
// on the execution schedule of the statements in the program.

// synchronized - block of code is executed only by one thread,
// provides exclusive access to shared resources
// memory changed by one thread visible for other threads
// locks - locks block of code or waits, access limit on shared resource
var uidCount = 0L

// this.synchronized
def getUniqueId() = synchronized {
  val freshUid = uidCount + 1
  uidCount = freshUid
  freshUid
}

def printUniqueIds(n: Int): Unit = {
  val uids = for (i<- 0 until n) yield getUniqueId()
  log(s"Generated uids: $uids")
}
val t = thread { printUniqueIds(5) }
printUniqueIds(5)
t.join()

import scala.collection._
val transfers = mutable.ArrayBuffer[String]()
def logTransfer(name: String, n: Int) = transfers.synchronized {
  transfers += s"transfer to account '$name' = $n"
}

class Account(val name: String, var money: Int)
def add(account: Account, n: Int) = account.synchronized {
  account.money += n
  if (n > 10) logTransfer(account.name, n)
}

val jane = new Account("Jane", 100)
val john = new Account("John", 200)
val t1 = thread { add(jane, 5) }
val t2 = thread { add(john, 50) }
val t3 = thread { add(jane, 70) }
t1.join(); t2.join(); t3.join()
log(s"--- transfers ---\n$transfers")