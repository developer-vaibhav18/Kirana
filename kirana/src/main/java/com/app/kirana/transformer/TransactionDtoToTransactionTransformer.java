package com.app.kirana.transformer;

import com.app.kirana.beans.Transaction;
import com.app.kirana.dto.TransactionDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class TransactionDtoToTransactionTransformer {
    public Transaction transform(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setPayer(transactionDto.getPayer());
        transaction.setAmount(transactionDto.getAmount());
        if (nonNull(transactionDto.getCurrency())) {
            transaction.setCurrency(transactionDto.getCurrency().name());
        }
        if (nonNull(transactionDto.getTransactionType())) {
            transaction.setTransactionType(transactionDto.getTransactionType().name());
        }
        transaction.setReason(transactionDto.getReason());

        setCommonFields(transaction);
        return transaction;
    }

    private void setCommonFields(Transaction transaction) {
        transaction.setTransactionTime(System.currentTimeMillis());
    }
}
