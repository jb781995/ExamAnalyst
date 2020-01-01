package newExam.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="exam_question")
public class ExamQuestions implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String topic;
	
	private String question;
	
	@Column(name="correct_column")
	private String correctOption;
	
	@JoinColumn(name="subject_id")
	@ManyToOne
	private Subject subject;
	
	@JoinColumn(name="semester")
	@ManyToOne
	private Semester sem;
	
	@JoinColumn(name="faculty_id")
	@ManyToOne
	private FacultyProfile facultyProfile;
	
	@OneToMany(mappedBy="examQue")
	private List<AnswerSheet> as;
	
	@Column(name="exam_type")
	private String examType;
	
	
	

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public List<AnswerSheet> getAs() {
		return as;
	}

	public void setAs(List<AnswerSheet> as) {
		this.as = as;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Semester getSem() {
		return sem;
	}

	public void setSem(Semester sem) {
		this.sem = sem;
	}

	public FacultyProfile getFacultyProfile() {
		return facultyProfile;
	}

	public void setFacultyProfile(FacultyProfile facultyProfile) {
		this.facultyProfile = facultyProfile;
	}
	
	
	
	

}
