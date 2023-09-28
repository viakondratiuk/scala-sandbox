package concurrent

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorCapabilities extends App {

  class SimpleActor extends Actor {
    override def receive: Receive = {
      case "Hi!" => sender() ! "Hello, there!"
      case message: String => println(s"[$self] I have received STRING: $message")
      case n: Int => println(s"[simple actor] I have received NUMBER: $n")
      case SpecialMessage(c) => println(s"[simple actor] I have received SPECIAL: $c")
      case SendToMyself(c) => self ! c
      case SayHi(ref) => ref ! "Hi!"
    }
  }

  val system = ActorSystem("actorCapabilitiesDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "hello, actor"

  // 1. messages of Any type
  // a. Message Immutable
  // b. Message Serializable
  simpleActor ! 42

  case class SpecialMessage(contents: String)
  simpleActor ! SpecialMessage("SPECIAL")

  // 2. Actors have info about context and themselves
  // context.self || self  === this
  case class SendToMyself(content: String)
  simpleActor ! SendToMyself("IAmMySelf")

  // 3. Actors can REPLY to messages
  val alice = system.actorOf(Props[SimpleActor], "alice")
  val bob = system.actorOf(Props[SimpleActor], "bob")

  case class SayHi(ref: ActorRef)
  alice ! SayHi(bob)

  // 4- dead letter
   alice ! "Hi!"

  // 5 - forward message
}
