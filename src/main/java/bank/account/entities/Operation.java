package bank.account.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String operationType;

	@NotNull
	private Double amount;

	private LocalDateTime operationDate;
	
	private Double balance;
	
	@NotNull
	@ManyToOne
	private BankAccount bankAccount;

	public Operation() {
		this.operationDate = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(LocalDateTime operationDate) {
		this.operationDate = operationDate;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public String getOperationType() {
		return operationType;
	}


	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		return "Operation{"+
				"id=" + id +
				", operationType=" + operationType +
				", amount="+ amount +
				", balance="+ balance +
				", operationDate=" + operationDate.format(formatter) + "}";
	}
}
