package com.app.kirana.dto;

import com.app.kirana.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionFetchResponseDto {
    private long startTime;
    private long endTime;
    private Currency currency;
    private List<TransactionDto> transactions;
    private TransactionMetaDataDto transactionsMetaData;
}
