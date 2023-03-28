package com.bootcamp.bankaccount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankAccount")
public class BankAccount extends BaseAuditDto{

    @Id
    private String id_account;
    private String id_product;
    private String id_customer;
    private String account_number; //numero de cuenta (14 digits)
    private String cci; //numero de cuenta interbancaria (20 digits)
    private Float availableBalance; //saldo disponible
    private List<String> holderAccount; //cuenta titular 1.*
    private List<String> authorizedSigner; //firmante autorizado 0.*
    private List<String> bankMovements; //lista de movimientos bancarios

}