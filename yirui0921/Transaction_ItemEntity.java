package cteam.com.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_item")
public class Transaction_ItemEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "lesson_id")
	private Long lessonId;
	
	@Column(name = "transaction_id")
	private Long transactionId;

	public Transaction_ItemEntity() {

	}

	public Transaction_ItemEntity(Long lessonId, Long transactionId) {
		this.lessonId = lessonId;
		this.transactionId = transactionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
}
