package com.uj.atm;

import com.uj.atm.common.Account;
import com.uj.atm.common.CreditCard;
import com.uj.atm.interfaces.IAccount;
import com.uj.atm.interfaces.ICreditCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardTest {

    @Test
    public void creditCardInitMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.Init("1234", "1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creditCardInitMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.Init("1234", "1235");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creditCardInitMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.Init("12345", "12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creditCardInitMethodIncorrectParameterTest3() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.Init("1a45", "1a45");
    }

    @Test
    public void isPinValidMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.IsPinValid("1234");

        // then
        assertTrue(result);
    }

    @Test
    public void isPinValidMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.IsPinValid("1235");

        // then
        assertFalse(result);
    }

    @Test
    public void isPinValidMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.IsPinValid("12345");

        // then
        assertFalse(result);
    }

    @Test
    public void isPinValidMethodIncorrectParameterTest3() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.IsPinValid("123a");

        // then
        assertFalse(result);
    }

    @Test
    public void changePinMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.ChangePin("1234", "5678", "5678");

        // then
        assertTrue(result);
    }

    @Test
    public void changePinMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.ChangePin("12345", "5678", "5678");

        // then
        assertFalse(result);
    }

    @Test
    public void changePinMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.ChangePin("1234", "56789", "5678");

        // then
        assertFalse(result);
    }

    @Test
    public void changePinMethodIncorrectParameterTest3() {
        // given
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // when
        boolean result = creditCard.ChangePin("1234", "56789", "56789");

        // then
        assertFalse(result);
    }

    @Test
    public void addAccountMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();

        // when
        creditCard.AddAccount(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAccountMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.AddAccount(null);
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void addAccountMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        IAccount account2 = new Account();
        creditCard.AddAccount(account1);

        // when
        creditCard.AddAccount(account2);
    }

    @Test
    public void getAccountMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);

        // when
        IAccount result = creditCard.GetAccount();

        // then
        assertNotNull(result);
        assertEquals(result, account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAccountMethodIncorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();

        // when
        creditCard.GetAccount();
    }

    @Test
    public void depositFundsMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = 50.0;

        // when
        boolean result = creditCard.DepositFunds(amount);

        // then
        assertTrue(result);
    }

    @Test
    public void depositFundsMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();
        double amount = 50.0;

        // when
        boolean result = creditCard.DepositFunds(amount);

        // then
        assertFalse(result);
    }

    @Test
    public void depositFundsMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        boolean result = creditCard.DepositFunds(amount);

        // then
        assertFalse(result);
    }

    @Test
    public void withdrawFundsMethodCorrectParameterTest() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = 50.0;
        creditCard.DepositFunds(amount);

        // when
        boolean result = creditCard.WithdrawFunds(amount);

        // then
        assertTrue(result);
    }

    @Test
    public void withdrawFundsMethodIncorrectParameterTest1() {
        // given
        ICreditCard creditCard = new CreditCard();
        double amount = 50.0;

        // when
        boolean result = creditCard.WithdrawFunds(amount);

        // then
        assertFalse(result);
    }

    @Test
    public void withdrawFundsMethodIncorrectParameterTest2() {
        // given
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        double amount = -10.0;

        // when
        boolean result = creditCard.WithdrawFunds(amount);

        // then
        assertFalse(result);
    }
}