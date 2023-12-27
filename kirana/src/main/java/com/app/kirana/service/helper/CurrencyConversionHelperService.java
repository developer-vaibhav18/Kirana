package com.app.kirana.service.helper;

import com.app.kirana.dto.external.CurrencyConversionApiResponseDto;
import com.app.kirana.enums.Currency;
import com.app.kirana.service.external.CurrencyConversionApiRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyConversionHelperService {
    private final CurrencyConversionApiRequestService currencyConversionApiRequestService;

    public double convertToUSD(double value, Currency currency, double conversionRate) {
        return value / conversionRate;
    }

    public double convertFromUSD(double value, Currency currency, double conversionRate) {
        return value * conversionRate;
    }

    public double fetchConversionRateForCurrency(Currency currency) {
        CurrencyConversionApiResponseDto currencyConversionApiResponseDto = currencyConversionApiRequestService.getCurrencyConversionData();
        Map<String, Double> rates = currencyConversionApiResponseDto.getRates();
        if (rates == null) {
            throw new RuntimeException("rates map found null in currency API response");
        }
        Double conversionRate = rates.get(currency.name());
        if (conversionRate == null) {
            throw new RuntimeException("conversion Rate not found for currency : " + currency.name());
        }
        return conversionRate;
    }
}
