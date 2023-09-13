package CTeam.com.ex.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;


public class KeyEntity implements Serializable{
    private Long lessonId;
    private Long transcationId;
	public Long getLessonId() {
		return lessonId;
	}
	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	public Long getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(Long transcationId) {
		this.transcationId = transcationId;
	}
    
}
