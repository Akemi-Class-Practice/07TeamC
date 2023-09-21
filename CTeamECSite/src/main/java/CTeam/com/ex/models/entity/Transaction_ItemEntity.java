package CTeam.com.ex.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//tableを指名する
@Table(name = "transaction_item")
public class Transaction_ItemEntity {
	
	@Id
	//フィールドとテーブルのカラムをマッピングする
	@Column(name = "id")
	//プライマリーキーを自動生成する方法を指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "lesson_id")
	private Long lessonId;
	
	@Column(name = "transaction_id")
	private Long transactionId;
	//空の引数を持つコンストラクタの作成
	public Transaction_ItemEntity() {

	}
	
	//全ての引数を持つコンストラクタの作成
	public Transaction_ItemEntity(Long lessonId, Long transactionId) {
		this.lessonId = lessonId;
		this.transactionId = transactionId;
	}
    //全ての引数のgetterとsetter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
