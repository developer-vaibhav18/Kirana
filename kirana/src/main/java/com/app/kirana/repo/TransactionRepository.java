package com.app.kirana.repo;

import com.app.kirana.beans.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);

    List<Transaction> fetchAllTransactionsBetweenStartAndEndTime(long startTime, long endTime);
}
