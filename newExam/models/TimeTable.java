package newExam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="time_table")
public class TimeTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
//	@JoinColumn(name="fac_id")
//	@ManyToOne
//	private FacultyProfile facId;
	
	@JoinColumn(name="sub_id")
	@ManyToOne
	private Subject subId;
	
	@JoinColumn
	@ManyToOne
	private Semester sem;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	
	private String examType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

//	public FacultyProfile getFacId() {
//		return facId;
//	}
//
//	public void setFacId(FacultyProfile facId) {
//		this.facId = facId;
//	}

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
	
	
	
	
	
	
	

}
