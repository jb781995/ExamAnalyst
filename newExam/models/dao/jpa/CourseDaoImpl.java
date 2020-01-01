package newExam.models.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import csjobs.model.Job;
import newExam.models.dao.CourseDao;
import java.util.List;

import newExam.models.Semester;
import newExam.models.Subject;

@Repository
public class CourseDaoImpl implements CourseDao  {
	
	 @PersistenceContext
		EntityManager entitymanager;

@Override
@Transactional
 public List<Subject> getSubjects(int semester)
{
	
	// always pass name of table within query as name of your class. for instance
	// name of my table is semester, but name of my class is Semester.
	//so , i've given here select from Subject . Not , select from subject.
	String query = "select * from subjects where semester = "+semester;
          return entitymanager.createNativeQuery(query,Subject.class).getResultList();  

}


@Override
@Transactional
public List<Semester> getSemesters()
{
	 return entitymanager.createNativeQuery( "select * from semester order by id asc", Semester.class ).getResultList();
}

@Override
@Transactional
public Subject getThisSubject(long id)
{
	 return entitymanager.find(Subject.class,id );
}

@Override
@Transactional
public Semester getThisSem(int i)
{
	return entitymanager.find(Semester.class, i); 
}


}
