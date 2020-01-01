package newExam.models.dao;

import java.util.List;

import newExam.models.FacultySubject;

public interface FacultySubjectDao {
	
	FacultySubject saveSubjectDetails(FacultySubject fs);
	List<FacultySubject> facultiesWithSubjectsAllocated();
	List<FacultySubject> facultiesWithSubjectsApproved();
	FacultySubject assignedSubjectToThisFaculty(long id);
	FacultySubject approveThisFaculty(long id);
	List<FacultySubject> getMySubjects(long id);
	List<FacultySubject> getMyPartners(long id,long myId);
	void unAssign(long id);


}
