package com.app.kirana.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    public static final String TRANSACTION_TIME = "transactionTime";
    @Id
    private String id;

    private String payer;
    private long transactionTime;
    private double amount;
    private String currency;
    private String reason;
    private String transactionType;
}
