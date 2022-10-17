package bank.account.services;

import java.util.List;

import bank.account.entities.Client;
import bank.account.entities.OperationResponse;


public interface BankAccountService {

    OperationResponse addOperation(Double amount, Integer bankAccountId, String operationType) ;

    List<OperationResponse> allOperations(Integer bankAccountId);

    Double getBalance( Integer bankAccountId);

}
