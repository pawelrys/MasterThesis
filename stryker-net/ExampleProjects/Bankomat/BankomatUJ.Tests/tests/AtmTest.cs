using BankomatUJ.common;
using BankomatUJ.interfaces;

namespace BankomatUJ.Tests.tests;

public class AtmTest
{
    private const double Delta = 0.0000000001;

    [Test]
    public void CheckPinAndLogInMethodCorrectParameterTest()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.CheckPinAndLogIn(creditCard, "1234");

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void CheckPinAndLogInMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();

        // Act
        Assert.Throws<ArgumentException>(() => atm.CheckPinAndLogIn(null, "1234"));
    }

    [Test]
    public void CheckPinAndLogInMethodIncorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.CheckPinAndLogIn(creditCard, "12345");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void CheckPinAndLogInMethodIncorrectParameterTest3()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.CheckPinAndLogIn(creditCard, "123a");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void AccountStatusMethodCorrectParameterTest()
    {
        // Arrange
        IAtm atm = new Atm();
        IAccount account = new Account();
        account.DepositFunds(50.0);

        // Act
        var result = atm.AccountStatus(account);

        // Assert
        Assert.That(50.0, Is.EqualTo(result).Within(Delta));
    }

    [Test]
    public void AccountStatusMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();

        // Act
        Assert.Throws<ArgumentException>(() => atm.AccountStatus(null));
    }

    [Test]
    public void ChangePinCardMethodCorrectParameterTest()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.ChangePinCard(creditCard, "1234", "5678", "5678");

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void ChangePinCardMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();

        // Act
        Assert.Throws<ArgumentException>(() => atm.ChangePinCard(null, "1234", "5678", "5678"));
    }

    [Test]
    public void ChangePinCardMethodIncorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.ChangePinCard(creditCard, "12345", "5678", "5678");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void ChangePinCardMethodIncorrectParameterTest3()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.ChangePinCard(creditCard, "1234", "5678", "5679");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void ChangePinCardMethodIncorrectParameterTest4()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        creditCard.Init("1234", "1234");

        // Act
        var result = atm.ChangePinCard(creditCard, "1234", "567a", "567a");

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void DepositFundsMethodCorrectParameterTest()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = 50.0;

        // Act
        var result = atm.DepositFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        const double amount = 50.0;

        // Act
        var result = atm.DepositFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        var result = atm.DepositFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void DepositFundsMethodIncorrectParameterTest3()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        Assert.Throws<ArgumentException>(() => atm.DepositFunds(null, amount));
    }

    [Test]
    public void WithdrawFundsMethodCorrectParameterTest()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = 50.0;
        creditCard.DepositFunds(amount);

        // Act
        var result = atm.WithdrawFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.True);
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        const double amount = 50.0;

        // Act
        var result = atm.WithdrawFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        var result = atm.WithdrawFunds(creditCard, amount);

        // Assert
        Assert.That(result, Is.False);
    }

    [Test]
    public void WithdrawFundsMethodIncorrectParameterTest3()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account = new Account();
        creditCard.AddAccount(account);
        const double amount = -10.0;

        // Act
        Assert.Throws<ArgumentException>(() => atm.WithdrawFunds(null, amount));
    }

    [Test]
    public void TransferMethodCorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        const double transferValue = 50.0;

        // Act
        var result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // Assert
        Assert.That(result, Is.True);
        Assert.Multiple(() =>
        {
            Assert.That(accountRecipient.AccountStatus(), Is.EqualTo(transferValue).Within(Delta));
            Assert.That(account1.AccountStatus(), Is.EqualTo(amount - transferValue).Within(Delta));
        });
    }

    [Test]
    public void TransferMethodCorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        const double transferValue = 500.0;

        // Act
        var result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // Assert
        Assert.That(result, Is.True);
        Assert.Multiple(() =>
        {
            Assert.That(accountRecipient.AccountStatus(), Is.EqualTo(transferValue).Within(Delta));
            Assert.That(account1.AccountStatus(), Is.EqualTo(amount - transferValue).Within(Delta));
        });
    }

    [Test]
    public void TransferMethodIncorrectParameterTest1()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        const double transferValue = 500.1;

        // Act
        var result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // Assert
        Assert.That(result, Is.False);
        Assert.Multiple(() =>
        {
            Assert.That(accountRecipient.AccountStatus(), Is.EqualTo(0).Within(Delta));
            Assert.That(amount, Is.EqualTo(account1.AccountStatus()).Within(Delta));
        });
    }

    [Test]
    public void TransferMethodIncorrectParameterTest2()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        const double transferValue = -50.0;

        // Act
        var result = atm.Transfer(creditCard, accountRecipient, transferValue);

        // Assert
        Assert.That(result, Is.False);
        Assert.Multiple(() =>
        {
            Assert.That(accountRecipient.AccountStatus(), Is.EqualTo(0).Within(Delta));
            Assert.That(amount, Is.EqualTo(account1.AccountStatus()).Within(Delta));
        });
    }

    [Test]
    public void TransferMethodIncorrectParameterTest3()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        const double transferValue = 500.1;

        // Act
        Assert.Throws<ArgumentException>(() => atm.Transfer(creditCard, null, transferValue));
    }

    [Test]
    public void TransferMethodIncorrectParameterTest4()
    {
        // Arrange
        IAtm atm = new Atm();
        ICreditCard creditCard = new CreditCard();
        IAccount account1 = new Account();
        creditCard.AddAccount(account1);
        const double amount = 500.0;
        creditCard.DepositFunds(amount);
        IAccount accountRecipient = new Account();
        const double transferValue = 500.1;

        // Act
        Assert.Throws<ArgumentException>(() => atm.Transfer(null, accountRecipient, transferValue));
    }
}