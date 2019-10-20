package snipets

object Flatten  extends App {

  def flatten(xs: List[Any]): List[Any] = xs match {
    case List() => List()
    case x :: xs => x match {
      case x: List[Any] => flatten(x) ::: flatten(xs)
      case x: Any => x :: flatten(xs)
    }
  }

  val list = List(List(1, 1), 2, List(3, List(5, 8)))

  println(list)
  println(flatten(list))
}
