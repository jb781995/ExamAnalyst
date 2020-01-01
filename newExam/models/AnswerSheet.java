package newExam.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="anser_sheet")
public class AnswerSheet {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@JoinColumn(name="stu_id")
	@ManyToOne
	private StudentProfile stuProfile;
	
	@JoinColumn(name="Question_id")
	@ManyToOne
	private ExamQuestions examQue;
	
	@JoinColumn(name="sub_id")
	@ManyToOne
	private Subject subId;
	
	@JoinColumn
	@ManyToOne
	private Semester sem;
	
	private String studentAnswer;
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StudentProfile getStuProfile() {
		return stuProfile;
	}

	public void setStuProfile(StudentProfile stuProfile) {
		this.stuProfile = stuProfile;
	}

	public ExamQuestions getExamQue() {
		return examQue;
	}

	public void setExamQue(ExamQuestions examQue) {
		this.examQue = examQue;
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

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
