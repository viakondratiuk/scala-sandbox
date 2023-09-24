// expression problem: we want evaluate and get a proper value

// true && false || true => Or(And(Leaf(true), Leaf(false)), Leaf(true))

object Problem {
  trait Expr // the "tree"
  case class B(boolean: Boolean) extends Expr
  case class Or(left: Expr, right: Expr) extends Expr
  case class And(left: Expr, right: Expr) extends Expr
  case class Not(expr: Expr) extends Expr

  def eval(expr: Expr): Boolean = expr match {
    case B(b) => b
    case Or(a, b) => eval(a) || eval(b)
    case And(a, b) => eval(a) && eval(b)
    case Not(e) => !eval(e)
  }

  case class I(int: Int) extends Expr
  case class Sum(left: Expr, right: Expr) extends Expr

  def eval_v2(expr: Expr): Any = expr match { // losing type safety with Any
    case B(b) => b
    case Or(a, b) => eval(a).asInstanceOf[Boolean] || eval(b).asInstanceOf[Boolean]
    // casts everywhere
  }
}

// How do we return the right type for the right expression?

object Tagging {
  trait Expr {
    val tag: String
  }
  case class B(boolean: Boolean) extends Expr { val tag = "bool"}
  case class Or(left: Expr, right: Expr) extends Expr { val tag = "bool"}
  case class And(left: Expr, right: Expr) extends Expr { val tag = "bool"}
  case class Not(expr: Expr) extends Expr { val tag = "bool"}
  case class I(int: Int) extends Expr { val tag = "int"}
  case class Sum(left: Expr, right: Expr) extends Expr { val tag = "int"}

  // better type checking
  // still runtime casts and Any
  def eval(expr: Expr): Any = expr match {
    case B(b) => b
    case Or(left, right) =>
      if (left.tag == "bool" && right.tag == "bool")
        eval(left).asInstanceOf[Boolean] || eval(right).asInstanceOf[Boolean]
      else
        throw new IllegalArgumentException("attempting to evaluate an expression with improperly typed operands")
    // same for others
  }
}

object TaglessInitial {
  trait Expr[A]
  case class B(boolean: Boolean) extends Expr[Boolean]
  case class Or(left: Expr[Boolean], right: Expr[Boolean]) extends Expr[Boolean]
  case class And(left: Expr[Boolean], right: Expr[Boolean]) extends Expr[Boolean]
  case class Not(expr: Expr[Boolean]) extends Expr[Boolean]
  case class I(int: Int) extends Expr[Int]
  case class Sum(left: Expr[Int], right: Expr[Int]) extends Expr[Int]

//  def eval[A](expr: Expr[A]): A = expr match {
//    case B(b) => b
//    case I(i) => i
//    case Or(left, right) => eval(left) || eval(right)
//    case Sum(left, right) => eval(left) + eval(right)
//    // etc
//  }
}

object TaglessFinal {
  trait Expr[A] {
    val value: A // the final value we care about
  }

  def b(boolean: Boolean): Expr[Boolean] = new Expr[Boolean] {
    val value = boolean
  }

  def i(int: Int): Expr[Int] = new Expr[Int] {
    val value = int
  }

  def or(left: Expr[Boolean], right: Expr[Boolean]) = new Expr[Boolean] {
    val value = left.value || right.value
  }

  def and(left: Expr[Boolean], right: Expr[Boolean]) = new Expr[Boolean] {
    val value = left.value && right.value
  }

  def sum(left: Expr[Int], right: Expr[Int]) = new Expr[Int] {
    val value = left.value + right.value
  }

  def eval[A](expr: Expr[A]): A = expr.value
}
// TODO: Read gof pattern interpretator
def demoTaglessFinal(): Unit = {
  import TaglessFinal._

  println(eval(or(b(true), and(b(true), b(false)))))
  println(eval(sum(i(24), i(-3))))
}

demoTaglessFinal()

object TaglessFinal_V2 {
  trait Algebra[E[_]] {
    def b(boolean: Boolean): E[Boolean]
    def i(int: Int): E[Int]
    def or(left: E[Boolean], right: E[Boolean]): E[Boolean]
    def and(left: E[Boolean], right: E[Boolean]): E[Boolean]
    def sum(left: E[Int], right: E[Int]): E[Int]
  }

  case class SimpleExpr[A](value: A)

  // interpreter
  implicit val simpleExprAlg: Algebra[SimpleExpr] = new Algebra[SimpleExpr] {
    override def b(boolean: Boolean) = SimpleExpr (boolean)
    override def i(int: Int) = SimpleExpr (int)
    override def or(left: SimpleExpr[Boolean], right: SimpleExpr[Boolean]) = SimpleExpr (left.value || right.value)
    override def and(left: SimpleExpr[Boolean], right: SimpleExpr[Boolean]) = SimpleExpr (left.value && right.value)
    override def sum(left: SimpleExpr[Int], right: SimpleExpr[Int]) = SimpleExpr (left.value + right.value)
  }

  def program1[E[_]](implicit alg: Algebra[E]): E[Boolean] = {
    import alg._
    or(b(true), and(b(true), b(false)))
  }

  def program2[E[_]](implicit alg: Algebra[E]): E[Int] = {
    import alg._
    sum(i(24), i(-3))
  }
}

def demoFinalTagless_v2(): Unit = {
  import TaglessFinal_V2._

  println(program1[SimpleExpr].value)
  println(program2[SimpleExpr].value)
}

demoFinalTagless_v2()

// TODO: Func programming for Mortals