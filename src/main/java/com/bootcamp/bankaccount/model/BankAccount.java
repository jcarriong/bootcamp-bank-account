package com.bootcamp.bankaccount.model;

import lombok.*;
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
    private String idAccount;
    @NonNull
    private String idProduct;
    @NonNull
    private String idCustomer;
    @NonNull
    private String accountNumber; //numero de cuenta (14 digits)
    @NonNull
    private String cci; //numero de cuenta interbancaria (20 digits)
    private Float availableBalance; //saldo disponible
    private List<String> holderAccount; //cuenta titular 1.*
    private List<String> authorizedSigner; //firmante autorizado 0.*
    private List<String> bankMovements; //lista de movimientos bancarios

}
