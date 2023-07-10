package com.uj.atm.common;

import com.uj.atm.interfaces.IAccount;
import com.uj.atm.interfaces.ICreditCard;


/**
 * UWAGA:
 * 1. Nie wolno tworzyć publicznych pól, jedynie można używać metod z interface
 * 2. Nie wolno dopisywać własnych metod
 * 3. Nie wolno modyfikować interface
 * 4. Nie wolno zmieniać wersji javy ani junita.
 * 5. Nie wolno tworzyć nowych (własnych) konstruktorów. Można używać jedynie istniejących (bezparametrowych).
 */
public class CreditCard implements ICreditCard {

    private String pin;
    private IAccount account;

    public CreditCard() {
        pin = "0000";
        account = null;
    }

    @Override
    public void Init(String newPin, String newPinConfirm) {
        if (!newPin.equals(newPinConfirm)) {
            throw new IllegalArgumentException("Podałeś dwa różne piny! Pin i pin weryfikujący muszą być takie same!");
        }
        if (newPin.length() != 4) {
            throw new IllegalArgumentException("Pin musi składać się z 4 cyfr!");
        }
        for (int i = 0; i < newPin.length(); i++) {
            char c = newPin.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("Pin musi składać się z 4 cyfr!");
            }
        }
        this.pin = newPin;
    }

    @Override
    public boolean ChangePin(String oldPin, String newPin, String newPinConfirm) {
        if (pin.equals(oldPin) && newPin.equals(newPinConfirm) && newPin.length() == 4) {
            for (int i = 0; i < newPin.length(); i++) {
                char c = newPin.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            pin = newPin;
            return true;
        }
        return false;
    }

    @Override
    public boolean IsPinValid(String pin) {
        if (pin.length() != 4) {
            return false;
        }
        for (int i = 0; i < pin.length(); i++) {
            char c = pin.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return pin.equals(this.pin);
    }

    @Override
    public void AddAccount(IAccount account) {
        if (this.account != null) {
            throw new ExceptionInInitializerError("Ta karta jest już dodana do jakiegoś konta!");
        }
        if (account == null) {
            throw new IllegalArgumentException("Dodawane konto nie istnieje!");
        }
        this.account = account;
    }

    @Override
    public IAccount GetAccount(){
        if (account == null) {
            throw new IllegalArgumentException("Ta karta nie jest jeszcze dodana do żadnego konta!");
        }
        return account;
    }

    @Override
    public boolean DepositFunds(double amount) {
        if (account != null && amount > 0) {
            account.DepositFunds(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean WithdrawFunds(double amount) {
        if (account != null && amount > 0) {
            account.WithdrawFunds(amount);
            return true;
        }
        return false;
    }
}
