package bank.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bank.account.entities.Client;
import bank.account.entities.OperationRequestBody;
import bank.account.entities.OperationResponse;
import bank.account.services.BankAccountService;

import java.util.List;

@RestController
public class BankAccountController {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping(value="/operations")
    public List<OperationResponse> getAllOperations(@RequestParam(required = true) Integer bankAccountId) {
        return bankAccountService.allOperations(bankAccountId);
    }

    @GetMapping(value="/balance")
    public Double getBalance(@RequestParam(required = true) Integer id) {
        return bankAccountService.getBalance(id);
    }

    @PostMapping(value = "/deposit")
    public OperationResponse deposit( @RequestBody OperationRequestBody operation) {
        return bankAccountService.addOperation(operation.getAmount(), operation.getBankAccountId(),DEPOSIT );
    }

    @PostMapping(value = "/withdraw")
    public OperationResponse withdraw( @RequestBody OperationRequestBody operation) {
       return bankAccountService.addOperation(operation.getAmount(), operation.getBankAccountId(),WITHDRAWAL);

    }

}