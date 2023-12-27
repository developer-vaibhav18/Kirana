package com.app.kirana.dto;

import com.app.kirana.enums.Currency;
import com.app.kirana.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    private String id;
    @NotBlank(message = "payer cannot be null or blank")
    private String payer;
    private long transactionTime;
    @DecimalMin(value = "0.0", message = "amount should be greater than 0")
    private double amount;
    private Currency currency;
    private String reason;
    private TransactionType transactionType;
}
