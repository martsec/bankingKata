package cat.martsec.bankingkata.functionalsolution

import java.time.LocalDate
import java.util.concurrent.atomic.AtomicInteger

/**
  * Solution created by:
  * @author Lau
  * @author Artur
  */

object Account {
  val idCounter: AtomicInteger = new AtomicInteger(0)

  case class AccountSnapshot(date: LocalDate, amount: Double, balance: Double)

  def apply(): Account = Account(0, List.empty, idCounter.getAndIncrement())
}

case class Account(balance: Double, snapshots: List[Account.AccountSnapshot], id: Int) {

  /**
    *
    * @return current balance of the account
    */
  //def balance: Double = ;

  def modifyBalance(amount: Double): Account = {
    val newBalance = math.floor((balance + amount) * 100.0) / 100.0
    Account(newBalance, Account.AccountSnapshot(LocalDate.now(), amount, balance) :: snapshots, id)
  }

  def deposit(quantity: Double): Account = if (quantity > 0) modifyBalance(quantity) else this

  /**
    *
    * @param quantity amount of money to withdrawal
    * @return money withdrawed. 0 if none
    */
  def withdrawal(quantity: Double): (Account, Double) = // falten els { per veure-ho clar de moment
    if (balance < quantity || quantity <= 0)
      (this, 0)
    else
      (modifyBalance(-quantity), quantity)


  /**
    * Prints all the history starting with the recent one.
    * An example statement would be:
    * Date        Amount  Balance
    * 23.8.2016    -100      400
    * 24.12.2015   +500      500
    */
  def printStatements(): Unit = {
    println("Date        Amount  Balance")
    snapshots.foreach { x =>
      println(s"${x.date} ${x.amount}  ${x.balance}")
    }
  }

}
