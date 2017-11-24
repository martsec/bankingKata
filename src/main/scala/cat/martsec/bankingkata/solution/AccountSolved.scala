package cat.martsec.bankingkata.solution

import java.time.LocalDate

class AccountSolved {

  /**
    * id should be unique for each new account. (Don't use RNG)
    */
  val id: Int = AccountSolved.newUniqueId()

  private var history: List[Statement] = List(Statement(LocalDate.now,0.0,0.0))
  /**
    *
    * @return current balance of the account
    */
  def balance: Double = history.head.balance

  def deposit(quantity: Double): Unit =
    if (isPostitive(quantity)) addStatement(quantity)

  /**
    *
    * @param quantity amount of money to withdrawal
    * @return money withdrawed. 0 if none
    */
  def withdrawal(quantity: Double): Double =
    if (isPostitive(quantity) && notGreaterThanBalance(quantity)) {
      addStatement(-quantity)
      quantity
    }
    else 0.0

  private def isPostitive(x: Double): Boolean = x >= 0

  private def notGreaterThanBalance(x: Double): Boolean = balance >= x

  private def truncateAtPrecisionTwo(x: Double): Double = (math floor (x*100.0)) / 100.0

  private def addStatement(quantity: Double): Unit = {
    val q = truncateAtPrecisionTwo(quantity)
    history = Statement(LocalDate.now, q, balance + q)::history
  }

  /**
    * Prints all the history starting with the recent one.
    * An example statement would be:
    * Date        Amount  Balance
    * 23.8.2016    -100      400
    * 24.12.2015   +500      500
    */
  def printStatements(): Unit = {
    println("Date\tAmount\tBalance")
    history.foreach(s  => println(s.toTsv))
  }

}

object AccountSolved {
  private var lastId = 0
  private def newUniqueId() = {lastId += 1; lastId}
}


