package com.app.kirana.api;

import com.app.kirana.dto.TransactionDto;
import com.app.kirana.dto.TransactionFetchRequestDto;
import com.app.kirana.dto.TransactionFetchResponseDto;
import com.app.kirana.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/kirana/transactions")
@RequiredArgsConstructor
public class TransactionApi {
    private final TransactionService transactionService;
    @PostMapping("/save")
    public ResponseEntity<String> saveTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>("transaction saved successfully !!", CREATED);
    }

    @PostMapping("/fetch")
    public ResponseEntity<TransactionFetchResponseDto> fetchTransactions(@RequestBody @Valid TransactionFetchRequestDto transactionFetchRequestDto) {
        return new ResponseEntity<>(transactionService.fetchTransactions(transactionFetchRequestDto), OK);
    }
}
