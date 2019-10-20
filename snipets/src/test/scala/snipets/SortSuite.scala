package snipets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SortSuite extends FunSuite {

  test("Sort.mergeSort sorts the list") {
    val list = List(1, 5, 3, 6, 7, 9, 4, 6, 3, 7, 9, 3, 3, 5, 2, 4)
    assert(Sort.mergeSort(list) == List(1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 9, 9))
  }

}