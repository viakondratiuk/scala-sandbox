import scala.collection.mutable.ArrayBuffer

/**
 * Rich
 */
class Rational(val n: Int, val d: Int) extends Ordered[Rational] {
  override def compare(that: Rational): Int =
    (this.n * that.d) - (that.n * this.d)
}
val half = new Rational(1, 2)
val third = new Rational(1, 3)

half > third
half < third

/**
 * Stack
 */
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x * 2)
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int): Unit = if (x > 5) super.put(x)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  override def get() = buf.remove(0)
  def put(x: Int) = buf += x
}

val q = new BasicIntQueue with Incrementing with Filtering with Doubling
q.put(3)
q.put(7)
q.put(11)
q.get()


/**
 * Linearization
 */

trait A {
  def foo = "A"
}

trait B extends A {
  override def foo: String = "B" + super.foo
}

trait C extends A {
  override def foo: String = "C" + super.foo
}

trait D extends C {
  override def foo: String = "D" + super.foo
}

val o = new D with C
o.foo


/**
 * Self
 */
trait A {
  def canDoSomething(): Boolean
}

trait B { self: A => // B requires A
  def doo(s: String) =
    if (self.canDoSomething()) println(s)
    else println("Cant")
}

class C extends B with A {
  override def canDoSomething(): Boolean = false
}

val c = new C
c.doo("I did")

/**
 * Super
 */


/**
 * Early init
 */
// init field of subclass before super call
trait A {
  val a: String
  val dup = a + " " + a
}

class X extends {
  val a = "Cat"
} with A
(new X).dup

class Y extends A {
  val a = "Dog"
}
(new Y).dup

///////////////////////

trait A {
  val audience: String
  println("Hello " + audience)
}

class BMember(a: String = "World") extends A {
  val audience = a
  println("I repeat: Hello " + audience)
}

class BConstructor(val audience: String) extends A {
  println("I repeat: Hello " + audience)
}

new BMember("Readers")
new BConstructor("11")
