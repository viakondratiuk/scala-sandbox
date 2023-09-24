"abc".map(_.toUpper) // Predef.StringCanBuildFrom  CanBuildFrom[String, Char, String]
"abc".map(_ + "*") // Predef.fallbackStringCanBuildFrom[String]  CanBuildFrom[String, T, immutable.IndexedSeq[T]]
"abc".map(_.toInt) // Predef.fallbackStringCanBuildFrom[Int]

Map(1 -> "one", 2 -> "two").map {case(k,v) => k -> v.length} // returns Map
Map(1 -> "one", 2 -> "two").map(_._2) // returns Iterable

List(1,2,3).map(_ + 1)

List('a', 'b', 'c').map(x => x.toInt -> x)
List('a', 'b', 'c').map(x => x.toInt -> x).view.toMap
val m: Map[Int, Char] = List('a', 'b', 'c').map(x => x.toInt -> x)(collection.breakOut)
//def breakOut[From, T, To](implicit b: CanBuildFrom[Nothing, T, To]): CanBuildFrom[From, T, To]
List('a', 'b', 'c').map(_.toUpper)(collection.breakOut)
// CanBuildFrom[String, Char, String]

