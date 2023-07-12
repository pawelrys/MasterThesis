using BankomatUJ.common;
using BankomatUJ.interfaces;

namespace BankomatUJ.Tests.tests;

public class CreditCardTest
{
    [Test]
    public void CreditCardInitMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        creditCard.Init("1234", "1234");
    }

    [Test]
    public void CreditCardInitMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.Init("1234", "1235"));
    }

    [Test]
    public void CreditCardInitMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.Init("12345", "12345"));
    }

    [Test]
    public void CreditCardInitMethodIncorrectParameterTest3()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.Init("1a45", "1a45"));
    }

    [Test]
    public void IsPinValidMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.IsPinValid("1234");

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void IsPinValidMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.IsPinValid("1235");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void IsPinValidMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.IsPinValid("12345");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void IsPinValidMethodIncorrectParameterTest3()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.IsPinValid("123a");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void ChangePinMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.ChangePin("1234", "5678", "5678");

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void ChangePinMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.ChangePin("12345", "5678", "5678");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void ChangePinMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.ChangePin("1234", "56789", "5678");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void ChangePinMethodIncorrectParameterTest3()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = creditCard.ChangePin("1234", "56789", "56789");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void AddAccountMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();

        // Act
        creditCard.AddAccount(account);
    }

    [Test]
    public void AddAccountMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.AddAccount(null));
    }

    [Test]
    public void AddAccountMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        IAccount account2 = new Account();
        creditCard.AddAccount(account1);

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.AddAccount(account2));
    }

    [Test]
    public void GetAccountMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);

        // Act
        var result = creditCard.GetAccount();
        Assert.Multiple(() =>
        {
            // Assert
            Assert.That(result, Is.Not.Null);
            Assert.That(account, Is.EqualTo(result));
        });
    }

    [Test]
    public void GetAccountMethodIncorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();

        // Act
        Assert.Throws<ArgumentException>(() => creditCard.GetAccount());
    }

    [Test]
    public void DepositFundsMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = 50.0;

        // Act
        var result = creditCard.DepositFunds(amount);

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        const double amount = 50.0;

        // Act
        var result = creditCard.DepositFunds(amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        var result = creditCard.DepositFunds(amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void WithdrawFundsMethodCorrectParameterTest()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = 50.0;
        creditCard.DepositFunds(amount);

        // Act
        var result = creditCard.WithdrawFunds(amount);

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        const double amount = 50.0;

        // Act
        var result = creditCard.WithdrawFunds(amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        var result = creditCard.WithdrawFunds(amount);

        // Assert
        Assert.That(result, Is.False);
    }
}