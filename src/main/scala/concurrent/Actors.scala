package concurrent

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object Actors extends App {

  // 1. Actor System: Creation of ActorSystem
  val system = ActorSystem("MyActorSystem")

  // 2. Behaviour & 3. State
  class CounterActor extends Actor {
    // State: private variable to maintain the count
    private var count = 0

    // Behaviour: defining how to respond to messages
    def receive = {
      case "increment" => count += 1
      case "get" => sender() ! count
      case (ref: ActorRef, message: String) =>
        // 6. Pass: pass a message to another actor
        ref ! s"Received: $message. Count is: $count"
      case "forward" =>
        // 7. Forward: forward a message to another actor preserving the original sender
        context.actorSelection("../receiver") forward "Hello from forwarder!"
    }
  }

  class MyActor extends Actor {
    // Behaviour
    def receive = {
      case msg => println(s"MyActor received: $msg")
    }
  }

  // Actor creation. This gives us an ActorRef (4. Ref)
  val counterRef: ActorRef = system.actorOf(Props[CounterActor], "counter")
  val receiver: ActorRef = system.actorOf(Props[MyActor], "receiver")

  // Sending messages using the ActorRef triggers the actor's cycle (5. Cycle)
  counterRef ! "increment"
  counterRef ! "increment"
  counterRef ! (receiver, "test")
  counterRef ! "forward"

  // Please remember to shut down the system when done.
//   system.terminate()
}
