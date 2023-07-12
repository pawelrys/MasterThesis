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
public class Account : IAccount
{
    private double _saldo;

    public Account()
    {
        _saldo = 0.0;
    }

    public double AccountStatus()
    {
        return _saldo;
    }

    public double DepositFunds(double amount)
    {
        if (amount <= 0)
        {
            throw new ArgumentException("Wartość depozytu powinna być większa od zera!");
        }

        _saldo += amount;
        return _saldo;
    }

    public double WithdrawFunds(double amount)
    {
        if (amount <= 0)
        {
            throw new ArgumentException("Wartość wypłaty powinna być większa od zera!");
        }

        if (_saldo - amount < 0)
        {
            throw new ArgumentException("Nie wystarczająca kwota na koncie!");
        }

        _saldo -= amount;
        return _saldo;
    }
}