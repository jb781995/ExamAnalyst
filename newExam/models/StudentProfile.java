package newExam.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_profile")
public class StudentProfile implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="enrollment_no")
	private long enrollmentNo;
	
	
//	@OneToOne
//	@JoinColumn(name="user_id")
//    private User userId;
	
	@OneToMany(mappedBy="stuProfile")
	private List<AnswerSheet> as;
	
	@OneToMany(mappedBy="stuId")
	private List<ExamStatus> es;
	
	
	
	
	public List<ExamStatus> getEs() {
		return es;
	}

	public void setEs(List<ExamStatus> es) {
		this.es = es;
	}

	public List<AnswerSheet> getAs() {
		return as;
	}

	public void setAs(List<AnswerSheet> as) {
		this.as = as;
	}

	private int semester;
	
	private  String division;
	
	@OneToOne
	@JoinColumn
	private User stuUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEnrollmentNo() {
		return enrollmentNo;
	}

	public void setEnrollmentNo(long enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

//	public User getUserId() {
//		return userId;
//	}
//
//	public void setUserId(User userId) {
//		this.userId = userId;
//	}

	public User getStuUser() {
		return stuUser;
	}

	public void setStuUser(User stuUser) {
		this.stuUser = stuUser;
	}
	
	
	
	
	
	
	

}
