package reductions

import org.scalameter._

import scala.annotation.tailrec

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    val signs = chars.map({
      case '(' => 1
      case ')' => -1
      case _ => 0
    })
      .filter(c => c != 0)
      .toList

    @tailrec
    def innerBalance(sum: Int, signs: List[Int]): Boolean = signs match {
      case Nil => sum == 0
      case head :: Nil => sum + head == 0
      case head :: tail => ((sum + head) >= 0 && innerBalance(sum + head, tail))
    }

    innerBalance(0, signs)
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {
    val signs = chars.map({
      case '(' => 1
      case ')' => -1
      case _ => 0
    }).toList

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int): (Int, Int) = {
      if (idx < until) {
        signs(idx) match {
          case 1 => traverse(idx + 1, until, arg1 + 1, arg2)
          case -1 if arg1 > 0 => traverse(idx + 1, until, arg1 - 1, arg2)
          case -1 => traverse(idx + 1, until, arg1, arg2 + 1)
          case 0 => traverse(idx + 1, until, arg1, arg2)
        }
      } else {
        (arg1, arg2)
      }
    }

    def reduce(from: Int, until: Int): (Int, Int) = {
      val chunk = until - from
      if (chunk > threshold) {
        val half = chunk / 2
        // (l1, 0) (l2, 0)
        // (l1, 0) (0, r2)
        // (0, r1) (l2, 0)
        // (0, r1) (0, r2)
        val ((l1, r1), (l2, r2)) = common.parallel(reduce(from, from + half), reduce(from + half, until))
        if (l1 > r2) {
          (l1 - r2 + l2, r1)
        } else {
          (l2, r2 - l1 + r1)
        }
      } else {
        traverse(from, until, 0, 0)
      }
    }

    reduce(0, chars.length) == (0, 0)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
