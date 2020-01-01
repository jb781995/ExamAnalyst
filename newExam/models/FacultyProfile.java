package newExam.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="facultyy_profile")
public class FacultyProfile implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="fac_id")
	private long facId;
	
//	
//	@OneToMany(mappedBy="facId")
//	private List<TimeTable> timeTable;
	
	
	
//	@OneToOne
//	@JoinColumn(name="user_id")
//    private User userId;
	
//	@ManyToMany
//	@JoinTable(name="faculty_subject",
//	joinColumns=@JoinColumn(name="faculty_id"),
//	inverseJoinColumns=@JoinColumn(name="subject_name"))
//	private List<Subject> subjects;
	
	
	@OneToOne
	@JoinColumn
	private User facUser;
	
	@OneToMany(mappedBy = "lstsubject")
	private List<FacultySubject> listFacultySubject;
	
	@OneToMany(mappedBy="facultyProfile")
	private List<ExamQuestions> examQuestions;
	
	@OneToMany(mappedBy="fac")
	List<Topics> topic;
	
	
	
	
	
	public List<Topics> getTopic() {
		return topic;
	}

	public void setTopic(List<Topics> topic) {
		this.topic = topic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFacId() {
		return facId;
	}

	public void setFacId(long facId) {
		this.facId = facId;
	}

//	public User getUserId() {
//		return userId;
//	}
//
//	public void setUserId(User userId) {
//		this.userId = userId;
//	}

	public User getFacUser() {
		return facUser;
	}

	public void setFacUser(User facUser) {
		this.facUser = facUser;
	}

	public List<ExamQuestions> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(List<ExamQuestions> examQuestions) {
		this.examQuestions = examQuestions;
	}

//	public List<TimeTable> getTimeTable() {
//		return timeTable;
//	}
//
//	public void setTimeTable(List<TimeTable> timeTable) {
//		this.timeTable = timeTable;
//	}

//	public List<Subject> getSubjects() {
//		return subjects;
//	}
//
//	public void setSubjects(List<Subject> subjects) {
//		this.subjects = subjects;
//	}

		
	
	

	
}
