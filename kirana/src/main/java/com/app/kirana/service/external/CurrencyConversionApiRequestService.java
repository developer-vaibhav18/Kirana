package com.app.kirana.service.external;

import com.app.kirana.dto.external.CurrencyConversionApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CurrencyConversionApiRequestService {
    private final String currencyConversionApiUri = "https://api.fxratesapi.com/latest";
    private final RestTemplate restTemplate;
    public CurrencyConversionApiResponseDto getCurrencyConversionData() {
        try {
            return restTemplate.getForObject(currencyConversionApiUri, CurrencyConversionApiResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
