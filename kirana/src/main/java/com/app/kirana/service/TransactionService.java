package com.app.kirana.service;

import com.app.kirana.dto.TransactionFetchRequestDto;
import com.app.kirana.dto.TransactionFetchResponseDto;
import com.app.kirana.dto.TransactionMetaDataDto;
import com.app.kirana.enums.Currency;
import com.app.kirana.service.helper.CurrencyConversionHelperService;
import com.app.kirana.transformer.TransactionDtoToTransactionTransformer;
import com.app.kirana.beans.Transaction;
import com.app.kirana.dto.TransactionDto;
import com.app.kirana.repo.TransactionRepository;
import com.app.kirana.transformer.TransactionToTransactionDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDtoToTransactionTransformer transactionDtoToTransactionTransformer;
    private final CurrencyConversionHelperService currencyConversionHelperService;
    private final TransactionMetaDataService transactionMetaDataService;
    private final TransactionToTransactionDtoTransformer transactionToTransactionDtoTransformer;

    public void saveTransaction(TransactionDto transactionDto) {
        convertToUSDCurrency(transactionDto);
        Transaction transaction = transactionDtoToTransactionTransformer.transform(transactionDto);
        transactionRepository.save(transaction);
    }

    public TransactionFetchResponseDto fetchTransactions(TransactionFetchRequestDto transactionFetchRequestDto) {
        List<Transaction> transactions = transactionRepository.fetchAllTransactionsBetweenStartAndEndTime(
                transactionFetchRequestDto.getStartTime(), transactionFetchRequestDto.getEndTime());
        convertTransactionsToCurrency(transactions, transactionFetchRequestDto.getCurrency());
        TransactionMetaDataDto transactionMetaDataDto = transactionMetaDataService.getTransactionMetaDataDto(transactions);

        return TransactionFetchResponseDto.builder()
                .transactions(getTransactionDtos(transactions))
                .transactionsMetaData(transactionMetaDataDto)
                .startTime(transactionFetchRequestDto.getStartTime())
                .endTime(transactionFetchRequestDto.getEndTime())
                .currency(transactionFetchRequestDto.getCurrency())
                .build();
    }

    private void convertTransactionsToCurrency(List<Transaction> transactions, Currency currency) {
        double conversionRate = currencyConversionHelperService.fetchConversionRateForCurrency(currency);
        transactions
                .forEach(transaction -> {
                    double amount = transaction.getAmount();
                    transaction.setAmount(amount * conversionRate);
                });
    }

    private List<TransactionDto> getTransactionDtos(List<Transaction> transactions) {
        return transactions
                .stream()
                .map(transactionToTransactionDtoTransformer::transform)
                .collect(Collectors.toList());
    }

    private void convertToUSDCurrency(TransactionDto transactionDto) {
        double value = transactionDto.getAmount();
        Currency currency = transactionDto.getCurrency();
        if (currency == Currency.USD) {
            return;
        }
        double conversionRate = currencyConversionHelperService.fetchConversionRateForCurrency(currency);
        transactionDto.setAmount(currencyConversionHelperService.convertToUSD(value, currency, conversionRate));
    }
}
