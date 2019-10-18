import common._



object Worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  object sim extends Circuits with Parameters
  import sim._
  
  val in1, in2, sum, carry = new Wire             //> in1  : Worksheet.sim.Wire = common.Gates$Wire@3ac3fd8b
                                                  //| in2  : Worksheet.sim.Wire = common.Gates$Wire@5594a1b5
                                                  //| sum  : Worksheet.sim.Wire = common.Gates$Wire@6a5fc7f7
                                                  //| carry  : Worksheet.sim.Wire = common.Gates$Wire@3b6eb2ec
  
  halfAdder(in1, in2, sum, carry)
  probe("sum", sum)                               //> sum 0 value = false
  probe("carry", carry)                           //> carry 0 value = false
  
  in1 setSignal true
  run()                                           //> **** simulation started, time = 0 ****
                                                  //| sum 5 value = true
                                                  //| sum 10 value = false
                                                  //| sum 10 value = true
  in2 setSignal true
  run()                                           //> **** simulation started, time = 10 ****
                                                  //| carry 13 value = true
                                                  //| sum 18 value = false
  in1 setSignal false
  run()                                           //> **** simulation started, time = 18 ****
                                                  //| carry 21 value = false
                                                  //| sum 26 value = true
	def bang(x: Int): Int = if (x == 0) throw new Exception("bang!") else bang(x - 1)
                                                  //> bang: (x: Int)Int
  //bang(3);
  val fun = bang _                                //> fun  : Int => Int = <function1>
  fun(3);                                         //> java.lang.Exception: bang!
                                                  //| 	at Worksheet$$anonfun$main$1.Worksheet$$anonfun$$bang$1(Worksheet.scala:
                                                  //| 23)
                                                  //| 	at Worksheet$$anonfun$main$1$$anonfun$1.apply$mcII$sp(Worksheet.scala:25
                                                  //| )
                                                  //| 	at Worksheet$$anonfun$main$1.apply$mcV$sp(Worksheet.scala:26)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at Worksheet$.main(Worksheet.scala:5)
                                                  //| 	at Worksheet.main(Worksheet.scala)
                   
                                                  
                                                  
  
 
}