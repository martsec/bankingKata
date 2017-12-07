package cat.martsec.bankingkata.functionalsolution

import org.scalatest._

/**
  * Solution created by:
  * @author Lau
  * @author Artur
  */

class AccountSpec extends WordSpec with Matchers {
  "cat.martsec.bankingkata.functionalsolution.Account" should {
    "not have the same if for 2 instances" in {
      val c1 = Account()
      val c2 = Account()

      c1.id shouldNot be { c2.id }
    }

    "have a balance 0 in the beginning" in {
      val c = Account()

      c.balance shouldBe 0
    }

    "have a balance that changes" in {
      val c = Account()
      val b1 = c.balance
      val c2 = c deposit 10

      b1 + 10 shouldBe c2.balance
    }

    "not allow balance to be changed directly" in {
      "val a = Account(); a.balance = 10" shouldNot compile
    }

    "not allow a negative deposit" in {
      val c = Account()
      val c2 = c deposit -10

      c2.balance shouldBe 0
    }

    "have a printStatements method" in {
      val c = Account()
      val c2 = c.deposit(1000)
      val (c3,amount) = c2.withdrawal(100)
      c3.printStatements()
      succeed
    }

    "remove the right ammount of money when withdrawal is called" in {
      val c = Account()
      val c2 = c.deposit(1000)
      val b1 = c2.balance
      val (c3,_) = c2.withdrawal(100)

      c3.balance + 100 shouldBe b1
    }

    "return the amount of money removed when withdrawal" in {
      val c = Account()
      val c2 = c.deposit(1000)
      val (_,amount) = c2.withdrawal(100)

      amount shouldBe 100
    }

    "return 0 and do nothing if there is not enough money for wihdrawal" in {
      val c = Account()
      val c2 = c deposit 10

      val (c3,withdrawal) = c2.withdrawal(1000)
      withdrawal shouldBe 0
      c3.balance shouldBe 10
    }

    "return 0 and do nothing if there is a negative withdrawal" in {
      val c = Account()
      val (c2,withdrawal) = c withdrawal -10

      withdrawal shouldBe 0
      c2.balance shouldBe 0
    }

    "limit precision to two decimals" in {
      val c = Account()
      val c2 = c.deposit(10.1234)

      c2.balance shouldBe 10.12
    }
  }
}