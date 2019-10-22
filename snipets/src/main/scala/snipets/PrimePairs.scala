package snipets

object PrimePairs extends App {

  val N:Int = 10

  def isPrime(n: Int): Boolean = (2 until n) forall (n % _ != 0)

  val primePairs1 = (1 until N) flatMap (i => (1 until i) map (j => (i, j))) filter (pair => isPrime(pair._1 + pair._2))
  val primePairs2 = for {
      i <- 1 until N
      j <- 1 until i
      if isPrime(i + j)
    } yield (i, j)

  println(primePairs1)
  println(primePairs2)

}
