package snipets

object Streams extends App {

  def someStream(): Stream[Int] = {
    lazy val generate: Stream[Int] = 1 #:: generate map (_ + 1)
    generate
  }

  println(someStream().take(5).toList)


  def from(n: Int): Stream[Int] = n #:: from(n+1)
  def sieve(s: Stream[Int]): Stream[Int] = s.head #:: sieve(s.tail.filter(_ % s.head != 0))
  def primes: Stream[Int] = sieve(from(2))

  println(primes.take(1000).toList)

}
