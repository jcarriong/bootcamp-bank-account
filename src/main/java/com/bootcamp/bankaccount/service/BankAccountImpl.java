package com.bootcamp.bankaccount.service;

import com.bootcamp.bankaccount.model.BankAccount;
import com.bootcamp.bankaccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BankAccountImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Flux<BankAccount> findByIdCustomer(String idCustomer) {
        return bankAccountRepository.findByIdCustomer(idCustomer);
    }

    @Override
    public Mono<BankAccount> findById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Mono<BankAccount> save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Mono<BankAccount> updateAccount(BankAccount bankAccount, String idAccount) {

        return bankAccountRepository.findById(idAccount)
                .flatMap(currentBankAccount -> {
                    currentBankAccount.setAvailableBalance(bankAccount.getAvailableBalance());
                    currentBankAccount.setHolderAccount(bankAccount.getHolderAccount());
                    currentBankAccount.setAuthorizedSigner(bankAccount.getAuthorizedSigner());
                    currentBankAccount.setUpdateDatetime(LocalDateTime.now());
                    return bankAccountRepository.save(currentBankAccount);
                });

    }

    @Override
    public Mono<BankAccount> deleteAccountById(String idAccount) {
        return bankAccountRepository.findById(idAccount)
                .flatMap(existingAccount -> bankAccountRepository.delete(existingAccount)
                        .then(Mono.just(existingAccount)));
    }
}
