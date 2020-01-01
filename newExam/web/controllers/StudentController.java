package newExam.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import newExam.models.AnswerSheet;
import newExam.models.ExamQuestions;
import newExam.models.ExamStatus;
import newExam.models.Subject;
import newExam.models.TimeTable;
import newExam.models.dao.CourseDao;
import newExam.models.dao.ExamsDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.FacultySubjectDao;
import newExam.models.dao.StudentDao;
import newExam.models.dao.UserDao;

@Controller
public class StudentController {
	
//	@RequestMapping(value="/giveExam.html")
//	public String giveExam()
//	{
//		return "giveExam";
//	}
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private FacultySubjectDao facultySubDao;
	
	@Autowired
	private ExamsDao examDao;
	
	@Autowired
	private StudentDao stuDao;
	
	
	
	 @Autowired
	 private HttpServletRequest req;

	
	@Autowired
	 private HttpSession session;

	@RequestMapping("/giveExam.html")
	 public String demo()
	 {
		 long subId= (Long)session.getAttribute("validSubId");
		 TimeTable tt= (TimeTable)session.getAttribute("particularSubDate");
		 int sem = tt.getSem().getSemester();
		 
		 session.setAttribute("questionPaper", examDao.getPaper(subId, sem));
		 System.out.println("ID of Sub for which exam is to be taken today:"+subId);
		 return "giveExam";
	 }
	 
	 @RequestMapping("/beginExam.html")
	 public String goToExamPaper()
	 {
		 return "redirect:/demo2.html";
	 }
	 
	 @RequestMapping("/demo.html")
	 public String displayPaper()
	 {
		 
		 session.removeAttribute("thisQuestion");
		 
		 List<ExamQuestions> qList= (List<ExamQuestions>)session.getAttribute("questionPaper");
		 int size= qList.size();
		 System.out.println("total number of questions for this subject :"+size);
		 session.setAttribute("TotalQuestions", size);
		 int index=1;
		 if(index>=qList.size())
		 {
			 return "redirect:/demoStudentAnswerList.html";
		 }
		 else
		 {
		 if(session.getAttribute("index")==null)
		 {
			 session.setAttribute("index", index);
		 }
		 else {
				index = (Integer) session.getAttribute("index");
			}
		 System.out.println("current value of index:"+index);
		 boolean flag=true;
		 AnswerSheet as= new AnswerSheet();
		 for(int i=0;i<=qList.size();i++)
		 {
			 if(i==(index-1))
			 {
				//as=qList.get(i);
				 ExamQuestions eq= qList.get(i);
				 session.setAttribute("thisQuestion", eq);
				 break;
			 }
		 }
		 }
		 
		 
		 
		 return "demo";
	 }
	 
	 @RequestMapping(value="/saveAndNextStu.html", method=RequestMethod.POST)
	 public String gotToNextQuestion()
	 {
		 List<ExamQuestions>eq=(List<ExamQuestions>)session.getAttribute("questionPaper");
		 List<AnswerSheet> ansList=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
		 if(session.getAttribute("stuAnswerList")==null)
		 {
			 ansList=new ArrayList<AnswerSheet>();
		     session.setAttribute("stuAnswerList", ansList);
		 }
		 
		 long stuId=(Long)session.getAttribute("stuId");
		 long subId=(Long)session.getAttribute("validSubId");
		 Subject sub=(Subject)session.getAttribute("subject");
		 int semester = sub.getSem().getId();
		 //int index=1;
		 int index2=(Integer)session.getAttribute("index");
		 System.out.println("what is index2 inside save and next ? :"+index2);
		 if(session.getAttribute("index")==null)
		 {
			 session.setAttribute("index", index2);
		 }else {
				index2 = (Integer) session.getAttribute("index");
			}
			
		 boolean flag=true;
		 AnswerSheet as= new AnswerSheet();
		 for(int i=0;i<ansList.size();i++)
		 {
			 if(i==(index2-1))
			 {
				 flag=false;
				 as=ansList.get(i);
				 break;
			 }
		 }
		 
		 if(flag)
		 {
			 ansList.add(as);
			 System.out.println("inside Add");
		 }
		
		 
		 
		 long queId=index2;
		 //index2++;
		 String stuAns= req.getParameter("correctOption");
		 System.out.println("this is what student typed:" +stuAns);
		 
		 
		 as.setExamQue(examDao.getThisQuestion(queId));
		 as.setSem(courseDao.getThisSem(semester));
		 as.setStudentAnswer(stuAns);
		 as.setStuProfile(stuDao.getThisStudent(stuId));
		 as.setSubId(courseDao.getThisSubject(subId));
		 
		 if(index2>=eq.size())
		 {
			 return "redirect:/demoStudentAnswerList.html";
		 }
		 
		 
		 session.setAttribute("index", ++index2);
		 System.out.println("i have incremented value of index:" +index2);
		 session.removeAttribute("previousQuestion");
		 
		 return "redirect:/demo.html";
	 }
	 
