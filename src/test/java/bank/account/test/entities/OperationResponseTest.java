package bank.account.test.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import bank.account.entities.BankAccount;
import bank.account.entities.Client;
import bank.account.entities.Operation;
import bank.account.entities.OperationResponse;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationResponseTest {
    private ModelMapper modelMapper = new ModelMapper();
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    private static Operation operation;
    private static OperationResponse operationDto;

    @BeforeAll
    public static void setUp() {
        Client client = new Client();
        client.setLastName("Hadj mabrouk");
        client.setFirstName("Wiem");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setClient(client);
        bankAccount.setBalance(1000d);

        operation = new Operation();
        operation.setBankAccount(bankAccount);
        operation.setOperationType(DEPOSIT);
        operation.setAmount(100d);

        operationDto = new OperationResponse();
        operationDto.setBankAccountClientLastName("Hadj mabrouk");
        operationDto.setBalance(500d);
        operationDto.setType(WITHDRAWAL);
        operationDto.setAmount(-100d);
        operationDto.setDate(LocalDateTime.now());
    }

    @Test
    public void mapOperationEntityToOperationDto() {
        OperationResponse dto = modelMapper.map(operation, OperationResponse.class);
        assertEquals(operation.getOperationType(), dto.getType());
        assertEquals(operation.getAmount(), dto.getAmount());
        assertEquals(operation.getBalance(), dto.getBalance());
        assertEquals(operation.getBankAccount().getClient().getLastName(), dto.getBankAccountClientLastName());
    }

    @Test
    public void mapOperationDtoToOperationEntity() {
        Operation op = modelMapper.map(operationDto, Operation.class);
        assertEquals(op.getOperationType(), operationDto.getType());
        assertEquals(op.getAmount(), operationDto.getAmount());
        assertEquals(op.getBalance(), operationDto.getBalance());
        assertEquals(op.getBankAccount().getClient().getLastName(), operationDto.getBankAccountClientLastName());
    }

}