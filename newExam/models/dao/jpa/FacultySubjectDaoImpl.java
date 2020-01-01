package newExam.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.FacultySubject;
import newExam.models.dao.FacultySubjectDao;

@Repository
public class FacultySubjectDaoImpl implements FacultySubjectDao{
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	@Transactional
	public FacultySubject saveSubjectDetails(FacultySubject fs)
	{
		return entitymanager.merge(fs); 
	}
	
	@Override
	@Transactional
	public FacultySubject approveThisFaculty(long id)
	{
		FacultySubject fs=entitymanager.find(FacultySubject.class,id);
		fs.setApproved("Approved");
		return entitymanager.merge(fs);	
	}
	
	@Override
	@Transactional
	public List<FacultySubject> getMySubjects(long id)
	{
		
		return entitymanager.createNativeQuery("select * from faculty_subject_details where approved LIKE 'Approved' AND user_id="+id, FacultySubject.class).getResultList();
	}

	
	@Override
	@Transactional
	public FacultySubject assignedSubjectToThisFaculty(long id)
	{
		FacultySubject fs= entitymanager.find(FacultySubject.class, id);
		fs.setApproved("NotApproved");
		return entitymanager.merge(fs);
	}
	@Override
	@Transactional
	public List<FacultySubject> facultiesWithSubjectsAllocated()
	{
	return entitymanager.createNativeQuery("select * from faculty_subject_details where approved LIKE 'NotApproved'",FacultySubject.class).getResultList();
	}
	
	@Override
	@Transactional
	public List<FacultySubject> facultiesWithSubjectsApproved()
	{
		return entitymanager.createNativeQuery("select * from faculty_subject_details where approved LIKE 'Approved'",FacultySubject.class).getResultList();

	}

	
	@Override
	@Transactional
	public List<FacultySubject> getMyPartners(long subCode,long myId)
	{
		return entitymanager.createNativeQuery("select * from faculty_subject_details where approved LIKE 'Approved' AND subject_id="+subCode+" AND faculty_id!="+myId, FacultySubject.class).getResultList();
	}
	
	@Override
	@Transactional
	public void unAssign(long id)
	{
		FacultySubject fs= entitymanager.find(FacultySubject.class, id);
		entitymanager.remove(fs);
		
	}

}
