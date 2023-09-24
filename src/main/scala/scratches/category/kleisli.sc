object Kleisli {
  type Writer[A] = (A, String)

  implicit class KleisliOps[A, B](m1: A => Writer[B]) {
    def >=>[C](m2:B => Writer[C]): A => Writer[C] =
      x => {
        val (y, s1) = m1(x)
        val (z, s2) = m2(y)
        (z, s1 + s2)
      }

    def pure[I](x: I): Writer[I] = (x, "")
  }

  val upCase: String => Writer[String] = s => (s.toUpperCase, "upCase ")
  val toWords: String => Writer[List[String]] = s => (s.split(" ").toList, "toWords ")
  val totalLength: List[String] => Writer[Int] = l => (l.map(_.length).sum, "totalLength ")

  val process: String => Writer[Int] = {
    upCase >=> toWords >=> totalLength
  }
}

Kleisli.process("this is gonna be processed")