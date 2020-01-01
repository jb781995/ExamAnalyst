package newExam.models.dao;

import java.util.List;

import newExam.models.FacultySubject;
import newExam.models.TermDate;
import newExam.models.TimeTable;

public interface AdminDao {
	
	FacultySubject approveThisFaculty(long id);
	
	TimeTable setDate(TimeTable tt);
	
	List<TimeTable> getAllTimeTable();
	
	void deleteThisDate(long id);
	
	TimeTable findById(long id);
	
	TermDate save(TermDate td);
	
	TermDate find(long id);
	
	List<TermDate> getTermDateList();

}
