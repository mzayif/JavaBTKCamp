package com.btkAkademi.rentACar.servises.bankServise.outServis;

public class IsBankManager  {

    public boolean CheckCardValid(String cardNumber, String cardName, String cvv, double price) {
        if (cardName == "Muhammed")
            return true;
        else
            return false;
    }
}
