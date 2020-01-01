package newExam.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="term_start_end")
public class TermDate implements Serializable  {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private int semester;
	private Date fromD;
	private Date toD;
	private String status;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFromD() {
		return fromD;
	}
	public void setFromD(Date fromD) {
		this.fromD = fromD;
	}
	public Date getToD() {
		return toD;
	}
	public void setToD(Date toD) {
		this.toD = toD;
	}
	
	
	
	
	
	
	
	
	

}
