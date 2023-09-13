package CTeam.com.ex.models.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;


@Entity
@IdClass(value=KeyEntity.class)
public class SubscriptionEntity {
	@Id
	@Column(name = "lesson_id")
	private Long lessonId;
	
	@Id
	@Column(name = "transaction_id")
	private Long transactionId;
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "start_time")
    private LocalTime startTime;
	
	
	@Column(name = "finish_time")
    private LocalTime finishTime;
	
	@Column(name = "lesson_name")
	private String lessonName;

	@Column(name = "lesson_detail")
	private String lessonDetail;

	@Column(name = "lesson_fee")
	private int lessonFee;

	@Column(name = "image_name")
	private String imageName;
	
	@Column(name="student_id")
	private Long studentId;
	
	@Column(name="amount")
	private int amount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="transaction_date")
	private LocalDateTime transationDate;
	
    public SubscriptionEntity() {
		
	}

	public SubscriptionEntity(Long lessonId, Long transactionId, LocalDate startDate, LocalTime startTime,
			LocalTime finishTime, String lessonName, String lessonDetail, int lessonFee, String imageName,
			Long studentId, int amount, LocalDateTime transationDate) {
		this.lessonId = lessonId;
		this.transactionId = transactionId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.lessonName = lessonName;
		this.lessonDetail = lessonDetail;
		this.lessonFee = lessonFee;
		this.imageName = imageName;
		this.studentId = studentId;
		this.amount = amount;
		this.transationDate = transationDate;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalTime finishTime) {
		this.finishTime = finishTime;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getLessonDetail() {
		return lessonDetail;
	}

	public void setLessonDetail(String lessonDetail) {
		this.lessonDetail = lessonDetail;
	}

	public int getLessonFee() {
		return lessonFee;
	}

	public void setLessonFee(int lessonFee) {
		this.lessonFee = lessonFee;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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

	public LocalDateTime getTransationDate() {
		return transationDate;
	}

	public void setTransationDate(LocalDateTime transationDate) {
		this.transationDate = transationDate;
	}
	
}
