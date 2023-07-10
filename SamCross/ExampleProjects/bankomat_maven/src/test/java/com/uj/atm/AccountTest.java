package com.uj.atm;

import com.uj.atm.common.Account;
import com.uj.atm.interfaces.IAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private static final double DELTA = 0.0000000001;

    @Test
    public void initAccountTest() {
        // given
        IAccount account = new Account();

        // given
        double result = account.AccountStatus();

        // then
        assertEquals(0, result, DELTA);
    }

    @Test
    public void depositFundsMethodCorrectParameterTest1() {
        // given
        IAccount account = new Account();

        // when
        double result = account.DepositFunds(5.0);

        // then
        assertEquals(result, 5.0, DELTA);
    }

    @Test
    public void depositFundsMethodCorrectParameterTest2() {
        // given
        IAccount account = new Account();

        // when
        double result = account.DepositFunds(9999.99);

        // then
        assertEquals(result, 9999.99, DELTA);
    }

    @Test
    public void depositFundsMethodCorrectParameterTest3() {
        // given
        IAccount account = new Account();

        // when
        double result = account.DepositFunds(0.0001);

        // then
        assertEquals(result, 0.0001, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositFundsMethodIncorrectParameterTest1() {
        // given
        IAccount account = new Account();

        // when
        account.DepositFunds(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositFundsMethodIncorrectParameterTest2() {
        // given
        IAccount account = new Account();

        // when
        account.DepositFunds(0.000000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositFundsMethodIncorrectParameterTest3() {
        // given
        IAccount account = new Account();

        // when
        account.DepositFunds(-5.555);
    }

    @Test
    public void accountStatusMethodAfterDepositFundsTest1() {
        // given
        IAccount account = new Account();
        account.DepositFunds(5.0);

        // when
        double result = account.AccountStatus();

        // then
        assertEquals(result, 5.0, DELTA);
    }

    @Test
    public void accountStatusMethodAfterDepositFundsTest2() {
        // given
        IAccount account = new Account();
        account.DepositFunds(0.00001);

        // when
        double result = account.AccountStatus();

        // then
        assertEquals(result, 0.00001, DELTA);
    }

    @Test
    public void accountStatusMethodAfterDepositFundsTest3() {
        // given
        IAccount account = new Account();
        account.DepositFunds(9.99999);

        // when
        double result = account.AccountStatus();

        // then
        assertEquals(result, 9.99999, DELTA);
    }

    @Test
    public void withdrawFundsMethodCorrectParameterTest1() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        double result = account.WithdrawFunds(50.0);

        // then
        assertEquals(result, 450.0, DELTA);
    }

    @Test
    public void withdrawFundsMethodCorrectParameterTest2() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        double result = account.WithdrawFunds(500.0);

        // then
        assertEquals(result, 0.0, DELTA);
    }

    @Test
    public void withdrawFundsMethodCorrectParameterTest3() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        double result = account.WithdrawFunds(499.9);

        // then
        assertEquals(result, 0.1, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFundsMethodIncorrectParameterTest1() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(500.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFundsMethodIncorrectParameterTest2() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(999999.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFundsMethodIncorrectParameterTest3() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawFundsMethodIncorrectParameterTest4() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(-5.0);
    }

    @Test
    public void accountStatusMethodAfterWithdrawFundsTest1() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(50.0);

        // then
        assertEquals(450.0, account.AccountStatus(), DELTA);
    }

    @Test
    public void accountStatusMethodAfterWithdrawFundsTest2() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(499.9);

        // then
        assertEquals(0.1, account.AccountStatus(), DELTA);
    }

    @Test
    public void accountStatusMethodAfterWithdrawFundsTest3() {
        // given
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // when
        account.WithdrawFunds(500.0);

        // then
        assertEquals(0.0, account.AccountStatus(), DELTA);
    }
}