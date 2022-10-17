package bank.account.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.account.entities.BankAccount;
import bank.account.entities.Client;
import bank.account.entities.Operation;
import bank.account.entities.OperationResponse;
import bank.account.repositories.BankAccountRepository;
import bank.account.repositories.ClientRepository;
import bank.account.repositories.OperationRepository;
import bank.account.services.BankAccountService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    @Autowired
    OperationRepository operationRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OperationResponse addOperation(Double amount, Integer bankAccountId, String operationType) {
    	if(WITHDRAWAL.equals(operationType)) {
    		 amount = amount > 0 ? (-1 * amount) : amount;
    	}

        Operation operation = new Operation();
        operation.setOperationType(operationType);
        operation.setAmount(amount);
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        operation.setBankAccount(bankAccount);
        Double balance = bankAccount.getBalance() + amount;
        operation.setBalance(balance);
        bankAccount.setBalance(balance);
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(operationRepository.save(operation), OperationResponse.class);
    }

    @Override
    public List<OperationResponse> allOperations(Integer bankAccountId) {
    	BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        List<Operation> operations = operationRepository.findByBankAccount(bankAccount);
        return operations
                .stream()
                .map(operation -> modelMapper.map(operation, OperationResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getBalance(Integer bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId);
        return bankAccount.getBalance();
    }

}
