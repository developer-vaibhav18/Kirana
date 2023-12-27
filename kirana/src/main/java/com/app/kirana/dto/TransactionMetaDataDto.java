package com.app.kirana.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionMetaDataDto {
    private double totalAmountDebited;
    private double totalAmountCredited;
    private double maxAmountCredited;
    private double minAmountCredited;
    private double maxAmountDebited;
    private double minAmountDebited;
    private double netProfit;
}
