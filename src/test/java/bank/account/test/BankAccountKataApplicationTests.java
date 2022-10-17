package bank.account.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import bank.account.entities.OperationResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountKataApplicationTests {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@AfterEach

	@Test
	public void allOperations() throws Exception {
		ResponseEntity<OperationResponse[]> response =
				restTemplate.getForEntity(
						"http://localhost:" + port + "/operations",
						OperationResponse[].class);
		OperationResponse[] operations = response.getBody();

		assertThat(operations).isNotNull();
		assertThat(operations).hasSize(2);
		assertThat(operations[0].getType()).isEqualTo(DEPOSIT);
		assertThat(operations[0].getAmount()).isEqualTo(1000d);
		assertThat(operations[1].getType()).isEqualTo(WITHDRAWAL);
		assertThat(operations[1].getAmount()).isEqualTo(-500d);
	}

}