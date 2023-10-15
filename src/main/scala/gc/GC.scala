package gc

object GC extends App {
  /*

  GC:
    - Reclaiming of memory that's no longer in use

  Generational Hypothesis:
    - Java's garbage collection is based on the observation that most objects are short-lived.
    - Young generation is typically collected more frequently,
      but it's generally fast because it's smaller and has many short-lived objects. This is a "Minor GC".
    - Old generation collections happen less frequently but might take longer because they involve more objects.
      This is a "Major GC" or sometimes referred to as "Full GC" when it involves the entire heap including the young generation.

  Generational GC:
    - Young
        Small space, collect frequently, low survive

        - Eden
            New objects allocated, empty after minor gc
        - G0, G1
            Objects survive 1 minor gc, have a chance to become unreachable, only one holds objects

        - GC
            Live eden objects copied to unused space
            Live survivors also copied to give another reclaim chance
            Old enough promoted to old
            Eden empty, survivors swap
            TODO: ask abot card table

    - Old
        Long live objects promoted here
        Larger than young, grows slowly
        Collections are infrequent and slow

        - GC
          Mark-Sweep-Compact for Old Generation:
            - Mark - identify all objects still alive
            - Sweep - delete all unmarked
            - Compact - move marked object to the beginning of heap

    - Permanent, Metaspace
        Class data structure, interned strings

  Minor and Full GC:
    - Both stop-the-world action

  Latency -> Throughout -> Footprint

  Types of GC:
    - Serial
        Uses one thread
        Stop-the-world
        For single thread apps or low heap apps(client), pause time doesn't influence performance

        Latency(h) -> Throughout(l) -> Footprint(l)
    - Parallel
        Use multiple threads
        For multithreaded apps
        Pauses can be shorter than Serial
        Big heap
        Used for server-side and maximize throughput
        Spark

        Latency(m) -> Throughout(h) -> Footprint(m)
    - CMS
        Rapid response time
        Exists because long pauses of full gc
        Young gen as Serial and Parallel
        Old gen is concurrently with 2 small pauses(initial mark and remark)
        Main disadvantage: Defragmentation in time Full GC, compaction

        Latency(l) -> Throughout(m) -> Footprint(h)

    - G1 replace CMS is parallel, concurrent, and incrementally compacting low-pause garbage collector

        Split heap is equal-size chunks
        No space for young and old, each generation is set of regions
        Evacuate survivors from one space to another
        Mostly minor gc
        Has a process of almost empty not young regions
        Less of fragmentation
        Latency adjustable Throughout

        Latency(m) -> Throughout(m) -> Footprint(h)

  Creating work for GC:
    - Allocation Failure: When there's not enough space to allocate a new object in the Eden space.
    - Promotion Failure: When there's not enough space to move a long-surviving object from the young to the old generation.
    - Explicit GC Calls: Via System.gc() or other JVM operations.
    - JVM Heuristics: JVM's internal mechanisms might trigger GC based on various factors.
    ? Ref update in old generation

    - Old GC - gc after 60%

    ? Memory leak
    ? Heap dump

  ?JMX - collect gc metrics
  ?ZGC - new gc
  ?Python

  ???

   */
}
