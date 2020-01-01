package newExam.models.dao;

import java.util.List;

import newExam.models.StudentProfile;
import newExam.models.User;

public interface StudentDao {
	
	StudentProfile saveStudent(StudentProfile s);
	User saveTypeAsStudent(long id);
	StudentProfile getThisStudent(long id);
	List<StudentProfile> getStudentOfThisSem(int sem);
	Object checkForStudentReport(long id);

}
