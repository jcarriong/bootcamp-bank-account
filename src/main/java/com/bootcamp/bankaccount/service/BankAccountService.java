package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.model.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    Flux<BankAccount> findAll();

    Mono<BankAccount> findById(String id);

    Mono<BankAccount> save(BankAccount bankAccount);
}
