package bank.account.test.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import bank.account.entities.BankAccount;
import bank.account.entities.Client;
import bank.account.entities.Operation;
import bank.account.repositories.BankAccountRepository;
import bank.account.repositories.ClientRepository;
import bank.account.repositories.OperationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class OperationRepositoryTest {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OperationRepository operationRepository;

    private static Client client;
    private static BankAccount bankAccount;
    private static Operation operation;


    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Hadj mabrouk");
        client.setFirstName("Wiem");
        bankAccount = new BankAccount();
        bankAccount.setBalance(990d);

        operation = new Operation();
        operation.setOperationType(DEPOSIT);
        operation.setAmount(100d);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 990d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.operationRepository.save(operation);
        assertEquals(savedOperation.getOperationType(),DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }
}