package snipets

object PackDuplicates extends App {

  def pack[T](list: List[T]): List[List[T]] = list match {
    case Nil => Nil
    case x :: something => {
      val (same, rest) = list.span(y => y == x)
      same :: pack(rest)
    }
  }

  val list = List("a", "a", "a", "b", "b", "c", "d", "e", "e", "f")

  println(list)
  println(pack(list))
  println(pack(list).map(l => (l.head, l.size)))
}
