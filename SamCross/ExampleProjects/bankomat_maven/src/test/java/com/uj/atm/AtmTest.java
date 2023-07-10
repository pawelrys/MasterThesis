package com.uj.atm;

import com.uj.atm.common.Account;
import com.uj.atm.common.Atm;
import com.uj.atm.common.CreditCard;
import com.uj.atm.interfaces.IAccount;
import com.uj.atm.interfaces.IAtm;
import com.uj.atm.interfaces.ICreditCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtmTest {

    private static final double DELTA = 0.0000000001;

    @Test
    public void checkPinAndLogInMethodCorrectParameterTest() {
      // given
      IAtm atm = new Atm();
      ICreditCard creditCard = new CreditCard();
      creditCard.Init("1234", "1234");

      // when
      boolean result = atm.CheckPinAndLogIn(creditCard, "1234");

      // then
      assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPinAndLogInMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();

        // when
        atm.CheckPinAndLogIn(null, "1234");
    }

    @Test
    public void checkPinAndLogInMethodIncorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.CheckPinAndLogIn(creditCard, "12345");

        // then
        assertFalse(result);
    }

    @Test
    public void checkPinAndLogInMethodIncorrectParameterTest3() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.CheckPinAndLogIn(creditCard, "123a");

        // then
        assertFalse(result);
    }

    @Test
    public void accountStatusMethodCorrectParameterTest() {
        // given
        IAtm atm = new Atm();
        IAccount account = new Account();
        account.DepositFunds(50.0);

        // when
        double result = atm.AccountStatus(account);

        // then
        assertEquals(result, 50.0, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void accountStatusMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();

        // when
        atm.AccountStatus(null);
    }

    @Test
    public void changePinCardMethodCorrectParameterTest() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.ChangePinCard(creditCard, "1234", "5678", "5678");

        // then
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePinCardMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();

        // when
        atm.ChangePinCard(null, "1234", "5678", "5678");
    }

    @Test
    public void changePinCardMethodIncorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.ChangePinCard(creditCard, "12345", "5678", "5678");

        // then
        assertFalse(result);
    }

    @Test
    public void changePinCardMethodIncorrectParameterTest3() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.ChangePinCard(creditCard, "1234", "5678", "5679");

        // then
        assertFalse(result);
    }

    @Test
    public void changePinCardMethodIncorrectParameterTest4() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = atm.ChangePinCard(creditCard, "1234", "567a", "567a");

        // then
        assertFalse(result);
    }

    @Test
    public void depositFundsMethodCorrectParameterTest() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = 50.0;

        // when
        boolean result = atm.DepositFunds(creditCard, amount);

        // then
        assertTrue(result);
    }

    @Test
    public void depositFundsMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        double amount = 50.0;

        // when
        boolean result = atm.DepositFunds(creditCard, amount);

        // then
        assertFalse(result);
    }

    @Test
    public void depositFundsMethodIncorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        boolean result = atm.DepositFunds(creditCard, amount);

        // then
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositFundsMethodIncorrectParameterTest3() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        atm.DepositFunds(null, amount);
    }

    @Test
    public void withdrawFundsMethodCorrectParameterTest() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = 50.0;
        creditCard.DepositFunds(amount);

        // when
        boolean result = atm.WithdrawFunds(creditCard, amount);

        // then
        assertTrue(result);
    }

    @Test
    public void withdrawFundsMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        double amount = 50.0;

        // when
        boolean result = atm.WithdrawFunds(creditCard, amount);

        // then
        assertFalse(result);
    }

    @Test
    public void withdrawFundsMethodIncorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        boolean result = atm.WithdrawFunds(creditCard, amount);

        // then
        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFundsMethodIncorrectParameterTest3() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        atm.WithdrawFunds(null, amount);
    }

    @Test
    public void transferMethodCorrectParameterTest1() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        double transferValue = 50.0;

        // when
        boolean result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // then
        assertTrue(result);
        assertEquals(transferValue, accountRecipient.AccountStatus(), DELTA);
        assertEquals(amount - transferValue, account1.AccountStatus(), DELTA);
    }

    @Test
    public void transferMethodCorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        double transferValue = 500.0;

        // when
        boolean result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // then
        assertTrue(result);
        assertEquals(transferValue, accountRecipient.AccountStatus(), DELTA);
        assertEquals(amount - transferValue, account1.AccountStatus(), DELTA);
    }

    @Test
    public void transferMethodIncorrectParameterTest1() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        double transferValue = 500.1;

        // when
        boolean result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // then
        assertFalse(result);
        assertEquals(0, accountRecipient.AccountStatus(), DELTA);
        assertEquals(account1.AccountStatus(), amount, DELTA);
    }

    @Test
    public void transferMethodIncorrectParameterTest2() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        double transferValue = -50.0;

        // when
        boolean result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // then
        assertFalse(result);
        assertEquals(0, accountRecipient.AccountStatus(), DELTA);
        assertEquals(account1.AccountStatus(), amount, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferMethodIncorrectParameterTest3() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        double transferValue = 500.1;

        // when
        atm.Transfer(creditCard, null, transferValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferMethodIncorrectParameterTest4() {
        // given
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        double transferValue = 500.1;

        // when
        atm.Transfer(null, accountRecipient, transferValue);
    }

}