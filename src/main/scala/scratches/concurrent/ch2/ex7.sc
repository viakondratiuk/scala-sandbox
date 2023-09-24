/*
  The send method in the Deadlocks section was used to transfer money between the two accounts.
  The sendAll method takes a set accounts of bank accounts and a target bank account,
  and transfers all the money from every account in accounts to the target bank account.
  The sendAll method has the following signature:
    def sendAll(accounts: Set[Account], target: Account): Unit

  Implement the sendAll method and ensure that a deadlock cannot occur.
 */

def log(msg: String): Unit =
  println(s"${Thread.currentThread.getName}: $msg")

var uidCount = 0

def getUniqueId() = synchronized {
  val freshUid = uidCount + 1
  uidCount = freshUid
  freshUid
}

class Account(val name: String, var money: Int) {
  val uid = getUniqueId()
}

// bring an order
def send(a1: Account, a2: Account, n: Int) {
  def adjust() {
    a1.money -= n
    a2.money += n
  }

  if (a1.uid < a2.uid)
    a1.synchronized { a2.synchronized { adjust() } }
  else
    a2.synchronized { a1.synchronized { adjust() } }
}

def sendAll(accounts: Set[Account], target: Account): Unit =
  accounts.toList.sortBy(_.uid).foreach(a => send(a, target, a.money))

val accounts = (1 to 100).map((i) => new Account(s"Account: $i", i * 10)).toSet
val target = new Account("Target account", 0)

sendAll(accounts, target)

accounts.foreach((a) => log(s"${a.name}, money = ${a.money}"))
log(s"${target.name} - money = ${target.money}")
