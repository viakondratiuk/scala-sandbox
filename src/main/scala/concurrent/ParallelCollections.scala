package concurrent

import scala.collection.parallel._
import scala.concurrent.forkjoin.ForkJoinPool


// USE: CPU bound, large data sets, independent, not IO bound
// NOT USE: Overhead of parallel, side effects, order, tiny tasks

object Duration {
  def measure[A](block: => A): (A, Long) = {
    val startTime = System.nanoTime()
    val result = block
    val endTime = System.nanoTime()
    val duration = (endTime - startTime) / 1000000
    (result, duration)
  }
}

object Playground {
  def computeWithParallelism(data: ParSeq[Int], parallelism: Int): Long = {
    val forkJoinPool = new ForkJoinPool(parallelism)
    data.tasksupport = new ForkJoinTaskSupport(forkJoinPool)

    val (_, duration) = Duration.measure {
      data.map(x => x * x)
    }

    duration
  }

  def compareSeqPar() = {
    val largeList = (1 to 10000000).toList

    // Sequential computation
    val (_, sequentialTime) = Duration.measure {
      largeList.map(x => x * x)
    }

    // Parallel computation
    val (_, parallelTime) = Duration.measure {
      largeList.par.map(x => x * x)
    }

    println(s"Sequential time: $sequentialTime ms")
    println(s"Parallel time: $parallelTime ms")
  }

  def testParallel() = {
    val largeList = (1 to 10000000).toList
    val parallelList = largeList.par

    val cores = Runtime.getRuntime.availableProcessors()

    println(s"Available cores: $cores")
    println("-------------------------")

    for (i <- 1 to cores) {
      val duration = computeWithParallelism(parallelList, i)
      println(s"Parallelism Level $i: $duration ms")
    }
  }
}

object ParallelCollections extends App {
//  Playground.compareSeqPar()
  Playground.testParallel()
}
