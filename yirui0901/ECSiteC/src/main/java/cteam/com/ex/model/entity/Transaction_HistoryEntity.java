package cteam.com.ex.model.entity;

import java.time.LocalDate;

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
	private long studentId;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "transaction_date")
	private LocalDate transactionDate;

	public Transaction_HistoryEntity(long transactionId, long studentId, int amount, LocalDate transactionDate) {
		this.transactionId = transactionId;
		this.studentId = studentId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public Transaction_HistoryEntity(long transactionId, long studentId, int amount) {
		this.transactionId = transactionId;
		this.studentId = studentId;
		this.amount = amount;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
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
