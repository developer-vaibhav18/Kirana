package com.app.kirana.enums;

public enum TransactionType {
    CREDIT,
    DEBIT;

    public static TransactionType fromValue(String value) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.name().equals(value)) {
                return transactionType;
            }
        }
        return null;
    }
}
