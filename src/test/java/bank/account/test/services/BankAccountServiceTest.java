package bank.account.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import bank.account.entities.BankAccount;
import bank.account.entities.Operation;
import bank.account.entities.OperationResponse;
import bank.account.repositories.BankAccountRepository;
import bank.account.repositories.OperationRepository;
import bank.account.services.impl.BankAccountServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BankAccountServiceTest {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
	
    @Autowired
    BankAccountServiceImpl operationService;

    @MockBean
    OperationRepository operationRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;


    @BeforeEach
    void setUp() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);

        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(anyInt()))
                .thenReturn(bankAccount);

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Operation.class)))
                .thenReturn(operation);

    }

    @Test
    void deposit() {
        OperationResponse deposit = operationService.addOperation(100d, 1,DEPOSIT);
        assertNotNull(deposit);
        assertEquals(deposit.getBalance(), 1100d);
    }

    @Test
    void withdraw() {
        OperationResponse withdraw = operationService.addOperation(100d, 1,WITHDRAWAL);
        assertNotNull(withdraw);
        assertEquals(withdraw.getBalance(), 900d);
    }
}