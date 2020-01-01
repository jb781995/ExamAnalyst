package newExam.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="my_topics")
public class Topics implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	String topics;
	
	@JoinColumn(name="subject_id")
	@ManyToOne
	private Subject sub;
	
	@JoinColumn(name="fac_id")
	@ManyToOne
	private FacultyProfile fac;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	int sem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public Subject getSub() {
		return sub;
	}

	public void setSub(Subject sub) {
		this.sub = sub;
	}

	public FacultyProfile getFac() {
		return fac;
	}

	public void setFac(FacultyProfile fac) {
		this.fac = fac;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}
	
	
	

}
