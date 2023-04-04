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

    /**
     * Consultar todas las cuentas bancarias existentes
     **/
    @GetMapping("/findAll")
    public Flux<BankAccount> findAll() {
        log.info("All bank accounts were consulted");
        return bankAccountService.findAll()
                .doOnNext(bankAccount -> bankAccount.toString());
    }
    /**
     * Consultar cuentas bancarias por idCustomer
     **/
    @GetMapping("/findAccountsByCustomer/{idCustomer}")
    public Flux<BankAccount> findByIdCustomer(@PathVariable("idCustomer") String idCustomer) {
        /*        log.info("All bank accounts were consulted");*/
        return bankAccountService.findByIdCustomer(idCustomer);
    }
    /**
     * Consultar cuenta bancaria por idCustomer
     **/
    @GetMapping("/findById/{id}")
    public Mono<BankAccount> findById(@PathVariable("id") String id) {
        log.info("Bank account consulted by id " + id);
        return bankAccountService.findById(id);

    }
    /**
     * Crear una cuenta bancaria de un producto relacionado
     **/
    @PostMapping("/saveAccount")
    public Mono<ResponseEntity<BankAccount>> save(@RequestBody BankAccount bankAccount) {
        log.info("A bank account was created");
        bankAccount.setCreationDatetime(LocalDateTime.now());
        return bankAccountService.save(bankAccount)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
    /**
     * Editar datos de una cuenta bancaria(se restringe la edición de llaves compuestas)
     **/
    @PutMapping("/updateAccountById/{idAccount}")
    public Mono<ResponseEntity<BankAccount>> update(@RequestBody BankAccount bankAccount,
                                                    @PathVariable("idAccount") String idAccount) {
        log.info("A bank account was changed");
        return bankAccountService.updateAccount(bankAccount, idAccount)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    /**
     * Eliminar una cuenta bancaria del registro
     **/
    @DeleteMapping("/deleteCustomerById/{idAccount}")
    public Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable(name = "idAccount") String idAccount) {
        log.info("A bank account was deleted");
        return bankAccountService.deleteAccountById(idAccount)
                .map(bankCustomer -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
}
