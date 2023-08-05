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
public class CreditCard : ICreditCard
{
    private string _pin;
    private IAccount? _account;

    public CreditCard()
    {
        _pin = "0000";
        _account = null;
    }


    public void Init(string newPin, string newPinConfirm)
    {
        if (!newPin.Equals(newPinConfirm))
        {
            throw new ArgumentException("Podałeś dwa różne piny! Pin i pin weryfikujący muszą być takie same!");
        }

        if (newPin.Length != 4)
        {
            throw new ArgumentException("Pin musi składać się z 4 cyfr!");
        }

        for (var i = 0; i < newPin.Length; i++)
        {
            var c = newPin[i];
            if (c is < '0' or > '9')
            {
                throw new ArgumentException("Pin musi składać się z 4 cyfr!");
            }
        }

        this._pin = newPin;
    }


    public bool ChangePin(string oldPin, string newPin, string newPinConfirm)
    {
        if (_pin.Equals(oldPin) && newPin.Equals(newPinConfirm) && newPin.Length == 4)
        {
            for (var i = 0; i < newPin.Length; i++)
            {
                var c = newPin[i];
                if (c is < '0' or > '9')
                {
                    return false;
                }
            }

            _pin = newPin;
            return true;
        }

        return false;
    }


    public bool IsPinValid(string pin)
    {
        if (pin.Length != 4)
        {
            return false;
        }

        for (var i = 0; i < pin.Length; i++)
        {
            var c = pin[i];
            if (c is < '0' or > '9')
            {
                return false;
            }
        }

        return pin.Equals(this._pin);
    }


    public void AddAccount(IAccount? account)
    {
        if (this._account != null)
        {
            throw new ArgumentException("Ta karta jest już dodana do jakiegoś konta!");
        }

        if (account == null)
        {
            throw new ArgumentException("Dodawane konto nie istnieje!");
        }

        this._account = account;
    }


    public IAccount GetAccount()
    {
        if (_account == null)
        {
            throw new ArgumentException("Ta karta nie jest jeszcze dodana do żadnego konta!");
        }

        return _account;
    }


    public bool DepositFunds(double amount)
    {
        if (_account != null && amount > 0)
        {
            _account.DepositFunds(amount);
            return true;
        }

        return false;
    }


    public bool WithdrawFunds(double amount)
    {
        if (_account != null && amount > 0)
        {
            _account.WithdrawFunds(amount);
            return true;
        }

        return false;
    }
}