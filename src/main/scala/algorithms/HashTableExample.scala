package algorithms

import scala.collection.mutable.ListBuffer

class HashTableChaining[K, V](val size: Int) {
  private val table = Array.fill(size)(ListBuffer[(K, V)]())

  private def hash(key: K): Int = {
    (key.hashCode() & 0x7fffffff) % size // Ensure positive index
  }

  def put(key: K, value: V): Unit = {
    val index = hash(key)
    val list = table(index)

    val opt = list.find(_._1 == key)
    if (opt.isDefined) {
      val idx = list.indexOf(opt.get)
      list(idx) = (key, value)
    } else {
      list += ((key, value))
    }
  }

  def get(key: K): Option[V] = {
    val index = hash(key)
    table(index).find(_._1 == key).map(_._2)
  }

  def remove(key: K): Option[V] = {
    val index = hash(key)
    val list = table(index)
    val opt = list.find(_._1 == key)
    opt match {
      case Some((_, value)) =>
        list -= opt.get // Remove the (key, value) pair from the list
        Some(value)
      case None => None
    }
  }
}


object HashTableExample extends App {
  val chain = new HashTableChaining[String, Int](10)
  chain.put("key1", 1)
  chain.put("key2", 2)
  println(chain.get("key1"))
  println(chain.get("key3"))
  chain.remove("key1")
  println(chain.get("key1"))
}
