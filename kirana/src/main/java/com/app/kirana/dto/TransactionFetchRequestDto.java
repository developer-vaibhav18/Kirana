package com.app.kirana.dto;

import com.app.kirana.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionFetchRequestDto {
    @Min(0)
    public long startTime;
    @Min(0)
    public long endTime;
    public Currency currency;
}
