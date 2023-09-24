// B depends on A
trait Last[A] {
  type B
  def last(a: A): B
}

object Last {
  type Aux[A,B0] = Last[A] { type B = B0 }

  implicit def tuple1Last[A]: Aux[Tuple1[A],A] = new Last[Tuple1[A]] {
    type B = A
    def last(a: Tuple1[A]) = a._1
  }

  implicit def tuple2Last[A,C]: Aux[(A,C),C] = new Last[(A,C)] {
    type B = C
    def last(a: (A,C)) = a._2
  }
}

def sort[A,B](as: List[A])(implicit last: Last.Aux[A,B], ord: Ordering[B]) = as.sortBy(last.last)
def sort1[A,B0](as: List[A])(implicit last: Last[A] { type B = B0 }, ord: Ordering[B0]) = as.sortBy(last.last)

sort(List(("ffle",3), ("fu",2), ("ker",1)))
sort(List(("ffle","mmm"), ("fu","mm"), ("ker","mmmmm")))

sort1(List(("ffle",3), ("fu",2), ("ker",1)))
