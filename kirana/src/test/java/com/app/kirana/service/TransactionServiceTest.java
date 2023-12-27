package com.app.kirana.service;

import com.app.kirana.beans.Transaction;
import com.app.kirana.dto.TransactionDto;
import com.app.kirana.enums.Currency;
import com.app.kirana.enums.TransactionType;
import com.app.kirana.repo.TransactionRepository;
import com.app.kirana.service.helper.CurrencyConversionHelperService;
import com.app.kirana.transformer.TransactionDtoToTransactionTransformer;
import com.app.kirana.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionDtoToTransactionTransformer transactionDtoToTransactionTransformer;
    @InjectMocks
    private CurrencyConversionHelperService currencyConversionHelperService;

    @Test
    public void testSaveTransaction() {
        TransactionDto transactionDto = TestUtils.getTransactionDto(Currency.USD, TransactionType.CREDIT, TestUtils.TRANSACTION_TIME);
        Mockito.when(transactionDtoToTransactionTransformer.transform(any())).thenReturn(new Transaction());

        transactionService.saveTransaction(transactionDto);

        Mockito.verify(currencyConversionHelperService.fetchConversionRateForCurrency(any()), Mockito.times(0));
        Mockito.verify(transactionRepository, Mockito.times(1)).save(any());
    }
}