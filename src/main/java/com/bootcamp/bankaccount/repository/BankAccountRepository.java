package com.bootcamp.bankaccount.repository;

import com.bootcamp.bankaccount.model.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {
}
