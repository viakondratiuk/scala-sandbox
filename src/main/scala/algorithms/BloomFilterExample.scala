package algorithms

import scala.util.hashing.MurmurHash3

class BloomFilter[T](val size: Int, val hashFunctions: Int) {
  private val bits = new Array[Boolean](size)

  def add(item: T): Unit = {
    (1 to hashFunctions).foreach { i =>
      val hash = MurmurHash3.stringHash(item.toString, i) % size
      bits(Math.abs(hash)) = true
    }
  }

  private def mightContain(item: T): Boolean = {
    (1 to hashFunctions).forall { i =>
      val hash = MurmurHash3.stringHash(item.toString, i) % size
      bits(Math.abs(hash))
    }
  }

  def check(item: T): Unit = {
    if (mightContain(item)) {
      println(s"[$item] might be blacklisted.")
    } else {
      println(s"[$item] is not blacklisted.")
    }
  }
}


object BloomFilterExample extends App {
  val passwordBloom = new BloomFilter[String](1000, 3)
  // blacklisted
  passwordBloom.add("password123")
  passwordBloom.add("123456")

  passwordBloom.check("password123")
  passwordBloom.check("cccc")

  val orphoBloom = new BloomFilter[String](1000, 3)
  // blacklisted
  orphoBloom.add("cot")
  orphoBloom.add("dag")

  orphoBloom.check("cot")
  orphoBloom.check("cat")
}
