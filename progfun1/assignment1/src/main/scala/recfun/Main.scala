package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c < 0 || c > r) 0
      else if (c == 0 || c == r) 1
      else pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      val signs = chars.map({
        case '(' => 1
        case ')' => -1
        case _ => 0
      })
      .filter(c => c != 0)
      
      def innerBalance(sum: Int, signs: List[Int]): Boolean = signs match {
        case Nil => sum == 0
        case head :: Nil => sum + head == 0
        case head :: tail => ((sum + head) >= 0 && innerBalance(sum + head, tail)) 
      }
      
      innerBalance(0, signs);      
    }
    
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money > 0 && !coins.isEmpty) countChange(money - coins.head, coins) + countChange(money, coins.tail) 
      else if (money == 0) 1 else 0
    }
    
  }
