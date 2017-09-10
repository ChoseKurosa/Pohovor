package com.company;

/**
 * Class representing object which contains currency and amount(payment).
 */
public class Payment {

    private String currency;
    private float amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
