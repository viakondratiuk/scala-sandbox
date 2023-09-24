// appear at compile-time, doesnt influence runtime
// enforces compile time checks
// state machine?

trait State
trait Active extends State
trait Inactive extends State

class Account[S <: State] {
  def activate()(implicit ev: S =:= Inactive): Account[Active] = new Account[Active]
  def deactivate()(implicit ev: S =:= Active): Account[Inactive] = new Account[Inactive]
  def comment(message: String)(implicit ev: S =:= Active): Unit =
    println(s"Comment: $message")
}

object Account {
  def apply() = new Account[Inactive]
}

//val account = Account() // Inactive account
//account.comment("Hello") // Compile error!

val account = Account() // Inactive account
val activeAccount = account.activate() // Now it's Active
activeAccount.comment("Hello") // It's OK