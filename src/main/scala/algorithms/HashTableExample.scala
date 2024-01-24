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


class HashTableBinary(val size: Int) {
  private val table = Array.fill[Option[Node]](size)(None)

  private def hash(key: Int): Int = {
    (key.hashCode() & 0x7fffffff) % size // Ensure positive index
  }

  def put(node: Node): Unit = {
    val index = hash(node.key)
    table(index) = BST.insert(table(index), node)
  }

  def get(key: Int): Option[Node] = {
    val index = hash(key)
    BST.get(table(index), key)
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

  val bin = new HashTableBinary(10)
  var root: Option[Node] = None
  val nodes = List((3, "Kyiv"), (7, "Dnipro"), (1, "Lviv"), (5, "Mariupol"), (9, "Zhytomyr"))

  nodes.foreach { case(key, value) =>
    bin.put(Node(key, value))
  }
  println(bin.get(3))
  println(bin.get(5))
  println(bin.get(11))
}
