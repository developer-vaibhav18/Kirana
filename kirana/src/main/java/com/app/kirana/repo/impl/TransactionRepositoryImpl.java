package com.app.kirana.repo.impl;

import com.app.kirana.beans.Transaction;
import com.app.kirana.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void save(Transaction transaction) {
        mongoTemplate.save(transaction);
    }

    @Override
    public List<Transaction> fetchAllTransactionsBetweenStartAndEndTime(long startTime, long endTime) {
        Criteria criteria = Criteria.where(Transaction.TRANSACTION_TIME).gte(startTime).lte(endTime);

        return mongoTemplate.find(new Query(criteria), Transaction.class);
    }
}
