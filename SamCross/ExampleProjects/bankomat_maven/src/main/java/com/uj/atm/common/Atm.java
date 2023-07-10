package com.uj.atm.common;

import com.uj.atm.interfaces.IAccount;
import com.uj.atm.interfaces.IAtm;
import com.uj.atm.interfaces.ICreditCard;

/**
 * UWAGA:
 * 1. Nie wolno tworzyć publicznych pól, jedynie można używać metod z interface
 * 2. Nie wolno dopisywać własnych metod
 * 3. Nie wolno modyfikować interface
 * 4. Nie wolno zmieniać wersji javy ani junita.
 * 5. Nie wolno tworzyć nowych (własnych) konstruktorów. Można używać jedynie istniejących (bezparametrowych).
 */
public class Atm implements IAtm {

    public Atm() {

    }

    @Override
    public boolean CheckPinAndLogIn(ICreditCard creditCard, String pin) {
        if (creditCard == null) {
            throw new IllegalArgumentException("Dana karta nie istnieje!");
        }
        return creditCard.IsPinValid(pin);
    }

    @Override
    public double AccountStatus(IAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Dane konto nie istnieje!");
        }
        return account.AccountStatus();
    }

    @Override
    public boolean ChangePinCard(ICreditCard card, String oldPin, String newPin, String newPinConfirm) {
        if (card == null) {
            throw new IllegalArgumentException("Dana karta nie istnieje!");
        }
        return card.ChangePin(oldPin, newPin, newPinConfirm);
    }

    @Override
    public boolean DepositFunds(ICreditCard card, double amount) {
        if (card == null) {
            throw new IllegalArgumentException("Dana karta nie istnieje!");
        }
        return card.DepositFunds(amount);
    }

    @Override
    public boolean WithdrawFunds(ICreditCard card, double amount) {
        if (card == null) {
            throw new IllegalArgumentException("Dana karta nie istnieje!");
        }
        return card.WithdrawFunds(amount);
    }

    @Override
    public boolean Transfer(ICreditCard card, IAccount accountRecipient, double amount) {
        if (card == null) {
            throw new IllegalArgumentException("Dana karta nie istnieje!");
        }
        if (accountRecipient == null) {
            throw new IllegalArgumentException("Dane konto nie istnieje!");
        }
        if (card.GetAccount().AccountStatus() >= amount && card.WithdrawFunds(amount)) {
            accountRecipient.DepositFunds(amount);
            return true;
        }
        return false;
    }
}
