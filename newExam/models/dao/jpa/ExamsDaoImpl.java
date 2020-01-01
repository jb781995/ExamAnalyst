package newExam.models.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import newExam.models.AnswerSheet;
import newExam.models.ExamQuestions;
import newExam.models.ExamStatus;
import newExam.models.TimeTable;
import newExam.models.dao.ExamsDao;

@Repository
public class ExamsDaoImpl implements ExamsDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public List<TimeTable> getTimeTableBySem(int sem)
	{
		return em.createNativeQuery("select * from time_table where sem_semester="+sem, TimeTable.class).getResultList();
	}
	
	@Override
	@Transactional
	public List<TimeTable> getTimeTableBySem2(int sem)
	{
		List<TimeTable> tt= em.createNativeQuery("select * from time_table where sem_semester="+sem, TimeTable.class).getResultList();
	
	 return tt.size()==0? null : tt;
	}
	
	@Override
	@Transactional
	public ExamQuestions saveThisQuestionObj(ExamQuestions eq)
	{
		return em.merge(eq);
	}
	
	@Override
	@Transactional
	public TimeTable getTimeTable(long id)
	{
//		return (TimeTable) em.createNativeQuery("select * from time_table where sub_id="+id,TimeTable.class);
		String query="select * from time_table where sub_id="+id;
		   List<TimeTable> tts=em.createNativeQuery(query, TimeTable.class).getResultList();	
		   return tts.size() == 0 ? null : tts.get( 0 );
	}
	
	@Override
	@Transactional
	public List<ExamQuestions> getPaper(long subId,int sem)
	{
		return em.createNativeQuery("select * from exam_question where subject_id="+subId+" AND semester="+sem+" AND exam_type LIKE 'Regular' ORDER BY RAND() LIMIT 25", ExamQuestions.class).getResultList();
	}
	
	@Override
	@Transactional
	public ExamQuestions getThisQuestion(long id)
	{
		return em.find(ExamQuestions.class, id);
	}
	
	@Override
	@Transactional
	public AnswerSheet saveThisAnswer(AnswerSheet as)
	{
		return em.merge(as);
	}
	
	@Override
	@Transactional
	public List<ExamQuestions> getQuestions(int semester, long subId, String examType)
	{
		return em.createNativeQuery("select * from exam_question where semester="+semester+" AND subject_id="+subId+" AND exam_type LIKE 'Regular'", ExamQuestions.class).getResultList();
	}
	
	@Override
	@Transactional
	public List<AnswerSheet> getAnswerOfThisStudent(int semester, long subId, long stuId)
	{
		return em.createNativeQuery("select * from anser_sheet where sem_semester="+semester+" AND sub_id="+subId+" AND stu_id="+stuId, AnswerSheet.class).getResultList();
	}
	
	@Override
	@Transactional
	public ExamStatus saveThisStudentStatus(ExamStatus es)
	{
		return em.merge(es);
	}
	
	@Override
	@Transactional
	public TimeTable getTypeOfExamByDate(Date d,int sem)
	{
		List<TimeTable>tts= em.createNativeQuery("select * from time_table where date="+d+" AND sem_semester="+sem,TimeTable.class).getResultList(); 
		 return tts.size() == 0 ? null : tts.get( 0 ); 
	}
	
	@Override
	@Transactional
	public ExamStatus returnExamStatus(long stuId,Date d,String type)
	{
		List<ExamStatus> es= em.createNativeQuery("select * from exam_status where stu_id="+stuId+" AND date="+d+" AND examType NOT LIKE 'Given'", ExamStatus.class).getResultList();
		return es.size() == 0 ? null : es.get( 0 ); 
	}
	
	@Override
	@Transactional
	public Object examValidationForThisStudent(long id)
	{
		List<AnswerSheet> aList=em.createNativeQuery("select * from anser_sheet where stu_id="+id, AnswerSheet.class).getResultList();
		return aList.size()==0? null: aList.get(0);
	}
	
	@Override
	@Transactional
	public List<ExamQuestions> getMyWorkTillDate(long subId, long myId)
	{
		return em.createNativeQuery("select * from exam_question where subject_id= "+subId+" AND faculty_id="+myId, ExamQuestions.class).getResultList();
	}
}
