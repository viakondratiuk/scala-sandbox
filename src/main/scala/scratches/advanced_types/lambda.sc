import scala.language.higherKinds

trait Wrapper[F[_]]

type FixedKeyMap = ({type T[V] = Map[Int, V]})#T

val instance: Wrapper[FixedKeyMap] = ???