package snipets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FlattenSuite extends FunSuite {

  test("Flatten.flatten flattens the list") {
    val list: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
    assert(Flatten.flatten(list) == List(1, 1, 2, 3, 5, 8))
  }

}