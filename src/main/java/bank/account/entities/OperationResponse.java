package bank.account.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class OperationResponse {
    private Integer id;

    private String type;

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("client")
    private String bankAccountClientLastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBankAccountClientLastName() {
        return bankAccountClientLastName;
    }

    public void setBankAccountClientLastName(String bankAccountClientLastName) {
        this.bankAccountClientLastName = bankAccountClientLastName;
    }
}
