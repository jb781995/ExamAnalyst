package newExam.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.FacultySubject;
import newExam.models.TermDate;
import newExam.models.TimeTable;
import newExam.models.dao.AdminDao;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@PersistenceContext
	EntityManager entitymanager;
	
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
	public TimeTable setDate(TimeTable tt)
	{
		return entitymanager.merge(tt);
	}
	
	@Override
	@Transactional
	public List<TimeTable> getAllTimeTable()
	{
		return entitymanager.createNativeQuery("select * from time_table", TimeTable.class).getResultList();
	}

	@Override
	@Transactional
	public void deleteThisDate(long id)
	{
		TimeTable tt= entitymanager.find(TimeTable.class, id);
		entitymanager.remove(tt);
	}
	
	@Override
	@Transactional
	public TimeTable findById(long id)
	{
		return entitymanager.find(TimeTable.class, id);
	}
	
	@Override
	@Transactional
	public List<TermDate> getTermDateList()
	{
		List<TermDate> td=entitymanager.createNativeQuery("select * from term_start_end ", TermDate.class).getResultList();
		return td.size()==0? null: td;
	}
	
	
	@Override
	@Transactional
	public TermDate save(TermDate td)
	{
		return entitymanager.merge(td);
	}
	
	@Override
	@Transactional
	public TermDate find(long id)
	{
		return entitymanager.find(TermDate.class, id);
	}
	
}
