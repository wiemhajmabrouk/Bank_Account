package bank.account.test.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import bank.account.entities.Client;
import bank.account.repositories.ClientRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private static Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Hadj mabrouk");
        client.setFirstName("Wiem");
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        assertEquals(this.clientRepository.findById(savedClient.getId()), client);
    }

    @Test
    public void saveClientAndFindByName() {
        Client savedClient = this.clientRepository.save(client);
        List<Client> clients = this.clientRepository.findByFirstNameAndLastName(savedClient.getFirstName(),savedClient.getLastName());
        assertEquals(clients.get(0).getLastName(), "Hadj mabrouk");
        assertEquals(clients.get(0).getFirstName(), "Wiem");
    }
}