package cat.martsec.bankingkata

import org.scalatest._

class AccountSpec extends WordSpec with Matchers {
  "cat.martsec.bankingkata.Account" should {
    "not have the same if for 2 instances" in {
      val c1 = new Account
      val c2 = new Account

      c1.id shouldNot be { c2.id }
    }

    "have a balance 0 in the beginning" in {
      val c = new Account

      c.balance shouldBe 0
    }

    "have a balance that changes" in {
      val c = new Account
      val b1 = c.balance
      c deposit 10

      b1 + 10 shouldBe c.balance
    }

    "not allow balance to be changed directly" in {
      "val a = new cat.martsec.bankingkata.solution.Account; a.balance = 10" shouldNot compile
    }

    "not allow a negative deposit" in {
      val c = new Account
      c deposit -10

      c.balance shouldBe 0
    }

    "have a printStatements method" in {
      (new Account).printStatements()
      succeed
    }

    "remove the right ammount of money when withdrawal is called" in {
      val c = new Account
      c.deposit(1000)
      val b1 = c.balance
      c.withdrawal(100)

      c.balance + 100 shouldBe b1
    }

    "return the amount of money removed when withdrawal" in {
      val c = new Account
      c.deposit(1000)
      val amount = c.withdrawal(100)

      amount shouldBe 100
    }

    "return 0 and do nothing if there is not enough money for wihdrawal" in {
      val c = new Account
      c deposit 10

      c.withdrawal(1000) shouldBe 0
      c.balance shouldBe 10
    }

    "return 0 and do nothing if there is a negative withdrawal" in {
      val c = new Account
      val withdrawal = c withdrawal -10

      withdrawal shouldBe 0
      c.balance shouldBe 0
    }

    "limit precision to two decimals" in {
      val c = new Account
      c.deposit(10.1234)

      c.balance shouldBe 10.12
    }
  }
}
