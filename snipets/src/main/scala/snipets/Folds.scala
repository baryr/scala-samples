package snipets

object Folds extends App {

  val list1:List[Int] = List(1, 2, 3, 4, 5)
  val list2:List[Int] = List(6, 7, 8, 9)

  println((list1 foldRight list2)(_ :: _))
  // Why this does not work?
  // println((list1 foldLeft list2)(_ :: _))
  //
  // Explanation:
  // With placeholder syntax for foldLeft - (_ :: _) - you are trying to do something like this: aggregator :: element
  // This means element.::(aggregator) and there is no :: method in element (type of element is T).
}
