package com.bootcamp.bankaccount.controller;

import com.bootcamp.bankaccount.model.BankAccount;
import com.bootcamp.bankaccount.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/findAll")
    public Flux<BankAccount> findAll() {
        log.info("All bank accounts were consulted");
        return bankAccountService.findAll()
                .doOnNext(bankAccount -> bankAccount.toString());
    }

    @GetMapping("/findById/{id}")
    public Mono<BankAccount> findById(@PathVariable("id") String id) {
        log.info("Bank account consulted by id " + id);
        return bankAccountService.findById(id);

    }

    @PostMapping("/save")
    public Mono<ResponseEntity<BankAccount>> save(@RequestBody BankAccount bankAccount) {
        log.info("A bank account was created");
        bankAccount.setCreationDatetime(LocalDateTime.now());
        return bankAccountService.save(bankAccount)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}
