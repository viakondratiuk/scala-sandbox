package actors

import akka.actor.{Actor, ActorSystem, Props}

/*
  Thread Model Limitations:
  1. OOP encapsulation is only valid at SINGLE THREADED MODEL -- needs synchronization
  2. Delegating something to a thread is hard, how to delegate to a specific running thread
  3. Tracing and dealing with errors is hard
 */

/*
  Actors:
  - You cant access Actor but only can send messages to them

  - Every interaction is sending and receiving messages
  - Messages are async
 */

object ActorsIntro extends App {
  // 1. Actor System [controls threads to run actors]
  val actorSystem = ActorSystem("firstActorSystem")

  // 2. Create Actors
  object WordCountActor {
    // factory method
    def props(name: String): Props = Props(new WordCountActor(name))
  }
  class WordCountActor(name: String) extends Actor {
    // internal ds
    var totalWords = 0

    override def receive: Receive = {
      case message: String =>
        println(s"[word count] I have received a message: $message from $name")
        totalWords += message.split(" ").length
      case msg => println(s"[word count] I dont understand ${msg.toString}")
    }
  }

  // 3. instantiate actor
  val wordCounter = actorSystem.actorOf(WordCountActor.props("wordCounter"), "wordCounter")
  val anotherWordCounter = actorSystem.actorOf(WordCountActor.props("anotherWordCounter"), "anotherWordCounter")

  // 4. communicate
  wordCounter ! "I am learning Akka"
  anotherWordCounter ! "Another word counter"
}
