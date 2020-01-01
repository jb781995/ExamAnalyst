package newExam.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.FacultyProfile;
import newExam.models.StudentProfile;
//import newExam.models.Faculty;
import newExam.models.User;
import newExam.models.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
    public User getUser( String email )
    {
        String query = "from User user left join fetch user.roles "
            + "where lower(email) = :email";

        List<User> users = entitymanager.createQuery( query, User.class )
            .setParameter( "email", email.toLowerCase() )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }
	 
	
	@Override
	 @Transactional
	public List<User> getAllUsers()
	{
		//String type="";
		return entitymanager.createNativeQuery("select * from user where type IS NULL",User.class).getResultList();
		
	}
	
	
	
	@Override
	 @Transactional
	 public User getThisUser(long id)
	 {
		
		return entitymanager.find( User.class,id);
	 }
	
	
	
	 

	 
	
	 
	 
	
	
	 @Override
	 @Transactional
	public User saveUser(User user)
	{
		return entitymanager.merge(user);
	}
	 

}
