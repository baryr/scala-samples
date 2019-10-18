package common

class Pouring(capacity : Vector[Int]) {
  
  // State
  type State = Vector[Int]
  val initialState = capacity map (_ => 0)
  
  val glasses = 0 until capacity.length
  
  // Moves
  trait Move {
    def change(state: State): State 
  }
  case class Empty(glass: Int) extends Move {
    def change(state: State): State = state updated (glass, 0)
  }
  case class Fill(glass: Int) extends Move {
    def change(state: State): State = state updated (glass, capacity(glass))
  }
  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State): State = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }

  val moves = 
        (for (glass <- glasses) yield Empty(glass)) ++ 
        (for (glass <- glasses) yield Fill(glass)) ++
        (for (fromGlass <- glasses; toGlass <- glasses; if fromGlass != toGlass) yield Pour(fromGlass, toGlass))
   
        
  // Paths 
        
  class Path(history: List[Move], val endState : State) {
    def extend(move: Move): Path = new Path(move :: history, move change endState)
    
    /* this was optimized with constructor param
    def endState : State = (history foldRight initialState)(_ change _)
    def extend(move: Move): Path = new Path(move :: history)
    */
    
    /* equivalent version with pattern match and helper function
    def endState : State = trackState(history)
    def trackState(xs : List[Move]): State = xs match {
      case Nil => initialState
      case move :: xs1 => move change trackState(xs1)
    }
    def extend(move: Move): Path = new Path(move :: history)
    */
    
    override def toString = (history.reverse mkString " ") + "--->" + endState
  }
  
  val initialPath = new Path(Nil, initialState)
  
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    // Edge case 
    if (paths.isEmpty) Stream.empty
    else {
      /// build new longer paths
      val longerPaths = for {
        path <- paths
        nextPath <- moves map path.extend
        if !(explored contains nextPath.endState)
      } yield nextPath
      paths #:: from(longerPaths, explored ++ (longerPaths map (_.endState)))
    }
  
  val pathSets: Stream[Set[Path]] = from(Set(initialPath), Set(initialState))
  
  def solution(target: Int): Stream[Path] = 
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
    
}