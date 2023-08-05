using BankomatUJ.interfaces;

namespace BankomatUJ.common;

/**
 * UWAGA:
 * 1. Nie wolno tworzyć publicznych pól, jedynie można używać metod z interface
 * 2. Nie wolno dopisywać własnych metod
 * 3. Nie wolno modyfikować interface
 * 4. Nie wolno zmieniać wersji javy ani junita.
 * 5. Nie wolno tworzyć nowych (własnych) konstruktorów. Można używać jedynie istniejących (bezparametrowych).
 */
public class Atm : IAtm
{
    public Atm()
    {
    }


    public bool CheckPinAndLogIn(ICreditCard? creditCard, string pin)
    {
        if (creditCard == null)
        {
            throw new ArgumentException("Dana karta nie istnieje!");
        }

        return creditCard.IsPinValid(pin);
    }


    public double AccountStatus(IAccount? account)
    {
        if (account == null)
        {
            throw new ArgumentException("Dane konto nie istnieje!");
        }

        return account.AccountStatus();
    }


    public bool ChangePinCard(ICreditCard? card, string oldPin, string newPin, string newPinConfirm)
    {
        if (card == null)
        {
            throw new ArgumentException("Dana karta nie istnieje!");
        }

        return card.ChangePin(oldPin, newPin, newPinConfirm);
    }


    public bool DepositFunds(ICreditCard? card, double amount)
    {
        if (card == null)
        {
            throw new ArgumentException("Dana karta nie istnieje!");
        }

        return card.DepositFunds(amount);
    }


    public bool WithdrawFunds(ICreditCard? card, double amount)
    {
        if (card == null)
        {
            throw new ArgumentException("Dana karta nie istnieje!");
        }

        return card.WithdrawFunds(amount);
    }


    public bool Transfer(ICreditCard? card, IAccount? accountRecipient, double amount)
    {
        if (card == null)
        {
            throw new ArgumentException("Dana karta nie istnieje!");
        }

        if (accountRecipient == null)
        {
            throw new ArgumentException("Dane konto nie istnieje!");
        }

        if (card.GetAccount().AccountStatus() >= amount && card.WithdrawFunds(amount))
        {
            accountRecipient.DepositFunds(amount);
            return true;
        }

        return false;
    }
}