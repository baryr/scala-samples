package snipets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PackDuplicatesSuite extends FunSuite {

  test("PackDuplicates.pack should pack the list") {
    val list = List("a", "a", "a", "b", "b", "c", "d", "e", "e", "f")

    assert(PackDuplicates.pack(list) == List(List("a", "a", "a"), List("b", "b"), List("c"), List("d"), List("e", "e"), List("f"))
    )
  }

}