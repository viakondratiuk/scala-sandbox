def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")


def thread(body: => Unit): Thread = {
  val t = new Thread {
    override def run(): Unit = body
  }
  t.start()
  t
}

var uidCount = 0L

def getUniqueId() = {
  val freshUid = uidCount + 1
  uidCount = freshUid
  freshUid
}

def printUniqueIds(n: Int): Unit = {
  val uids = for (i <- 0 until n) yield getUniqueId()
  log(s"Generated uids: $uids")
}

/*
  Possible output: Vector(1, 2, 3, 4, 5) Vector(1, 6, 7, 8, 9)
  Race condition is a phenomenon in which the output of a concurrent program
  depends on the execution schedule of the statements in the program.
 */
val t = thread {
  printUniqueIds(5)
}
printUniqueIds(5)
t.join()

// atomic, no two threads simultaneously could update uidCount
def getUniqueId() = uidCount.synchronized {
  val freshUid = uidCount + 1
  uidCount = freshUid
  freshUid
}

import java.util.concurrent.atomic._
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext

def execute(body: =>Unit) = ExecutionContext.global.execute(
  new Runnable { def run() = body }
)

val uid = new AtomicLong(0L)
def getUniqueId(): Long = uid.incrementAndGet()
execute { log(s"Uid asynchronously: ${getUniqueId()}") }
log(s"Got a unique id: ${getUniqueId()}")

@tailrec def getUniqueId(): Long = {
  val oldUid = uid.get
  val newUid = oldUid + 1
  if (uid.compareAndSet(oldUid, newUid)) newUid
  else getUniqueId()
}