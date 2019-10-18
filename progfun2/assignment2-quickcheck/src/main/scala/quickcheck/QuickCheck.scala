package quickcheck

import common._


import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import scala.collection.mutable.ListBuffer

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    v <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(v,h) 
  
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }
  
  property("empty after insert shoud be not empty") = forAll { (a: Int) =>
    val h = insert(a, empty)
    !isEmpty(h)    
  }
  
  property("not empty after insert shoud be not empty") = forAll { (a: Int, b: Int) =>
    val h1 = insert(a, empty)
    val h2 = insert(a, h1)
    !isEmpty(h2)
  }

  property("after adding and deleting from empty it should stay empty") = forAll { (a: Int) =>
    val h = insert(a, empty)
    val h2 = deleteMin(h)
    isEmpty(h2)
  }

  property("heap should return min element") = forAll { (a: Int, b: Int) =>
        val h1 = insert(a, empty)
        val h2 = insert(b, h1)
        findMin(h2) == Math.min(a,b)
  }
  
  property("min of meldedd heaps should be min of one of them") = forAll { (h1: H, h2: H, a: Int, b: Int) =>
    val h1a = insert(a, h1)
    val h2b = insert(b, h2)
    
    val meldH = meld(h1a, h2b)
    
    findMin(meldH) == Math.min(findMin(h1a), findMin(h2b))
  } 
      
  property("should return ordered list") = forAll { (h: H) => 
    def genOrdList(h: H, list: List[Int]): List[Int] = {
      if (isEmpty(h)) list
      else findMin(h) :: genOrdList(deleteMin(h), list)
    }
    
    val xs = genOrdList(h, Nil)
    xs == xs.sorted
  }
  
  property("meld should return ordered list") = forAll { (h1: H, h2: H) => 
    def genOrdList(h: H, list: List[Int]): List[Int] = {
      if (isEmpty(h)) list
      else findMin(h) :: genOrdList(deleteMin(h), list)
    }
    val xs = genOrdList(meld(h1, h2), Nil)
    xs == xs.sorted
  }

  property("meld ... WTF condition ???") = forAll { (h1: H, h2: H) =>
    def genOrdList(h: H, list: List[Int]): List[Int] = {
      if (isEmpty(h)) list
      else findMin(h) :: genOrdList(deleteMin(h), list)
    }
    
    val meld1 = meld(h1, h2)
    val min1 = findMin(h1)
    val meld2 = meld(deleteMin(h1), insert(min1, h2))
    
    val xs1 = genOrdList(meld1, Nil)
    val xs2 = genOrdList(meld2, Nil)
    xs1 == xs2
  }
  
}
