package CTeam.com.ex.models.entity;

import java.sql.Time;
import java.time.LocalDate;

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
	private Time startTime;
	
	@Column(name = "finish_time")
	private Time finishTime;
	
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
	private long adminId;

	public LessonEntity(long lessonId, LocalDate startDate, Time startTime, Time finishTime, String lessonName,
			String lessonDetail, int lessonFee, String imageName, LocalDate registerDate, int deleteFlg, long adminId) {
		this.lessonId = lessonId;
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

	public long getLessonId() {
		return lessonId;
	}

	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Time finishTime) {
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

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	
	
	
}
