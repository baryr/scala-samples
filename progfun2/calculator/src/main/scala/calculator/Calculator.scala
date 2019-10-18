package calculator

sealed abstract class Expr
final case class Literal(v: Double) extends Expr
final case class Ref(name: String) extends Expr
final case class Plus(a: Expr, b: Expr) extends Expr
final case class Minus(a: Expr, b: Expr) extends Expr
final case class Times(a: Expr, b: Expr) extends Expr
final case class Divide(a: Expr, b: Expr) extends Expr

object Calculator {
  def computeValues(namedExpressions: Map[String, Signal[Expr]]): Map[String, Signal[Double]] = {
    def mapFunction(tuple: Tuple2[String, Signal[Expr]]): Tuple2[String, Signal[Double]] = {
      (tuple._1, Signal(eval(tuple._2(), namedExpressions)))
    }
    namedExpressions.map(mapFunction)
  }

  def eval(expr: Expr, references: Map[String, Signal[Expr]]): Double = expr match {
    case Literal(v) => v
    case Ref(name) => eval(getReferenceExpr(name, references), references - name)
    case Plus(a, b) => eval(a, removeRefFromReferences(b, references)) + eval(b, removeRefFromReferences(a, references))
    case Minus(a, b) => eval(a, removeRefFromReferences(b, references)) - eval(b, removeRefFromReferences(a, references))
    case Times(a, b) => eval(a, removeRefFromReferences(b, references)) * eval(b, removeRefFromReferences(a, references))
    case Divide(a, b) => eval(a, removeRefFromReferences(b, references)) / eval(b, removeRefFromReferences(a, references))
  }

  /** Get the Expr for a referenced variables.
   *  If the variable is not known, returns a literal NaN.
   */
  private def getReferenceExpr(name: String, references: Map[String, Signal[Expr]]) = {
    references.get(name).fold[Expr] {
      Literal(Double.NaN)
    } { 
      exprSignal => exprSignal()
    }
  }
  
  private def removeRefFromReferences(expr: Expr, references: Map[String, Signal[Expr]]) = expr match {
    case Ref(name) =>  references - name
    case _ => references
  }
}