	 @RequestMapping("/showPreviousQ.html")
	 public String showPrevious()
	 {
		 int index=(Integer)session.getAttribute("index");
		 List<AnswerSheet> ansList=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
		 AnswerSheet as=ansList.get(index-2);
		 System.out.println("got this object from:"+ansList.lastIndexOf(as));
		 session.setAttribute("previousQuestion", as);
		 session.setAttribute("index", --index);
		 System.out.println("index after retreiving one object from list:"+index);
			
		 return "redirect:/demo.html";
	 }
	 
	 @RequestMapping("/showAnsWholeList.html")
	 public String goTo()
	 {
		 return "redirect:/demoStudentAnswerList.html";
	 }
	 
	 @RequestMapping("/demoStudentAnswerList.html")
	 public String goToThisPage()
	 {
		 List<AnswerSheet> alist=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
		 session.setAttribute("answersList", alist);
		 return "demoStudentAnswerList";
	 }
	 
	 @RequestMapping("/returnStudentProfile.html")
	 public String saveStuAns()
	 {
		 List<ExamQuestions> eq= (List<ExamQuestions>)session.getAttribute("questionPaper");
		 List<AnswerSheet> as= (List<AnswerSheet>)session.getAttribute("stuAnswerList");
	 
	     for(AnswerSheet as2:as)
	     {
	    	 for(ExamQuestions eq2:eq)
	    	 {
	    		 String actualAns2=as2.getExamQue().getCorrectOption();
	    		 String actualAns= eq2.getCorrectOption();
	    		 String stuAns=as2.getStudentAnswer();
	    		 if(actualAns.equals(stuAns))
	    		 {
	    			 as2.setStatus("correct");
	    			 as2=examDao.saveThisAnswer(as2);
	    		break;	 
	    		 }
	    		 else if(stuAns.equals(""))
	    		 {
	    			 as2.setStatus("unAttempted");
	    			 as2=examDao.saveThisAnswer(as2);
	    			 break;
	    		 }
	    		 else
	    		 {
	    			 as2.setStatus("wrong");
	    			 as2=examDao.saveThisAnswer(as2);
	    			 break;
	    		 }
	    	 }
	     }
		 
		 ExamStatus es= new ExamStatus();
		 //Subject sub= new Subject();
		 Subject sub=(Subject)session.getAttribute("yourTodaysSub");
		 long id=(Long)session.getAttribute("stuId");
		 long subId=(Long)session.getAttribute("validSubId");
		 //System.out.println(sub.getSem());
		 int semester = sub.getSem().getId();
		 Date date=(Date)session.getAttribute("todaysDate");

		 es.setStuId(stuDao.getThisStudent(id));
		 es.setSubId(courseDao.getThisSubject(subId));
		es.setSem(courseDao.getThisSem(semester));
         es.setDate(date);
         es.setExamType("Regular");
         es.setStatus("Given");
         es=examDao.saveThisStudentStatus(es);
	     
	     session.removeAttribute("index");
	     
	     session.removeAttribute("questionPaper");
	     session.removeAttribute("stuAnswerList");
	     return "redirect:/studentProfile.html";
	 }
	 
	 @RequestMapping("/goBack.html")
	 public String goToStudentProfile()
	 {
		 session.removeAttribute("index");
		 session.removeAttribute("questionPaper");
		 session.removeAttribute("thisQuestion");
		 return "redirect:/studentProfile.html";
	 }

}
