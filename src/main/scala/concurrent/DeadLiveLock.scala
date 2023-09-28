package concurrent

object DeadLiveLock extends App {

  case class Friend(name: String) {
    def bow(other: Friend) = {
      this.synchronized {
        println(s"$this: I am bowing to my friend $other")
        other.rise(this)
        println(s"$this: my friend $other has risen")
      }
    }

    def rise(other: Friend) = {
      this.synchronized {
        println(s"$this: I am rising to my friend $other")
      }
    }

    var side = "right"

    def switchSide(): Unit = {
      if (side == "right") side = "left"
      else side = "right"
    }

    def pass(other: Friend): Unit = {
      while (this.side == other.side) {
        println(s"$this: Oh, but please, $other, feel free to pass...")
        switchSide()
        Thread.sleep(1000)
      }
    }
  }

  val sam = Friend("Sam")
  val pierre = Friend("Pierre")

  // deadlock
//  new Thread(() => sam.bow(pierre)).start() // sam's lock,    |  then pierre's lock
//  new Thread(() => pierre.bow(sam)).start() // pierre's lock  |  then sam's lock

  // livelock
//  new Thread(() => sam.pass(pierre)).start()
//  new Thread(() => pierre.pass(sam)).start()
}
