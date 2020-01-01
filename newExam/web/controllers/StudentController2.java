package newExam.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import newExam.models.AnswerSheet;
import newExam.models.ExamQuestions;
import newExam.models.ResultClass;
import newExam.models.Statistics;
import newExam.models.Subject;
import newExam.models.dao.CourseDao;
import newExam.models.dao.ExamsDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.FacultySubjectDao;
import newExam.models.dao.StudentDao;
import newExam.models.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class StudentController2 {
	
	@Autowired
	private HttpSession session;
	
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
	 private static final ObjectMapper objectMapper = new ObjectMapper();

	
	@RequestMapping("/demo2.html")
	public String demo2()
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
		 
		 return "demo2";
	}

	
	@RequestMapping(value="/getNextQuestion.html",method=RequestMethod.GET)
	public  @ResponseBody String nextQuestion(@RequestParam Integer qNo,@RequestParam String ans,HttpServletResponse response)throws JsonGenerationException, JsonMappingException, IOException
	{
		 List<ExamQuestions> qList= (List<ExamQuestions>)session.getAttribute("questionPaper");
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
		 ExamQuestions eq= new ExamQuestions();
	
	int index=qNo;
	System.out.println("index inside nextFunction:" +index);
	for(int i=0;i<=qList.size();i++)
	 {
		 if(i==(index-1))
		 {
		     eq= qList.get(i);
			 session.setAttribute("thisQuestion", eq);
			 break;
		 }
	 }
	
	AnswerSheet as= new AnswerSheet();
	boolean flag=true;
	//index=(Integer)session.getAttribute("index");
	int index2=ansList.size();
	String nextans="";
	System.out.println("Answer List Size:"+ansList.size());
	System.out.println("before adding first element: "+ansList.size());
	for(int i=0;i<ansList.size();i++)
	{
		if(i==(index-1))
		{
			flag=false;
			as= ansList.get(i-1);
			AnswerSheet nextas=ansList.get(i);
			System.out.println(as.getExamQue());
			System.out.println(as.getStudentAnswer());
			System.out.println("next answer is :"+nextas.getStudentAnswer());
//			String stuAns= ans;
//			System.out.println("answer:" +stuAns);
//			as.setExamQue(examDao.getThisQuestion(qNo));
//			 as.setSem(courseDao.getThisSem(semester));
//			 as.setStudentAnswer(stuAns);
//			 as.setStuProfile(stuDao.getThisStudent(stuId));
//			 as.setSubId(courseDao.getThisSubject(subId));
			 
			nextans=nextas.getStudentAnswer();
			System.out.println("index in case of update:"+ ansList.lastIndexOf(as));
			break;
		}
	}
	
	if(flag)
	{
		System.out.println("ans:" +ans);
		 as.setExamQue(examDao.getThisQuestion(qNo));
		 as.setSem(courseDao.getThisSem(semester));
		 as.setStudentAnswer(ans);
		 as.setStuProfile(stuDao.getThisStudent(stuId));
		 as.setSubId(courseDao.getThisSubject(subId));
		 
		ansList.add(as);
		System.out.println("index after add: "+ansList.lastIndexOf(as));
	}
	
	 
	 //String stuAns= req.getParameter("correctOption");
	 String stuAns= ans;
	 System.out.println("stu ans:" + ans);
	 as.setExamQue(examDao.getThisQuestion(qNo));
	 as.setSem(courseDao.getThisSem(semester));
 as.setStudentAnswer(stuAns);
	 as.setStuProfile(stuDao.getThisStudent(stuId));
	 as.setSubId(courseDao.getThisSubject(subId));
	 
	 
	 
	 //ansList.add(as);
	index++;
	System.out.println("next");
	
	String question= eq.getQuestion();
	List<String> list= new ArrayList<String>();
	list.add(question);
	list.add(nextans);
	
			session.setAttribute("index", index);
			session.setAttribute("stuAnswerList",ansList );
	response.setContentType("application/json");
	objectMapper.writeValue(response.getWriter(), list);
		return null;	 
	}
	
	
	@RequestMapping(value="/getPreviousQuestion.html", method=RequestMethod.GET)
	public @ResponseBody String previousQue(@RequestParam Integer qNo,HttpServletResponse response)throws JsonGenerationException, JsonMappingException, IOException
	{
		int index=(Integer)session.getAttribute("index");
		index=qNo;
		System.out.println("at start of previous: index="+index);
		List<ExamQuestions> qList= (List<ExamQuestions>)session.getAttribute("questionPaper");
		 List<AnswerSheet> ansList=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
		 
		
		 
		 ExamQuestions eq= qList.get(index-2);
		 AnswerSheet as=ansList.get(index-2);
		 System.out.println("got this object from:"+ansList.lastIndexOf(as));
		 System.out.println("stu answer: "+as.getStudentAnswer());
		 
	
		 
	
			 session.setAttribute("thisQuestion", eq);
		
	index=index-2;
	
	String question= eq.getQuestion();
	String answer= as.getStudentAnswer();
	List<String> rc= new ArrayList<String>();
	
     rc.add(question);
     rc.add(answer);
	
			session.setAttribute("index", index);
			System.out.println("index after previous: "+index);			//session.setAttribute("prevQue", as);
	response.setContentType("application/json");
	objectMapper.writeValue(response.getWriter(), rc);
		return null;	
	}
	
	
	@RequestMapping(value="/paletteQuestion.html", method=RequestMethod.GET)
	public @ResponseBody String paletteQue(@RequestParam Integer qNo,@RequestParam String ans,HttpServletResponse response)throws JsonGenerationException, JsonMappingException, IOException
	{
		
		 long stuId=(Long)session.getAttribute("stuId");
		 long subId=(Long)session.getAttribute("validSubId");
		 Subject sub=(Subject)session.getAttribute("subject");
		 int semester = sub.getSem().getId();
		 
		 
		int index=(Integer)session.getAttribute("index");
		index=qNo;
		System.out.println("this qno: "+index);
		List<ExamQuestions> qList= (List<ExamQuestions>)session.getAttribute("questionPaper");
		 List<AnswerSheet> ansList=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
		 
		ExamQuestions eq= qList.get(index-1);
		//AnswerSheet as=ansList.get(index-1);
		
		 AnswerSheet as2= new AnswerSheet();
		 boolean flag=true;
		 
		 for(int i=0;i<ansList.size();i++)
			{
				if(i==(index-1))
				{
					flag=false;
					as2= ansList.get(i);
					//AnswerSheet nextas=ansList.get(i);
					/*System.out.println(as.getExamQue());
					System.out.println(as.getStudentAnswer());
					System.out.println("next answer is :"+nextas.getStudentAnswer());
					nextans=nextas.getStudentAnswer();
					System.out.println("index in case of update:"+ ansList.lastIndexOf(as));*/
					break;
				}
			}
		 
		 if(flag)
			{
				 as2.setExamQue(examDao.getThisQuestion(qNo));
				 as2.setSem(courseDao.getThisSem(semester));
				 as2.setStudentAnswer(ans);
				 as2.setStuProfile(stuDao.getThisStudent(stuId));
				 as2.setSubId(courseDao.getThisSubject(subId));
				 
				ansList.add(as2);
				System.out.println("index after add: "+ansList.lastIndexOf(as2));
			}
		
		session.setAttribute("thisQuestion", eq);
		
		System.out.println("this question: "+eq.getQuestion());
		index--;
		
		String question= eq.getQuestion();
		String answer= as2.getStudentAnswer();
		System.out.println("this question :" +question+" and its answer: "+answer);
		List<String> rc= new ArrayList<String>();
		
		rc.add(question);
		rc.add(answer);
		
		session.setAttribute("index", index);
		
		response.setContentType("application/json");
		objectMapper.writeValue(response.getWriter(), rc);
		return null;
	}
	
	@RequestMapping("/getJsonOfMap.html")
	public @ResponseBody String jsonMap(HttpServletResponse response)throws JsonGenerationException, JsonMappingException, IOException
	{
		long stuId=(Long)session.getAttribute("StuId");
		int semester=(Integer)session.getAttribute("semester");
		long subId=(Long)session.getAttribute("subjectId");
		String type="Regular"; 
		List<ExamQuestions> eq = examDao.getQuestions(semester, subId,type);
		List<AnswerSheet> as = examDao.getAnswerOfThisStudent(semester, subId, stuId);
		List<String> topicList = new ArrayList<String>();
		List<String> duplicates= new ArrayList<String>();
		List<String> junk= new ArrayList<String>();
		
		for(ExamQuestions eq2:eq)
		{
			String topic=eq2.getTopic();
			junk.add(topic);
		}
		
		for(String topic:junk)
		{
			if(topicList.contains(topic))
			{
				duplicates.add(topic);
			}
			else
			{
				topicList.add(topic);
			}
		}
	
		
		
		System.out.println("after for loop");
		
		for(String x:topicList)
		{
			System.out.println("topic name: "+x);
		}
		
		
		Map<String,List<Integer>> map= new HashMap<String,List<Integer>>();
		
		
		//String currentTopic= new String();
		for(int i=0;i<topicList.size();i++)
{
			List<Integer> status= new ArrayList<Integer>();
			int correctAns=0;
			int wrongAns=0;
			int unAttemptedAns=0;
	
	 String currentTopic= topicList.get(i);
	System.out.println("current topic from topicList :"+currentTopic);
	
	for(AnswerSheet as3:as)
	{
		String stuAnsTopic= as3.getExamQue().getTopic();
		if(currentTopic.equals(stuAnsTopic))
		{
			String ansStatus=as3.getStatus();
			if(ansStatus.equals("correct"))
			{
				correctAns=correctAns+1;
				
				//status.add(correctAns);
				//correctAns++;
				
			}
			else if(ansStatus.equals("wrong"))
			{
				wrongAns=wrongAns+1;
				
				//status.add(wrongAns);
				//wrongAns++;
				
			}
			else 
			{
				unAttemptedAns=unAttemptedAns+1;
				
				//status.add(unAttemptedAns);
				//unAttemptedAns++;
				
			}
			
			}
		
		    
		}
	
	
	status.add(correctAns);
    status.add(wrongAns);
    status.add(unAttemptedAns);
	map.put(currentTopic, status);
    
	}
		
		
for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {

    String key = entry.getKey();
    //statistics.setTopicName(key);

    List<Integer> values = entry.getValue();
     //System.out.println("String output: "+values.toString());

    System.out.println("Key = " + key);

    System.out.println("Values = " + values + "n");

		
	}

 
String mapAsJson = new ObjectMapper().writeValueAsString(map);
System.out.println(mapAsJson);
objectMapper.writeValue(response.getWriter(),mapAsJson );


		return null;
	}
	
	 @RequestMapping("/showAnsWholeListt.html")
	 public String goTo2()
	 {
		 List<AnswerSheet> alist=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
	 session.setAttribute("answersList", alist);
		 return "demoStudentAnswerList";
	 }
	 
//	 @RequestMapping("/demoStudentAnswerList2.html")
//	 public String goToThisPage2()
//	 {
//		 List<AnswerSheet> alist=(List<AnswerSheet>)session.getAttribute("stuAnswerList");
//		 session.setAttribute("answersList", alist);
//		 return "demoStudentAnswerList";
//	 }
	
	
	
	@RequestMapping(value="/logOutt.html")
	public String logout()
	{
		session.invalidate();
		
		
		return "redirect:/";		
	}
	
	
	

    }
