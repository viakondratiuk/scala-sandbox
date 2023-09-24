import scala.language.reflectiveCalls

// uses reflection
// different types but has common
// places: type alias, method param, generic
// adds compile time safety, compiler will check that method exists

// Scala compiler can check but JVM doesnt know

type Flyer = {
  def fly(): Unit
}

def fly1(thing: Flyer): Unit = thing.fly()
def fly2(thing: { def fly(): Unit }): Unit = thing.fly()
def fly3[T <: { def fly(): Unit }](thing: T): Unit = thing.fly()

class Duck {
  def fly(): Unit = println("Duck flies")
}

class Horse {
  def ride(): Unit = println("Horse rides")
//  def fly(): Unit = println("Duck flies")
}

fly1(new Duck)
fly2(new Duck)
fly3(new Duck)

//fly1(new Horse)

//////////////////////////

type Closable = { def close(): Unit }

def using(resource: Closable)(fn: () => Unit) {
  try {
    fn()
  } finally { resource.close() }
}
