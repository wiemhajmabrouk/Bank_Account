package bank.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bank.account.entities.BankAccount;
import bank.account.entities.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	 List<Operation> findByBankAccount(BankAccount bankAccount);
}
