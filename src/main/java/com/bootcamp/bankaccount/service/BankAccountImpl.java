package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.model.BankAccount;
import com.bootcamp.bankaccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> findById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Mono<BankAccount> save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
