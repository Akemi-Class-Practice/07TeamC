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
//tableを指名する
@Table(name = "transaction_history")
public class Transaction_HistoryEntity {
	
	@Id
	//フィールドとテーブルのカラムをマッピングする
	@Column(name = "transaction_id")
	//プライマリーキーを自動生成する方法を指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@Column(name = "student_id")
	private Long studentId;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	//空のコンストラクタの作成
	public Transaction_HistoryEntity() {
	}
	//必要引数を持つコンストラクタの作成
	public Transaction_HistoryEntity( Long studentId, int amount, LocalDateTime transactionDate) {
		this.studentId = studentId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
    //全ての引数のgetterとsetter
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
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

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
	
	
}
