package CTeam.com.ex.models.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lesson")
public class LessonEntity {
	
	@Id
	@Column(name = "lesson_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lessonId;
	
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
	
	@Column(name = "register_date")
	private LocalDate registerDate;
	
	@Column(name = "delete_flg")
	private int deleteFlg;
	
	@Column(name = "admin_id")
	private Long adminId;

	public LessonEntity() {
		
	}

	public LessonEntity(LocalDate startDate, LocalTime startTime, LocalTime finishTime, String lessonName,
			String lessonDetail, int lessonFee, String imageName, LocalDate registerDate, int deleteFlg, Long adminId) {
		this.startDate = startDate;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.lessonName = lessonName;
		this.lessonDetail = lessonDetail;
		this.lessonFee = lessonFee;
		this.imageName = imageName;
		this.registerDate = registerDate;
		this.deleteFlg = deleteFlg;
		this.adminId = adminId;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
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

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	
	

	
	
	
}
