def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")

class SynchronizedPool() {
  import scala.collection._

  val tasks = mutable.Queue[() => Unit]()

  object Worker extends Thread {
    setDaemon(true)

    def poll() = tasks.synchronized {
      while (tasks.isEmpty) tasks.wait()
      tasks.dequeue()
    }

    override def run() = while (true) {
      val task = poll()
      task()
    }
  }

  Worker.start()

  def asynchronous(body: =>Unit) = tasks.synchronized {
    tasks.enqueue(() => body)
    tasks.notify()
  }
}

val syncPool = new SynchronizedPool()
syncPool.asynchronous { log("Hello ") }
syncPool.asynchronous { log("World!") }

////////////////////////////////////

class PriorityTaskPool() {
  import scala.collection._

  implicit val ord: Ordering[(Int,() => Unit)] = Ordering.by(_._1)
  val tasks = mutable.PriorityQueue[(Int, () => Unit)]()

  def asynchronous(priority: Int)(body: => Unit) = tasks.synchronized {
    tasks.enqueue((priority, () => body))
    tasks.notify()
  }

  object Worker extends Thread {
    setDaemon(true)

    def poll() = tasks.synchronized {
      while (tasks.isEmpty) {
        tasks.wait()
      }
      log("queue: " + tasks.foldLeft("")((s, t) => s"$s${t._1},"))
      tasks.dequeue()
    }

    override def run() =
      while (true) {
        poll() match {
          case (_, task) => task()
        }
      }
  }

  Worker.start()
}


val priorityPool = new PriorityTaskPool

(1 to 10).foreach((i) => {
  val a = (Math.random * 1000).toInt
  priorityPool.asynchronous(a)({ log(s"<- $a") })
})

Thread.sleep(10000)