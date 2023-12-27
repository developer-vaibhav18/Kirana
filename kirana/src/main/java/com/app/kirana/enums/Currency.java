package com.app.kirana.enums;

public enum Currency {
    USD,
    INR;

    public static Currency fromValue(String value) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(value)) {
                return currency;
            }
        }
        return null;
    }
}