package newExam.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "VEHICLE_TYPE")
public class User implements Serializable  {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="f_name")
	private String fname;
//	long phn_numbner;
//	
//	public long getPhn_numbner() {
//		return phn_numbner;
//	}
//
//	public void setPhn_numbner(long phn_numbner) {
//		this.phn_numbner = phn_numbner;
//	}

	@Column(name="l_name")
	private String lname;
	
	@NotEmpty(message = "Email is required") // @NotEmpty,@Email: server side validation provided by hibernate validator jars.
	@Email(message = "Please enter a valid email")
	private String email;
	
	private String password;
	
	private String type;
	
	@OneToOne(mappedBy="stuUser")
	private StudentProfile studentProfile;
	
	@OneToOne(mappedBy="facUser")
	private FacultyProfile facultyProfile;
	
	@ElementCollection
    @CollectionTable(name = "authorities",
        joinColumns = @JoinColumn(name = "user_id") )
    @Column(name = "role")
    private Set<String> roles;
	
	 public boolean isAdmin()
	    {
	        return roles.contains( "ROLE_ADMIN" );
	    }

	    public boolean isReviewer()
	    {
	        return roles.contains( "ROLE_REVIEWER" );
	    }
	    
	    public boolean isReviewer2()
	    {
	        return roles.contains( "ROLE_REVIEWER2" );
	    } 
	    public boolean isFaculty()
	    {
	        return type.contains( "Faculty" );
	    }
	    public boolean isStudent()
	    {
	        return type.contains( "Student" );
	    }


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public FacultyProfile getFacultyProfile() {
		return facultyProfile;
	}

	public void setFacultyProfile(FacultyProfile facultyProfile) {
		this.facultyProfile = facultyProfile;
	}
	
	
	
	
	

}
