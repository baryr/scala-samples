
import common._
import streams._

object Workseet {
  
  object InfiniteLevel extends Solver with InfiniteTerrain {
    val startPos = Pos(3,3)
    val goal = Pos(3,6)
  }
  
	var problem = InfiniteLevel               //> problem  : Workseet.InfiniteLevel.type = Workseet$InfiniteLevel$@66cd51c3
	
	problem.startBlock                        //> res0: Workseet.InfiniteLevel.Block = Block(Pos(3,3),Pos(3,3))
	problem.startBlock.left                   //> res1: Workseet.InfiniteLevel.Block = Block(Pos(3,1),Pos(3,2))
	problem.startBlock.up                     //> res2: Workseet.InfiniteLevel.Block = Block(Pos(1,3),Pos(2,3))
	
	problem.startBlock.neighbors              //> res3: List[(Workseet.InfiniteLevel.Block, Workseet.InfiniteLevel.Move)] = Li
                                                  //| st((Block(Pos(3,1),Pos(3,2)),Left), (Block(Pos(1,3),Pos(2,3)),Up), (Block(Po
                                                  //| s(3,4),Pos(3,5)),Right), (Block(Pos(4,3),Pos(5,3)),Down))
  problem.startBlock.legalNeighbors               //> res4: List[(Workseet.InfiniteLevel.Block, Workseet.InfiniteLevel.Move)] = Li
                                                  //| st((Block(Pos(3,1),Pos(3,2)),Left), (Block(Pos(1,3),Pos(2,3)),Up), (Block(Po
                                                  //| s(3,4),Pos(3,5)),Right), (Block(Pos(4,3),Pos(5,3)),Down))
  problem.done(problem.startBlock)                //> res5: Boolean = false
  problem.done(problem.Block(problem.goal, problem.goal))
                                                  //> res6: Boolean = true
                                                  
  problem.neighborsWithHistory(problem.startBlock, List()).toList
                                                  //> res7: List[(Workseet.InfiniteLevel.Block, List[Workseet.InfiniteLevel.Move])
                                                  //| ] = List((Block(Pos(3,1),Pos(3,2)),List(Left)), (Block(Pos(1,3),Pos(2,3)),Li
                                                  //| st(Up)), (Block(Pos(3,4),Pos(3,5)),List(Right)), (Block(Pos(4,3),Pos(5,3)),L
                                                  //| ist(Down)))
  // problem.solution
}