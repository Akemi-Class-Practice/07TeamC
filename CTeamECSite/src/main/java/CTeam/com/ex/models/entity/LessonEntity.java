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
//tableを指名する
@Table(name = "lesson")
public class LessonEntity {
	
	@Id
	//フィールドとテーブルのカラムをマッピングする
	@Column(name = "lesson_id")
	//プライマリーキーを自動生成する方法を指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	//lessonId
	private Long lessonId;
	//講座開始日付け
	@Column(name = "start_date")
	private LocalDate startDate;
	//開始時間
	@Column(name = "start_time")
	private LocalTime startTime;
	//終了時間
	@Column(name = "finish_time")
	private LocalTime finishTime;
	//講座の名前
	@Column(name = "lesson_name")
	private String lessonName;
	//講座詳細
	@Column(name = "lesson_detail")
	private String lessonDetail;
	//講座費用
	@Column(name = "lesson_fee")
	private int lessonFee;
	//画像
	@Column(name = "image_name")
	private String imageName;
	//登録日付け
	@Column(name = "register_date")
	private LocalDate registerDate;
	//削除flg
	@Column(name = "delete_flg")
	private int deleteFlg;
	//adminId
	@Column(name = "admin_id")
	private Long adminId;
	//空のコンストラクタの作成
	public LessonEntity() {
		
	}
	//全ての引数を持つコンストラクタの作成
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
	//全ての引数のgetterとsetter
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
