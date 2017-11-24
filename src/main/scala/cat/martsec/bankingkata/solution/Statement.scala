package cat.martsec.bankingkata.solution

import java.time.LocalDate

case class Statement(date: LocalDate, amount: Double, balance: Double) {
  def toTsv: String = date + "\t" + amount + "\t" + balance
}
