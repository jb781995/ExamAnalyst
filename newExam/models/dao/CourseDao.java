package newExam.models.dao;

import java.util.List;

import newExam.models.Semester;
import newExam.models.Subject;

public interface CourseDao {

	List<Subject> getSubjects(int i);
	
	List<Semester> getSemesters();
	
	Subject getThisSubject(long id);
	
	Semester getThisSem(int i);
}
