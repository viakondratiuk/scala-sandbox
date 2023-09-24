/*
  volatile is a possibility to share mutable state between threads
  - guarantees direct reads and write to memory not in cache
  - atomic reads and writes, read or write done in a single step that cant be interrupted
  - ordering, compile wont reorder volatile operations
  - non-blocking, threads can read and write in atomic manner
  - used for flags
 */

def thread(body: =>Unit): Thread = {
  val t = new Thread {
    override def run(): Unit = body
  }
  t.start()
  t
}

class Page(val txt: String, var position: Int)

val pages = for (i<- 1 to 5)
  yield new Page("Na" * (100 - 20 * i) + " Batman!", -1)

@volatile var found = false

for (p <- pages) yield thread {
  var i = 0
  while (i < p.txt.length && !found)
    if (p.txt(i) == '!') {
      p.position = i
      found = true
    } else i += 1
}

while (!found) {}

println(s"results: ${pages.map(_.position)}")
