package HRMS


object Implicit_Classes extends App {

  sealed trait Currency
  object Currency {
    case object EUR extends Currency
    case object USD extends Currency
    case object GBP extends Currency
  }

  case class Money(amount: Double, currency: Currency)

  object MoneySyntax {
    implicit class RichMoney(val amount: Double) extends AnyVal {
      def euros: Money = Money(amount, Currency.EUR)
      def dollars: Money = Money(amount, Currency.USD)
      def pounds: Money = Money(amount, Currency.GBP)
    }
  }

  import HRMS.Implicit_Classes.MoneySyntax._

  val amount: Double = 30.5

  val euros: Money = amount.euros
  val dollars: Money = amount.dollars
  val pounds: Money = amount.pounds

  println(amount.euros)
  println(amount.dollars)
  println(amount.pounds)
}
