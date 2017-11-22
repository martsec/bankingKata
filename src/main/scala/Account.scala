class Account {

  /**
    * id should be unique for each new account. (Don't use RNG)
    */
  val id: Int = ???

  /**
    *
    * @return current balance of the account
    */
  var balance: Double = ???

  def deposit(quantity: Double): Unit = ???

  /**
    *
    * @param quantity amount of money to withdrawal
    * @return money withdrawed. 0 if none
    */
  def withdrawal(quantity: Double): Double = ???

  /**
    * Prints all the history starting with the recent one.
    * An example statement would be:
    * Date        Amount  Balance
    * 23.8.2016    -100      400
    * 24.12.2015   +500      500
    */
  def printStatements(): Unit = ???

}
