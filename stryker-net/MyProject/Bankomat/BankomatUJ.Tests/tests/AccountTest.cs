using BankomatUJ.common;
using BankomatUJ.interfaces;

namespace BankomatUJ.Tests.tests;

public class AccountTest
{
    private const double Delta = 0.0000000001;

    [Test]
    public void InitAccountTest()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        var result = account.AccountStatus();

        // Assert
        Assert.That(result, Is.EqualTo(0).Within(Delta));
    }

    [Test]
    public void DepositFundsMethodCorrectParameterTest1()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        var result = account.DepositFunds(5.0);

        // Assert
        Assert.That(5.0, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void DepositFundsMethodCorrectParameterTest2()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        var result = account.DepositFunds(9999.99);

        // Assert
        Assert.That(9999.99, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void DepositFundsMethodCorrectParameterTest3()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        var result = account.DepositFunds(0.0001);

        // Assert
        Assert.That(0.0001, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        Assert.Throws<ArgumentException>(() => account.DepositFunds(0));
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        Assert.Throws<ArgumentException>(() => account.DepositFunds(0.000000));
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest3()
    {
        // Arrange
        IAccount account = new Account();

        // Act
        Assert.Throws<ArgumentException>(() => account.DepositFunds(-5.555));
    }

    [Test]
    [TestCase(5, "elo", true)]
    [TestCase(2, "elo", true)]
    [TestCase(7, "elo", true)]
    public void AccountStatusMethodAfterDepositFundsTest1(int n, string a, bool e)
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(5.0);

        // Act
        var result = account.AccountStatus();

        // Assert
        Assert.That(5.0, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void AccountStatusMethodAfterDepositFundsTest2()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(0.00001);

        // Act
        var result = account.AccountStatus();

        // Assert
        Assert.That(0.00001, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void AccountStatusMethodAfterDepositFundsTest3()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(9.99999);

        // Act
        var result = account.AccountStatus();

        // Assert
        Assert.That(9.99999, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void WithdrawFundsMethodCorrectParameterTest1()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        var result = account.WithdrawFunds(50.0);

        // Assert
        Assert.That(450.0, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void WithdrawFundsMethodCorrectParameterTest2()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        var result = account.WithdrawFunds(500.0);

        // Assert
        Assert.That(0.0, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void WithdrawFundsMethodCorrectParameterTest3()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        var result = account.WithdrawFunds(499.9);

        // Assert
        Assert.That(0.1, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        Assert.Throws<ArgumentException>(() => account.WithdrawFunds(500.1));
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        Assert.Throws<ArgumentException>(() => account.WithdrawFunds(999999.0));
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest3()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        Assert.Throws<ArgumentException>(() => account.WithdrawFunds(0.0));
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest4()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        Assert.Throws<ArgumentException>(() => account.WithdrawFunds(-5.0));
    }

    [Test]
    public void AccountStatusMethodAfterWithdrawFundsTest1()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        account.WithdrawFunds(50.0);

        // Assert
        Assert.That(account.AccountStatus(), Is.EqualTo(450.0).Within(Delta));
    }

    [Test]
    public void AccountStatusMethodAfterWithdrawFundsTest2()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        account.WithdrawFunds(499.9);

        // Assert
        Assert.That(account.AccountStatus(), Is.EqualTo(0.1).Within(Delta));
    }

    [Test]
    public void AccountStatusMethodAfterWithdrawFundsTest3()
    {
        // Arrange
        IAccount account = new Account();
        account.DepositFunds(500.0);

        // Act
        account.WithdrawFunds(500.0);

        // Assert
        Assert.That(account.AccountStatus(), Is.EqualTo(0.0).Within(Delta));
    }
}