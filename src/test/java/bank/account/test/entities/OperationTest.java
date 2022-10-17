package bank.account.test.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import bank.account.entities.BankAccount;
import bank.account.entities.Client;
import bank.account.entities.Operation;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class OperationTest {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Client client;
    private Operation operation;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Hadj mabrouk");
        client.setFirstName("Wiem");

        bankAccount = new BankAccount();

        operation = new Operation();
        operation.setOperationType(DEPOSIT); 
        operation.setAmount(100d);
    }

    @Test
    public void saveOperation() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 0d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.entityManager.persistAndFlush(operation);
        assertEquals(savedOperation.getOperationType(), DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }

    @Test
    public void createBankAccountNullException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
        	Operation newOperation = new Operation();
        	newOperation.setOperationType(WITHDRAWAL); 
        	newOperation.setAmount(-10d);
            this.entityManager.persist(newOperation);
        });

        String expectedMessage = "Bank account is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void createOperationTypeNullException() throws Exception {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 0d);

        Operation nullTypeOperation = new Operation();
        nullTypeOperation.setBankAccount(savedBankAccount);
        nullTypeOperation.setAmount(100d);

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(nullTypeOperation);
        });

        String expectedMessage = "Operation type is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void createAmountNullException() throws Exception {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 0d);

        Operation nullAmountOperation = new Operation();
        nullAmountOperation.setBankAccount(savedBankAccount);
        nullAmountOperation.setOperationType(WITHDRAWAL);

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(nullAmountOperation);
        });

        String expectedMessage = "Amount is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}