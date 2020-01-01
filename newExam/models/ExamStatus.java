package newExam.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exam_status")
public class ExamStatus {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="stu_id")
	private StudentProfile stuId;
	
	@ManyToOne
	@JoinColumn(name="sub_id")
	private Subject subId;
	
	@ManyToOne
	@JoinColumn
	private Semester sem;
	
	String status ;
	String examType;
	Date date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StudentProfile getStuId() {
		return stuId;
	}
	public void setStuId(StudentProfile stuId) {
		this.stuId = stuId;
	}
	public Subject getSubId() {
		return subId;
	}
	public void setSubId(Subject subId) {
		this.subId = subId;
	}
	public Semester getSem() {
		return sem;
	}
	public void setSem(Semester sem) {
		this.sem = sem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	

}
