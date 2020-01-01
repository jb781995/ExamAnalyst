package newExam.models;

import java.util.List;

import javax.persistence.Column;
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

@Entity
@Table(name="faculty_subject_details")
public class FacultySubject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@OneToOne
	@JoinColumn
	private User user;
	
	//private FacultyProfile facultyProfile;
    
	@JoinColumn(name="subject_id")
	@ManyToOne
	private Subject lstsubject;
	
    @JoinColumn(name="faculty_id")
	@ManyToOne
   	private FacultyProfile lstfaculty;
   	
   	
	
	//private Semester semester;
	
	


	private String approved ;
	
	//@Column(name="first_name")
	//private String firstName;
	
	//@Column(name="subject_name")
	//private String subjectName;
	
	
	//private String email;
	
	//private int sem;


	public Subject getLstsubject() {
		return lstsubject;
	}


	public void setLstsubject(Subject lstsubject) {
		this.lstsubject = lstsubject;
	}


	public FacultyProfile getLstfaculty() {
		return lstfaculty;
	}


	public void setLstfaculty(FacultyProfile lstfaculty) {
		this.lstfaculty = lstfaculty;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getApproved() {
		return approved;
	}


	public void setApproved(String approved) {
		this.approved = approved;
	}


	


//	public Semester getSemester() {
//		return semester;
//	}
//
//
//	public void setSemester(Semester semester) {
//		this.semester = semester;
//	}


	


//	public String getFirstName() {
//		return firstName;
//	}
//
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//	public String getSubjectName() {
//		return subjectName;
//	}
//
//
//	public void setSubjectName(String subjectName) {
//		this.subjectName = subjectName;
//	}
//	public int getSem() {
//		return sem;
//	}
//
//
//	public void setSem(int sem) {
//		this.sem = sem;
//	}
//
//
//	public FacultyProfile getFacultyProfile() {
//		return facultyProfile;
//	}
//
//
//	public void setFacultyProfile(FacultyProfile facultyProfile) {
//		this.facultyProfile = facultyProfile;
//	}
	
	
	


	
	
	
	
	
	
	

}
