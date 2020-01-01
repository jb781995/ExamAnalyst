package newExam.models.dao;

import java.util.List;

import newExam.models.FacultyProfile;
import newExam.models.StudentProfile;
import newExam.models.User;

public interface UserDao {
	
	User saveUser(User u);
	User getUser( String email );
	List<User> getAllUsers();
	User getThisUser(long id);
	
	
	
	

}
