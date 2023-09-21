package cteam.com.ex.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_history")
public class Transaction_HistoryEntity {

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	
	@Column(name = "student_id")
	private Long studentId;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "transaction_date")
	private LocalDate transactionDate;


	
	public Transaction_HistoryEntity() {

	}

	public Transaction_HistoryEntity(Long studentId, int amount, LocalDate transactionDate) {
		this.studentId = studentId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}



	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
