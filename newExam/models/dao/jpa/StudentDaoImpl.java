package newExam.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.AnswerSheet;
import newExam.models.StudentProfile;
import newExam.models.User;
import newExam.models.dao.StudentDao;

@Repository
public class StudentDaoImpl implements StudentDao {

	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	 @Transactional
	 public StudentProfile saveStudent(StudentProfile s)
	 {
		 return entitymanager.merge(s);
	 }
	 
	 @Override
	 @Transactional
	 public User saveTypeAsStudent(long id)
	 {
		 
		 User u=entitymanager.find(User.class, id);
		 u.setType("Student");
		 return entitymanager.merge(u);
	 }
	 
	 @Override
	 @Transactional
	 public StudentProfile getThisStudent(long id)
	 {
		 return entitymanager.find(StudentProfile.class, id);
	 }
	 
	 @Override
	 @Transactional
	 public List<StudentProfile> getStudentOfThisSem(int sem)
	 {
		 return entitymanager.createNativeQuery("select * from student_profile where semester="+sem, StudentProfile.class).getResultList();
	 }
	 
	 @Override
	 @Transactional
	 public Object checkForStudentReport(long id)
	 {
		 List<AnswerSheet> as= entitymanager.createNativeQuery("select * from anser_sheet where stu_id="+id, AnswerSheet.class).getResultList();
		 return as.size()==0?null:as.get(0);
	 }
}
