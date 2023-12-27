package com.app.kirana.utils;

import com.app.kirana.dto.TransactionDto;
import com.app.kirana.enums.Currency;
import com.app.kirana.enums.TransactionType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    public static final String ID = "id";
    public static final String PAYER = "Vaibhav";
    public static final double AMOUNT = 50.0;
    public static final String REASON = "Reason";
    public static final long TRANSACTION_TIME = 84358943;
    public static final double CONVERSION_RATE = 30.5;

    public static TransactionDto getTransactionDto(Currency currency, TransactionType transactionType, long transactionTime) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(ID);
        transactionDto.setPayer(PAYER);
        transactionDto.setTransactionTime(transactionTime);
        transactionDto.setAmount(AMOUNT);
        transactionDto.setCurrency(currency);
        transactionDto.setTransactionType(transactionType);
        transactionDto.setReason(REASON);
        return transactionDto;
    }

}
