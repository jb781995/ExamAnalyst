package newExam.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="subjects")
public class Subject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name="subject_name")
	private String subjectName;
	
	@OneToMany(mappedBy = "lstfaculty")
	private List<FacultySubject> lstfacultySubject;
	
	@OneToMany(mappedBy="subId")
	private List<TimeTable> timeTable;
	
	@OneToMany(mappedBy="subject")
	private List<ExamQuestions> examQuestion;
	
	@OneToMany(mappedBy="subId")
	private List<AnswerSheet> as;
	
	@OneToMany(mappedBy="subId")
	private List<ExamStatus>  es;
	
	@OneToMany(mappedBy="sub")
	List<Topics> topic;
	
	
	
	
	
	
	
	
	
	
		
	
//	@Column(name="semester")
//	private int semeter; 
	
	public List<Topics> getTopic() {
		return topic;
	}

	public void setTopic(List<Topics> topic) {
		this.topic = topic;
	}

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

	@ManyToOne
	@JoinColumn(name="semester")
	@JsonBackReference
	private Semester sem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	public Semester getSem() {
		return sem;
	}

	public void setSem(Semester sem) {
		this.sem = sem;
	}

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

	public List<ExamQuestions> getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(List<ExamQuestions> examQuestion) {
		this.examQuestion = examQuestion;
	}
	
	
	
	 

}
