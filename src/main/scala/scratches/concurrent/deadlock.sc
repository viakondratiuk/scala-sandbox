import scala.collection._

def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")

def thread(body: => Unit): Thread = {
  val t = new Thread {
    override def run(): Unit = body
  }
  t.start()
  t
}
//////////////////

class Account(val name: String, var money: Int)

// there is no order
def send(a: Account, b: Account, n: Int): Unit = a.synchronized {
  b.synchronized {
    a.money -= n
    b.money += n
  }
}

val jack = new Account("Jack", 1000)
val jill = new Account("Jill", 2000)

val t1 = thread { for (i<- 0 until 100) send(jack, jill, 1) }
val t2 = thread { for (i<- 0 until 100) send(jill, jack, 1) }

t1.join(); t2.join()
log(s"a = ${jack.money}, b = ${jill.money}")

/////////////////////

var uidCount = 0

def getUniqueId() = synchronized {
  val freshUid = uidCount + 1
  uidCount = freshUid
  freshUid
}

class AccountWithUid(val name: String, var money: Int) {
  val uid = getUniqueId()
}

// bring an order
def send(a1: AccountWithUid, a2: AccountWithUid, n: Int) {
  def adjust() {
    a1.money -= n
    a2.money += n
  }

  if (a1.uid < a2.uid)
    a1.synchronized { a2.synchronized { adjust() } }
  else
    a2.synchronized { a1.synchronized { adjust() } }
}