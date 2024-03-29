package reductions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import reductions.ParallelCountChange._

@RunWith(classOf[JUnitRunner])
class ParallelCountChangeSuite extends FunSuite {

  test("countChange should return 0 for money < 0") {
    def check(money: Int, coins: List[Int]): Unit = assert(countChange(money, coins) == 0, s"countChang($money, _) should be 0")

    check(-1, List())
    check(-1, List(1, 2, 3))
    check(-Int.MinValue, List())
    check(-Int.MinValue, List(1, 2, 3))
  }

  test("parCountChange should return 0 for money < 0") {
    def check(money: Int, coins: List[Int]): Unit = assert(parCountChange(money, coins, combinedThreshold(money, coins)) == 0, s"parCountChange($money, _) should be 0")

    check(-1, List())
    check(-1, List(1, 2, 3))
    check(-Int.MinValue, List())
    check(-Int.MinValue, List(1, 2, 3))
  }

  test("countChange should return 1 when money == 0") {
    def check(coins: List[Int]): Unit = assert(countChange(0, coins) == 1, s"countChang(0, _) should be 1")

    check(List())
    check(List(1, 2, 3))
    check(List.range(1, 100))
  }

  test("parCountChange should return 1 when money == 0") {
    def check(coins: List[Int]): Unit = assert(parCountChange(0, coins, combinedThreshold(0, coins)) == 1, s"parCountChange(0, _) should be 1")

    check(List())
    check(List(1, 2, 3))
    check(List.range(1, 100))
  }

  test("countChange should return 0 for money > 0 and coins = List()") {
    def check(money: Int): Unit = assert(countChange(money, List()) == 0, s"countChang($money, List()) should be 0")

    check(1)
    check(Int.MaxValue)
  }

  test("parCountChange should return 0 for money > 0 and coins = List()") {
    def check(money: Int): Unit = assert(parCountChange(money, List(), combinedThreshold(money, List())) == 0, s"parCountChange($money, List()) should be 0")

    check(1)
    check(Int.MaxValue)
  }

  test("countChange should work when there is only one coin") {
    def check(money: Int, coins: List[Int], expected: Int): Unit = assert(countChange(money, coins) == expected, s"countChange($money, $coins) should be $expected")

    check(1, List(1), 1)
    check(2, List(1), 1)
    check(1, List(2), 0)
    check(Int.MaxValue, List(Int.MaxValue), 1)
    check(Int.MaxValue - 1, List(Int.MaxValue), 0)
  }

  test("parCountChange should work when there is only one coin") {
    def check(money: Int, coins: List[Int], expected: Int): Unit = assert(parCountChange(money, coins, combinedThreshold(money, coins)) == expected, s"parCountChange($money, $coins) should be $expected")

    check(1, List(1), 1)
    check(2, List(1), 1)
    check(1, List(2), 0)
    check(Int.MaxValue, List(Int.MaxValue), 1)
    check(Int.MaxValue - 1, List(Int.MaxValue), 0)
  }

  test("countChange should work for multi-coins") {
    def check(money: Int, coins: List[Int], expected: Int): Unit = assert(countChange(money, coins) == expected, s"countChange($money, $coins) should be $expected")

    check(50, List(1, 2, 5, 10), 341)
    check(250, List(1, 2, 5, 10, 20, 50), 177863)
  }

  test("parCountChange should work for multi-coins") {
    def check(money: Int, coins: List[Int], expected: Int): Unit = assert(parCountChange(money, coins, combinedThreshold(money, coins)) == expected, s"parCountChange($money, $coins) should be $expected")

    check(50, List(1, 2, 5, 10), 341)
    check(250, List(1, 2, 5, 10, 20, 50), 177863)
  }
}
