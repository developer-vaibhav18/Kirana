package com.app.kirana.transformer;

import com.app.kirana.beans.Transaction;
import com.app.kirana.dto.TransactionDto;
import com.app.kirana.enums.Currency;
import com.app.kirana.enums.TransactionType;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class TransactionToTransactionDtoTransformer {
    public TransactionDto transform(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setPayer(transaction.getPayer());
        transactionDto.setTransactionTime(transaction.getTransactionTime());
        transactionDto.setAmount(transaction.getAmount());
        if (nonNull(transaction.getCurrency())) {
            transactionDto.setCurrency(Currency.fromValue(transaction.getCurrency()));
        }
        if (nonNull(transaction.getTransactionType())) {
            transactionDto.setTransactionType(TransactionType.fromValue(transaction.getTransactionType()));
        }
        transactionDto.setReason(transaction.getReason());
        return transactionDto;
    }
}
