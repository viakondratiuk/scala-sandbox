// 1. PROGRAM ORDER RULE
// Each action in a thread happens-before every action that comes afterward
// in the program order of that thread.

var x = 0

new Thread(() => {
  x = 1  // Action A
  println(x)  // Action B
}).start()

// Here, action A (`x = 1`) happens-before action B (`println(x)`),
// which ensures that the printed value is 1
// as defined by the program order rule.

// 2. MONITOR LOCKING RULE
// Unlocking a monitor happens-before every subsequent lock on that monitor.

val lock = new Object
var y = 0

val thread1 = new Thread(() => {
  lock.synchronized {
    y = 1  // Action A
  }
})

val thread2 = new Thread(() => {
  lock.synchronized {
    println(y)  // Action B
  }
})

thread1.start()
thread1.join()
thread2.start()

// Here, the lock release at the end of `thread1` synchronized block (action A) happens-before
// the lock acquire at the beginning of `thread2` synchronized block (action B),
// so 1 will be printed.

// 3. VOLATILE FIELDS RULE
// A write to a volatile field happens-before every subsequent read of that volatile field.

@volatile var z = 0

val thread1 = new Thread(() => {
  z = 42  // Action A
})

val thread2 = new Thread(() => {
  println(z)  // Action B
})

thread1.start()
thread1.join()
thread2.start()

// The write to `z` in `thread1` (action A) happens-before the read in `thread2` (action B),
// ensuring 42 will be printed.

// 4. THREAD START RULE
//  A call to `start()` on a thread happens-before any action in the started thread.

var a = 72  // Action A

val thread1 = new Thread(() => {
  println(a)  // Action B
})
thread1.start()

// Here, the initialization of `a` (action A) happens-before the start of `thread1`,
// and hence before the print statement (action B),
// ensuring 72 will be printed.

// 5. THREAD TERMINATION RULE
// Any action in a thread happens-before
// another thread successfully returns from a `join()` on that thread.

var b = 0

val thread1 = new Thread(() => {
  b = 54  // Action A
})
thread1.start()
thread1.join()
println(b)  // Action B

// The modification of `b` in `thread1` (action A) happens-before
// the print statement (action B) due to the `join` call,
// thus 54 will be printed.

// 6. TRANSITIVITY RULE
// If an action A happens-before action B, and action B happens-before action C,
// then action A happens-before action C.

var c = 0  // Action A

val thread1 = new Thread(() => {
  c += 1  // Action B
})
thread1.start()
thread1.join()

val thread2 = new Thread(() => {
  c += 2  // Action C
})
thread2.start()
thread2.join()

println(c)  // Action D

// By the time we reach action D, actions A, B, and C have all occurred,
// thanks to the combination of the program order rule and the thread start and join rules,
// ensuring a printed value of 3.


// Additionally, the JMM guarantees that
// volatile reads and writes as well as monitor locks and unlocks are never reordered.

// A program in which this is not true is said to contain data races.