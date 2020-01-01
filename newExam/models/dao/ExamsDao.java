package newExam.models.dao;

import java.util.Date;
import java.util.List;

import newExam.models.AnswerSheet;
import newExam.models.ExamQuestions;
import newExam.models.ExamStatus;
import newExam.models.TimeTable;

public interface ExamsDao {
	
	List<TimeTable> getTimeTableBySem(int sem);
	List<TimeTable> getTimeTableBySem2(int sem);
	
	
	ExamQuestions saveThisQuestionObj(ExamQuestions eq);
	
	TimeTable getTimeTable(long id);
	
	List<ExamQuestions> getPaper(long subId,int sem);
	List<ExamQuestions> getMyWorkTillDate(long subId, long myId);
	
	ExamQuestions getThisQuestion(long id);
	
	AnswerSheet saveThisAnswer(AnswerSheet as);
	
	List<ExamQuestions> getQuestions(int semester, long subId , String examType);
	List<AnswerSheet> getAnswerOfThisStudent(int semester, long subId, long stuId);

	ExamStatus saveThisStudentStatus(ExamStatus es);
	
	TimeTable getTypeOfExamByDate(Date d,int sem);
	
	ExamStatus returnExamStatus(long stuId,Date d,String type);
	
	Object examValidationForThisStudent(long id);
}
