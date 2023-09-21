package CTeam.com.ex.models.entity;

import java.io.Serializable;

public class KeyEntity implements Serializable {
	private Long lessonId;
	private Long transactionId;

	// 全ての引数のgetterとsetter
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

	// 全ての引数を持つコンストラクタの作成
	public KeyEntity(Long lessonId, Long transactionId) {
		this.lessonId = lessonId;
		this.transactionId = transactionId;
	}

	// 空のコンストラクタの作成
	public KeyEntity() {
	}

}
