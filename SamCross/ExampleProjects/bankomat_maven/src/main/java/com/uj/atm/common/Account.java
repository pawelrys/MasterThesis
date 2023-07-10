package com.uj.atm.common;

import com.uj.atm.interfaces.IAccount;

/**
 * UWAGA:
 * 1. Nie wolno tworzyć publicznych pól, jedynie można używać metod z interface
 * 2. Nie wolno dopisywać własnych metod
 * 3. Nie wolno modyfikować interface
 * 4. Nie wolno zmieniać wersji javy ani junita.
 * 5. Nie wolno tworzyć nowych (własnych) konstruktorów. Można używać jedynie istniejących (bezparametrowych).
 */
public class Account implements IAccount {

    private double saldo;

    public Account() {
        saldo = 0.0;
    }

    @Override
    public double AccountStatus() {
        return saldo;
    }

    @Override
    public double DepositFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Wartość depozytu powinna być większa od zera!");
        }
        saldo += amount;
        return saldo;
    }

    @Override
    public double WithdrawFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Wartość wypłaty powinna być większa od zera!");
        }
        if (saldo - amount < 0) {
            throw new IllegalArgumentException("Nie wystarczająca kwota na koncie!");
        }
        saldo -= amount;
        return saldo;
    }
}
