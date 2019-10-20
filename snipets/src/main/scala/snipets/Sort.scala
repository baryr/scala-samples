package snipets

object Sort extends App {

  def mergeSort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(left: List[T], right: List[T]): List[T] = (left, right) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs, y :: ys) => if (ord.lt(x, y)) x :: merge(xs, right) else y :: merge(left, ys)
      }
      val (left, right) = xs splitAt n
      merge(mergeSort(left), mergeSort(right))
    }
  }

  val list = List(1, 5, 3, 6, 7, 9, 4, 6, 3, 7, 9, 3, 3, 5, 2, 4)

  println(list)
  println(mergeSort(list))
}
