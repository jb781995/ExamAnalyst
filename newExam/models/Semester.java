package newExam.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="semester")
public class Semester implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	@Id
	private int semester;
	
	@OneToMany(mappedBy="sem")
	@JsonManagedReference
	List<Subject> subjects;
	
	@OneToMany(mappedBy="sem")
	private List<TimeTable> timeTable;
	
	@OneToMany(mappedBy="sem")
	private List<ExamQuestions> examQuestions;
	
	@OneToMany(mappedBy="sem")
	private List<AnswerSheet> as;
	
	@OneToMany
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

	public List<ExamQuestions> getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(List<ExamQuestions> examQuestions) {
		this.examQuestions = examQuestions;
	}
	
	
	
	
	

	
}
