package threads

import scala.collection.mutable
import scala.util.Random

object ProducerConsumer extends App {

  def prodConsBuffer(): Unit = {
    val buffer = new mutable.Queue[Int]()
    val capacity = 5

    val consumer = new Thread(() => {
      val random = new Random()

      while (true) {
        buffer.synchronized {
          if (buffer.isEmpty) {
            println("[consumer] waiting for value...")
            buffer.wait()
          }

          val v = buffer.dequeue()
          println("[consumer] got value: " + v)

          buffer.notify()
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0

      while (true) {
        buffer.synchronized {
          if (buffer.size == capacity) {
            println("[producer] buffer is full...")
            buffer.wait()
          }

          println("[producer] producing: " + i)
          buffer.enqueue(i)

          buffer.notify()
          i += 1
        }

        Thread.sleep(Random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
  }

  prodConsBuffer()
}
