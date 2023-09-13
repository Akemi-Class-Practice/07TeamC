package CTeam.com.ex.models.entity;

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
	private Long transactionId;
	
	@Column(name = "student_id")
	private Long studentId;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	public Transaction_HistoryEntity(Long transactionId, Long studentId, int amount, LocalDateTime transactionDate) {
		this.transactionId = transactionId;
		this.studentId = studentId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public Transaction_HistoryEntity( Long studentId, int amount, LocalDateTime transactionDate) {
		this.studentId = studentId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
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

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
	
	
}
