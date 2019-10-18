import common._

object Worksheet {
  val problem: Pouring = new Pouring(Vector(4,9, 18))
                                                  //> problem  : common.Pouring = common.Pouring@1e67b872
 
  problem.glasses                                 //> res0: scala.collection.immutable.Range = Range(0, 1, 2)
  problem.moves                                   //> res1: scala.collection.immutable.IndexedSeq[Product with Serializable with W
                                                  //| orksheet.problem.Move] = Vector(Empty(0), Empty(1), Empty(2), Fill(0), Fill(
                                                  //| 1), Fill(2), Pour(0,1), Pour(0,2), Pour(1,0), Pour(1,2), Pour(2,0), Pour(2,1
                                                  //| ))
  problem.initialPath.toString                    //> res2: String = --->Vector(0, 0, 0)
  
  problem.pathSets.take(3).toList                 //> res3: List[Set[Worksheet.problem.Path]] = List(Set(--->Vector(0, 0, 0)), Set
                                                  //| (Fill(0)--->Vector(4, 0, 0), Fill(1)--->Vector(0, 9, 0), Fill(2)--->Vector(0
                                                  //| , 0, 18)), Set(Fill(2) Pour(2,0)--->Vector(4, 0, 14), Fill(1) Fill(2)--->Vec
                                                  //| tor(0, 9, 18), Fill(1) Fill(0)--->Vector(4, 9, 0), Fill(1) Pour(1,2)--->Vect
                                                  //| or(0, 0, 9), Fill(0) Pour(0,2)--->Vector(0, 0, 4), Fill(0) Pour(0,1)--->Vect
                                                  //| or(0, 4, 0), Fill(2) Pour(2,1)--->Vector(0, 9, 9), Fill(0) Fill(1)--->Vector
                                                  //| (4, 9, 0), Fill(2) Fill(1)--->Vector(0, 9, 18), Fill(1) Pour(1,0)--->Vector(
                                                  //| 4, 5, 0), Fill(0) Fill(2)--->Vector(4, 0, 18), Fill(2) Fill(0)--->Vector(4, 
                                                  //| 0, 18)))
  
  problem.solution(15)                            //> res4: Stream[Worksheet.problem.Path] = Stream(Fill(2) Pour(2,0) Pour(0,1) Fi
                                                  //| ll(1) Pour(2,0) Empty(0) Pour(1,0) Pour(1,2)--->Vector(4, 0, 15), ?)
}