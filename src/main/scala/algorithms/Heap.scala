package algorithms

import scala.collection.mutable.ArrayBuffer

/**
 * Priority Queue
 * - ADT
 * - Most common is heap
 *
 *  Heap
 *  - Complete binary tree
 *  - DS with max/min on top
 *  - Efficiently access highest/lowest priority element
 *  - implemented as Array
 * - Operations
 *  -- insert O(log_N)
 *  -- extract O(log_N)
 *  -- peek O(1)
 *
 *  -- left 2n + 1
 *  -- right 2n + 2
 *  -- parent (n - 1) / 2
 *
 *  Simulations
 * - A series of events, all of which happen at specific times
 * - An event occurrence may cause other events to happen
 * - Have to process all of the events in order
 */

object Heap extends App {

  object MaxHeap {
    def insert(a: ArrayBuffer[Int], key: Int): Unit = {
      a.append(key)
      heapifyUp(a, a.length - 1)
    }

    private def heapifyUp(a: ArrayBuffer[Int], index: Int): Unit = {
      var currentIndex = index
      while (currentIndex > 0 && a(parent(currentIndex)) < a(currentIndex)) {
        swap(a, currentIndex, parent(currentIndex))
        currentIndex = parent(currentIndex)
      }
    }

    private def parent(i: Int): Int = (i - 1) / 2
    private def leftChild(i: Int): Int = 2 * i + 1
    private def rightChild(i: Int): Int = 2 * i + 2

    def peek(a: ArrayBuffer[Int]): Int = a.headOption.getOrElse(throw new NoSuchElementException("Heap is empty"))

    def extract(a: ArrayBuffer[Int]): Int = {
      if (a.isEmpty) throw new NoSuchElementException("Heap is empty")
      val max = a(0)
      a(0) = a.last
      a.reduceToSize(a.length - 1)
      heapifyDown(a, 0)
      max
    }

    private def swap(a: ArrayBuffer[Int], i: Int, j: Int): Unit = {
      val temp = a(i)
      a(i) = a(j)
      a(j) = temp
    }

    private def heapifyDown(a: ArrayBuffer[Int], i: Int): Unit = {
      val left = leftChild(i)
      val right = rightChild(i)
      var largest = i

      if (left < a.length && a(left) > a(largest)) {
        largest = left
      }

      if (right < a.length && a(right) > a(largest)) {
        largest = right
      }

      if (largest != i) {
        swap(a, i, largest)
        heapifyDown(a, largest)
      }
    }

    def build(a: ArrayBuffer[Int]): ArrayBuffer[Int] = {
      for (i <- (a.length / 2 - 1) to 0 by -1) {
        heapifyDown(a, i)
      }
      a
    }
  }

  val test: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4,5,6)
/*
      6
    /   \
   5     3
  / \   /
 4   2 1

*/

  MaxHeap.build(test)

  println(s"Max Heap: $test")
  println(s"Max element: ${MaxHeap.peek(test)}")
  println(s"Deleting max element: ${MaxHeap.extract(test)}")
  println(s"Max Heap after deletion: $test")
  println(s"Inserting element: 7")
  MaxHeap.insert(test, 7)
  println(s"Max Heap after insertion: $test")
}
