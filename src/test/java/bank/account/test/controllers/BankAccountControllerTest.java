package bank.account.test.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import bank.account.controllers.BankAccountController;
import bank.account.entities.OperationResponse;
import bank.account.services.impl.BankAccountServiceImpl;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {
	public static final String DEPOSIT ="DEPOSIT";
	public static final String WITHDRAWAL ="WITHDRAWAL";
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BankAccountServiceImpl operationService;

    @BeforeEach
    void setUp() {
        OperationResponse deposit = new OperationResponse();
        deposit.setBankAccountClientLastName("Hadj mabrouk");
        deposit.setBalance(100d);
        deposit.setType(DEPOSIT);
        deposit.setAmount(100d);
        deposit.setDate(LocalDateTime.now());

        when(operationService.addOperation(100d,1,DEPOSIT))
                .thenReturn(deposit);

        OperationResponse withdraw = new OperationResponse();
        withdraw.setBankAccountClientLastName("Hadj mabrouk");
        withdraw.setBalance(0d);
        withdraw.setType(WITHDRAWAL);
        withdraw.setAmount(100d);
        withdraw.setDate(LocalDateTime.now());
        when(operationService.addOperation(100d,1,WITHDRAWAL))
                .thenReturn(withdraw);
    }

    @Test
    public void allOperations() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/operations")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deposit() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/deposit")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100));
    }

    @Test
    public void withdraw() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/withdraw")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0));
    }

}