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

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class BankAccountTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Hadj mabrouk");
        client.setFirstName("Wiem");
        bankAccount = new BankAccount();
        bankAccount.setBalance(990d);
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 990d);
    }

    @Test
    public void createClientNullException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(new BankAccount());
        });

        String expectedMessage = "Client is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}