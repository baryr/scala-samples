package reductions

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import reductions.ParallelParenthesesBalancing._

@RunWith(classOf[JUnitRunner])
class ParallelParenthesesBalancingSuite extends FunSuite {

  test("balance should work for empty string") {
    def check(input: String, expected: Boolean): Unit = assert(balance(input.toArray) == expected, s"balance($input) should be $expected")

    check("", true)
  }

  test("parBalance should work for empty string") {
    def check(input: String, expected: Boolean): Unit = assert(parBalance(input.toArray, 2) == expected, s"parBalance($input) should be $expected")

    check("", true)
  }

  test("balance should work for string of length 1") {
    def check(input: String, expected: Boolean):Unit = assert(balance(input.toArray) == expected, s"balance($input) should be $expected")

    check("(", false)
    check(")", false)
    check(".", true)
  }

  test("parBalance should work for string of length 1") {
    def check(input: String, expected: Boolean):Unit = assert(parBalance(input.toArray, 2) == expected, s"parBalance($input) should be $expected")

    check("(", false)
    check(")", false)
    check(".", true)
  }

  test("balance should work for string of length 2") {
    def check(input: String, expected: Boolean):Unit = assert(balance(input.toArray) == expected, s"balance($input) should be $expected")

    check("()", true)
    check(")(", false)
    check("((", false)
    check("))", false)
    check(".)", false)
    check(".(", false)
    check("(.", false)
    check(").", false)
  }

  test("parBalance should work for string of length 2") {
    def check(input: String, expected: Boolean):Unit = assert(parBalance(input.toArray, 2) == expected, s"parBalance($input) should be $expected")

    check("()", true)
    check(")(", false)
    check("((", false)
    check("))", false)
    check(".)", false)
    check(".(", false)
    check("(.", false)
    check(").", false)
  }

  test("parBalance should work for nested parentheses and threshold 1") {
    def check(input: String, expected: Boolean):Unit = assert(parBalance(input.toArray, 1) == expected, s"parBalance($input) should be $expected")

    check("((()))", true)
  }

}