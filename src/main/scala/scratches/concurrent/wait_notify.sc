


class Buffer(val capacity: Int) {
  val items = new Array[Int](capacity)
  private var count = 0
  private var putPtr = 0
  private var takePtr = 0

  def put(item: Int): Unit = this.synchronized {
    if (count == items.length) {
      this.wait()
    }
    items(putPtr) = item
    putPtr = (putPtr + 1) % items.length
    count += 1
    this.notify()
  }

  def take(): Int = this.synchronized {
    if (count == 0) {
      this.wait()
    }
    val item = items(takePtr)
    takePtr = (takePtr + 1) % items.length
    count -= 1
    this.notify()
    item
  }
}

val buffer = new Buffer(10)

val producer = new Thread(() => {
  var i = 0
  while (true) {
    Thread.sleep(100)
    buffer.put(i)
    println(s"Produced $i")
    i += 1
  }
})

val consumer = new Thread(() => {
  while (true) {
    Thread.sleep(300)
    val i = buffer.take()
    println(s"Consumed $i")
  }
})

producer.start()
consumer.start()

//producer.join(); consumer.join()