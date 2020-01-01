package newExam.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.FacultyProfile;
import newExam.models.TimeTable;
import newExam.models.Topics;
import newExam.models.User;
import newExam.models.dao.FacultyDao;

@Repository
public class FacultyDaoImpl  implements FacultyDao {
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	 @Transactional
	public List<User> getAllFaculties()
	{
		
		return entitymanager.createNativeQuery("select * from user where type LIKE 'Faculty'", User.class).getResultList();
	}
	
	@Override
	 @Transactional
	 public User getThisFacultyUser(long id)
	 {
		return entitymanager.find(User.class, id);
	 }
	
	@Override
	 @Transactional
	 public FacultyProfile findThisFaculty(long id)
	 {
		String query="select * from facultyy_profile where id="+id;
	   List<FacultyProfile> faculties=entitymanager.createNativeQuery(query, FacultyProfile.class).getResultList();	
	   return faculties.size() == 0 ? null : faculties.get( 0 );
		
		//return entitymanager.find(FacultyProfile.class,id);
		//return (FacultyProfile) entitymanager.createNativeQuery("select * from facultyy_profile where facUser_id="+id, FacultyProfile.class);
	 }
	
	@Override
	 @Transactional
	public FacultyProfile find(long id)
	{
		return entitymanager.find(FacultyProfile.class, id);
	}
	
//	@Override
//	 @Transactional
//	 public FacultyProfile getThisFacultyByUser(User user)
//	 {
//		return entitymanager.find(FacultyProfile.class, id);
//	 }
	
	
	
	
	 @Override
	 @Transactional
	 public FacultyProfile saveFaculty(FacultyProfile f)
	 {
		 return entitymanager.merge(f);
	 }
	 
	 
	 @Override
	 @Transactional
	 public FacultyProfile saveFacultySubject(FacultyProfile f)
	 {
		 return entitymanager.merge(f);
	 }

	 
	 @Override
	 @Transactional
	 public User saveTypeAsFaculty(long id)
	 {
		 
		 User u=entitymanager.find(User.class, id);
		 u.setType("Faculty");
		 return entitymanager.merge(u);
	 }
	 
	 
	 @Override
	 @Transactional
	 public List<TimeTable> getMyTimeTable(long id)
	 {
		 return entitymanager.createNativeQuery("select * from time_table where sub_id="+id, TimeTable.class).getResultList();
	 }
	 
	 
	 @Override
	 @Transactional
	 public Topics save(Topics t)
	 {
		 return entitymanager.merge(t);
	 }
	 
	 @Override
	 @Transactional
	 public List<Topics> getTopicsOfMyPartners(long partnerId,long subId)
	 {
		 return entitymanager.createNativeQuery("select * from  my_topics where fac_id="+partnerId+" AND subject_id="+subId, Topics.class).getResultList();
	 }
	 
	 @Override
	 @Transactional
	 public List<Topics> getMyTopics(long id)
	 {
		 return entitymanager.createNativeQuery("select * from my_topics where user_id="+id, Topics.class).getResultList();
	 }
	 
	 @Override
	 @Transactional
	 public Topics findTopic(long id)
	 {
		 return entitymanager.find(Topics.class, id);
	 }
	 
	 @Override
	 @Transactional
	 public void deleteTopic(long id)
	 {
		 Topics t= entitymanager.find(Topics.class, id);
		 entitymanager.remove(t);
	 }

}
