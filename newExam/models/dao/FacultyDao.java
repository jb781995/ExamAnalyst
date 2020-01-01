package newExam.models.dao;

import java.util.List;

import newExam.models.FacultyProfile;
import newExam.models.TimeTable;
import newExam.models.Topics;
import newExam.models.User;

public interface FacultyDao {
	
	FacultyProfile saveFaculty(FacultyProfile f);
	User saveTypeAsFaculty(long id);
	List<User> getAllFaculties();
	User getThisFacultyUser(long id);
	FacultyProfile findThisFaculty(long id);
	FacultyProfile find(long id);
	FacultyProfile saveFacultySubject(FacultyProfile f);
	List<TimeTable> getMyTimeTable(long id);
	
	Topics save(Topics t);
	Topics findTopic(long id);
	void deleteTopic(long id);
	
	List<Topics> getTopicsOfMyPartners(long partnerId,long subId);
	List<Topics> getMyTopics(long id);
	
	//FacultyProfile getThisFacultyByUser(User user);

}
