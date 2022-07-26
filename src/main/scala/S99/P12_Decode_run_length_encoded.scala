package S99

object P12_Decode_run_length_encoded extends App {

  // List((3, 1), (1, 2), (2, 1)) -> List(1, 1, 1, 2, 1, 1)
  def unpack[B](l: List[(Int, B)]): List[B] =
    l.flatMap(e => List.fill(e._1)(e._2))


  val l0 = List((3, 1), (1, 2), (2, 1))
  val l1 = List((2, 1), (2, 2), (2, 3))
  val l2 = List((1, 1), (5, 2), (1, 1))
  val l3 = List((1, 1), (7, 2))

  assert(unpack(l0) == List(1,1,1,2,1,1))
  assert(unpack(l1) == List(1,1,2,2,3,3))
  assert(unpack(l2) == List(1,2,2,2,2,2,1))
  assert(unpack(l3) == List(1,2,2,2,2,2,2,2))

  println(unpack(l3))
}
