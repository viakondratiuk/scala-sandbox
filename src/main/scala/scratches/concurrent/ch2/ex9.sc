def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")


class PriorityTaskPool(p: Int) {
  import scala.collection._

  implicit val ord: Ordering[(Int,() => Unit)] = Ordering.by(_._1)
  val tasks = mutable.PriorityQueue[(Int, () => Unit)]()

  def asynchronous(priority: Int)(body: => Unit) = tasks.synchronized {
    tasks.enqueue((priority, () => body))
    tasks.notify()
  }

  class Worker extends Thread {
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

  (1 to p).map(_ => new Worker()).foreach(_.start)
}


val priorityPool = new PriorityTaskPool(10)

(1 to 10).foreach((i) => {
  val a = (Math.random * 1000).toInt
  priorityPool.asynchronous(a)({ log(s"<- $a") })
})

Thread.sleep(10000)

/*
  Actor: System, Behaviour state, Ref, Cycle, Pass, Forward
 */