package snipets

object QueensOnBoard extends App {

  def placeQueens(n: Int): Set[List[Int]] = {
    def placeQueensIter(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueensIter(k - 1)
          col <- 0 until n
          if (isSafe(col, queens))
        } yield col :: queens
    }

    def isSafe(col: Int, partialSolution: List[Int]): Boolean = {
      val row = partialSolution.length
      val placedQueens = (row - 1 to 0 by -1) zip partialSolution
      placedQueens.forall {
        case (r, c) => col != c && notOnSameDiagonal((row, col), (r, c))
      }
    }

    def notOnSameDiagonal(p1: (Int, Int), p2: (Int, Int)): Boolean = {
      math.abs(p1._1 - p2._1) != math.abs(p1._2 - p2._2)
    }

    placeQueensIter(n)
  }

  def show(solution: List[Int]): String = {
    val lines = for (s <- solution.reverse)
      yield Vector.fill(solution.length)("* ").updated(s, "X ").mkString

    "\n" + (lines mkString "\n")
  }

  val solution = placeQueens(8)
  println(solution)
  println((solution map show) mkString "\n")
}
