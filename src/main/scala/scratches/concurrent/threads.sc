// Thread - used for concurrent programming, live at the same process, share the same memory within a process
// can have own counters, stack but shared globals, heap

val thread1 = new Thread(new Runnable {
  def run() {
    println("Thread is running!")
  }
})

var result: String = null

val thread = new Thread(() => {
    Thread.sleep(2000)
    println("Thread is done!")
    result = "Done"
  }
)

thread.start()
thread.join()

println(s"Result from thread: $result")

// deprecated and unsafe
// use volatile to stop
thread.stop()
