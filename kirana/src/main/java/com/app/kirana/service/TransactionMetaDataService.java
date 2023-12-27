package com.app.kirana.service;

import com.app.kirana.beans.Transaction;
import com.app.kirana.dto.TransactionMetaDataDto;
import com.app.kirana.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionMetaDataService {
    public TransactionMetaDataDto getTransactionMetaDataDto(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return null;
        }
        double totalAmountDebited = 0.0;
        double totalAmountCredited = 0.0;
        double maxAmountCredited = Double.MIN_VALUE;
        double minAmountCredited = Double.MAX_VALUE;
        double maxAmountDebited = Double.MIN_VALUE;
        double minAmountDebited = Double.MAX_VALUE;
        double netProfit = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                double amount = transaction.getAmount();
                if (TransactionType.DEBIT.name().equals(transaction.getTransactionType())) {
                    totalAmountDebited += amount;
                    maxAmountDebited = Math.max(maxAmountDebited, amount);
                    minAmountDebited = Math.min(minAmountDebited, amount);
                    netProfit -= amount;
                } else {
                    totalAmountCredited += amount;
                    maxAmountCredited = Math.max(maxAmountCredited, amount);
                    minAmountCredited = Math.min(minAmountCredited, amount);
                    netProfit += amount;
                }
            }
        }

        if (maxAmountCredited == Double.MIN_VALUE) {
            maxAmountCredited = 0.0;
        }
        if (minAmountCredited == Double.MAX_VALUE) {
            minAmountCredited = 0.0;
        }

        if (maxAmountDebited == Double.MIN_VALUE) {
            maxAmountDebited = 0.0;
        }
        if (minAmountDebited == Double.MAX_VALUE) {
            minAmountDebited = 0.0;
        }

        return TransactionMetaDataDto.builder()
                .maxAmountCredited(maxAmountCredited)
                .minAmountCredited(minAmountCredited)
                .maxAmountDebited(maxAmountDebited)
                .minAmountDebited(minAmountDebited)
                .totalAmountDebited(totalAmountDebited)
                .totalAmountCredited(totalAmountCredited)
                .netProfit(netProfit)
                .build();
    }
}
