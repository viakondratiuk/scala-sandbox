// atomics - more expensive volatile
// atomic var - is a memory location that supports complex linearizable operations
// Atomics:
//  Purpose: Atomics provide a way to read, modify, and write shared variables in a thread-safe manner
//  without the overhead of locking
//  use hardware-level support for concurrency

import scala.concurrent._
import java.util.concurrent.atomic._
import scala.annotation.tailrec

def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")

def execute(body: => Unit): Unit =
  ExecutionContext.global.execute(() => body)

val uid = new AtomicLong(0L)
// complex linearizable = read x, compute x + 1, write x + 1, return x + 1
// CAS: incrementAndGet, getAndSet, compareAndSet
def getUniqueId(): Long = uid.incrementAndGet()

execute { log(s"Uid asynchronously: ${getUniqueId()}") }
log(s"Got a unique id: ${getUniqueId()}")

// CAS
// Purpose: CAS is an atomic operation that updates a variable based on its current value.
//  It's used as a building block for many non-blocking algorithms.
// How It Works: CAS takes in three parameters: expected value, updated value, and the actual variable.
//   If the variable's current value is the same as the expected value, it gets updated with the new value.
//   This operation is atomic.
// Looping & Retrying: CAS can fail if the variable was changed by another thread between reading its value
//   and attempting to update it. When using CAS, it's common to use a loop to retry the operation until
//   it succeeds.

class AtomicSync {
  var v = 1L

  def get = this.v
  def set(nv: Long) = this.v = nv

  def compareAndSet(ov: Long, nv: Long): Boolean = this.synchronized {
    if (this.get != ov) false else {
      this.set(nv)
      true
    }
  }
}

@tailrec
def getUniqueId(): Long = {
  val oldUid = uid.get
  val newUid = oldUid + 1
  if (uid.compareAndSet(oldUid, newUid)) newUid
  else getUniqueId()
}
