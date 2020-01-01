package newExam.web.controllers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import newExam.models.AnswerSheet;
import newExam.models.ExamQuestions;
import newExam.models.FacultyProfile;
import newExam.models.FacultySubject;
import newExam.models.Semester;
import newExam.models.Statistics;
import newExam.models.StudentProfile;
import newExam.models.Subject;
import newExam.models.Topics;
import newExam.models.User;
import newExam.models.dao.CourseDao;
import newExam.models.dao.ExamsDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.StudentDao;
import newExam.models.dao.UserDao;

@Controller
@SessionAttributes("questionsList")
public class FacultyActionController {

	@Autowired
	private HttpSession session;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private FacultyDao facultyDao;

	@Autowired
	private StudentDao stuDao;

	@Autowired
	private ExamsDao examDao;
	
	@Autowired
	private UserDao userDao;

	// private FacultyProfile fp;
	// private Subject subject;
	private Semester sem;

	public int thisCounter = 1;
	public int localCounter = 0;
	ExamQuestions question;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();


	@RequestMapping(value = "setPaperForThisSub.html", method = RequestMethod.GET)
	public String goToSetPaperForThisSub(ModelMap model, @RequestParam long subId) {
		session.setAttribute("thisSub", courseDao.getThisSubject(subId));
		return "redirect:/SetQuestionPaper.html";
	}

	@RequestMapping("/SetQuestionPaper.html")
	public String setQuestionPaper(ModelMap model) {
		
		
		session.setAttribute("question", new ExamQuestions());
		session.setAttribute("counter", thisCounter);
		// thisCounter++;
		// model.put("thisQuestion", thisCounter);
		return "SetQuestionPaper";
	}

	@RequestMapping(value = "/saveAndNext.html")
	public String saveAndNext(@RequestParam Integer thisQNo, ModelMap model, HttpServletRequest req) {
		List<ExamQuestions> questionsList = (List<ExamQuestions>) session.getAttribute("questionsList");

		if (session.getAttribute("questionsList") == null) {
			questionsList = new ArrayList<ExamQuestions>();
			session.setAttribute("questionsList", questionsList);
		}

		int index = 1;
		if (session.getAttribute("index") == null) {
			session.setAttribute("index", index);
		} else {
			index = (Integer) session.getAttribute("index");
		}
		System.out.println("index before ADD or SET:" + index);

		boolean flag = true;
		ExamQuestions question = new ExamQuestions();
		for (int i = 0; i < questionsList.size(); i++) {
			if (i == (index - 1)) {
				flag = false;
				question = questionsList.get(i);
				break;
			}
		}

		if (flag) {
			// index++;
			questionsList.add(question);
			System.out.println("inside add ");

		}
		// index=(Integer)session.getAttribute("index");
		// System.out.println("index value before ADD or SET:"+index);
		// //if(questionsList.indexOf(question)!=index)
		// for(int i=0;i<=newQuestionsList.size();i++)
		// {
		// if(i==(index))
		// {
		// //questionsList.add(question);
		// newQuestionsList.set(index-1,question);
		// }
		// else
		// {
		// newQuestionsList.add(question);
		// //questionsList.set(index-1,question);
		// }
		// }

		User u = (User) session.getAttribute("faculty");
		Subject subject = (Subject) session.getAttribute("thisSub");

		String examQuestion = req.getParameter("question");
		String topicName = req.getParameter("topic");
		String correctAnswer = req.getParameter("correctOption");
		long subId = subject.getId();
		long fid = u.getFacultyProfile().getId();
		System.out.println("faculty id:" + fid);
		int semester = subject.getSem().getId();

		System.out.println("correct answer: " + correctAnswer);
		System.out.println("question: " + examQuestion);
		System.out.println("topic: " + topicName);

		question.setCorrectOption(correctAnswer);
		question.setQuestion(examQuestion);
		question.setTopic(topicName);
		question.setFacultyProfile(facultyDao.findThisFaculty(fid));
		question.setSubject(courseDao.getThisSubject(subId));
		question.setSem(courseDao.getThisSem(semester));

		// System.out.println("value of index before ADD or SET:" + index);

		System.out.println(
				"The Above question is saved on index:" + questionsList.lastIndexOf(question) + " of ArrayList");

		index++;
		session.setAttribute("index", index);

		System.out.println("index after add:" + index);

		req.getSession().setAttribute("questionsList", questionsList);
		System.out.println("saved in list");

		if (flag) {
			session.setAttribute("previousQuestion", new ExamQuestions());
		} else {
			if (index == (questionsList.size())) {
				session.setAttribute("previousQuestion", question);
			} else {
				session.setAttribute("previousQuestion", question);
			}
		}
		session.removeAttribute("previousQuestion");

		// thisCounter++;
		// localCounter++;
		return "redirect:/SetQuestionPaper.html";
	}

	@RequestMapping("/showWholeList.html")
	public String showQuestionsList() {
		return "redirect:/viewAllQuestions.html";
	}

	@RequestMapping(value = "/viewAllQuestions.html")
	public String showList(ModelMap model, HttpServletRequest req) {

		model.put("youReturnedFromQuestion", thisCounter);
		@SuppressWarnings("unchecked")
		List<ExamQuestions> list = (ArrayList<ExamQuestions>) req.getSession().getAttribute("questionsList");

		session.setAttribute("qlist", list);
		return "viewAllQuestions";
	}
	
	
	@RequestMapping("/viewAllQuestionsTillDate.html")
	public String myWorkTillDate()
	{
		Subject subject = (Subject) session.getAttribute("thisSub");
		User u = (User) session.getAttribute("faculty");
		long facId=u.getFacultyProfile().getId();
		long id=subject.getId();
		System.out.println("subId: "+id+" and facId: "+facId);
		session.setAttribute("workTillDay",examDao.getMyWorkTillDate(id, facId) );
		return "viewAllQuestionsTillDate";
	}

	@RequestMapping(value = "/showPrevious.html")
	public String showPrevious(ModelMap model, @RequestParam int currentQuestion) {
		List<ExamQuestions> questionsList2 = (ArrayList<ExamQuestions>) session.getAttribute("questionsList");
		int index = (Integer) session.getAttribute("index");

		System.out.println("current index from UI:" + index);
		// index=index-2;
		ExamQuestions eQ = questionsList2.get(index - 2);

		System.out.println("got this obj from index no:" + questionsList2.lastIndexOf(eQ));

		session.setAttribute("previousQuestion", eQ);
		session.setAttribute("index", --index);

		System.out.println("index after retreiving one object from list:" + index);

		return "redirect:/SetQuestionPaper.html";
	}

	@RequestMapping(value = "/returnFacultyProfile.html")
	public String saveTheseQuestionsInTable() {
		List<ExamQuestions> eq = (List<ExamQuestions>) session.getAttribute("questionsList");
		for (ExamQuestions eQ : eq) {
			eQ.setExamType("Regular");
			eQ = examDao.saveThisQuestionObj(eQ);
		}
		session.removeAttribute("index");
		session.removeAttribute("questionsList");
		return "redirect:/facultyProfile.html";
	}

	@RequestMapping(value = "/change.html", method = RequestMethod.POST)
	public String changeQue(@RequestParam String Que, @RequestParam String Option, @RequestParam String Topic,
			@RequestParam String QNo) {
		int qId = Integer.parseInt(QNo);
		System.out.println("questio id: "+qId);
		List<ExamQuestions> questionsList2 = (ArrayList<ExamQuestions>) session.getAttribute("questionsList");
		User u = (User) session.getAttribute("faculty");
		Subject subject = (Subject) session.getAttribute("thisSub");
		long subId = subject.getId();
		long fid = u.getFacultyProfile().getId();
		int semester = subject.getSem().getId();

		ExamQuestions eq = questionsList2.get(qId - 1);

		eq.setCorrectOption(Option);
		eq.setQuestion(Que);
		eq.setTopic(Topic);
		eq.setFacultyProfile(facultyDao.findThisFaculty(fid));
		eq.setSubject(courseDao.getThisSubject(subId));
		eq.setSem(courseDao.getThisSem(semester));
		questionsList2.set(qId - 1, eq);

		return "redirect:/viewAllQuestions.html";
	}

	@RequestMapping("/return.html")
	public String returnToQuestion(@RequestParam Integer qNo) {
		// thisCounter= thisCounter-qNo;
		return "redirect:/SetQuestionPaper.html";
	}

	@RequestMapping(value = "/logOut.html")
	public String logOutFaculty() {
		session.removeAttribute("faculty");
		return "redirect:/";
	}

	@RequestMapping(value = "/resultOfYourStu.html", method = RequestMethod.GET)
	public String goToStudentResultList(@RequestParam Integer sem, @RequestParam long subId) {

		session.setAttribute("subjectId", subId);
		session.setAttribute("semester", sem);

		return "redirect:/resultOfYourStudents.html";
	}

	@RequestMapping("/resultOfYourStudents.html")
	public String resultOfYourStudents() {
		int semester = (Integer) session.getAttribute("semester");
		session.setAttribute("studentsList", stuDao.getStudentOfThisSem(semester));
		return "resultOfYourStudents";

	}

	@RequestMapping(value = "/viewRep.html", method = RequestMethod.GET)
	public String goToViewReport(@RequestParam long stuId) {
		session.setAttribute("StuId", stuId);
		StudentProfile s = stuDao.getThisStudent(stuId);
		session.setAttribute("thisStudent", s);
		return "redirect:/viewReport.html";
	}

	@RequestMapping("/viewReport.html")
	public  String viewReport(HttpServletResponse res) throws IOException {
		
		System.out.println("inside view report");
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
		
		List<Statistics> stats= new ArrayList<>();
		Statistics st=new Statistics();
		//String currentTopic= new String();
		for(int i=0;i<topicList.size();i++)
{
			List<Integer> status= new ArrayList<Integer>();
			int correctAns=0;
			int wrongAns=0;
			int unAttemptedAns=0;
	
	 String currentTopic= topicList.get(i);
	System.out.println("current topic from topicList :"+currentTopic);
	
//	st.setTopicName("Topic");
//	st.setTopicName("Correct");
//	st.setTopicName("Wrong");
//	st.setTopicName("Unattempted");
//	
	
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
				
				
				
			}
			else 
			{
				unAttemptedAns=unAttemptedAns+1;
					}
			
			}
		
		    
		}
//	Statistics s= new Statistics();
//	s.setTopicName(currentTopic);
//	s.setCorrect(String.valueOf(correctAns));
//	s.setWrong(String.valueOf(wrongAns));
//	s.setUnAttempted(String.valueOf(unAttemptedAns));
//	stats.add(s);
//	stats.add(st);
	status.add(correctAns);
    status.add(wrongAns);
    status.add(unAttemptedAns);
	map.put(currentTopic, status);
    
	}
		
		
		
for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
String key = entry.getKey();
        List<Integer> values = entry.getValue();
    System.out.println(""+key+" :  ["+values+" ,]");
    }

//String mapAsJson = new ObjectMapper().writeValueAsString(stats);
//System.out.println(mapAsJson);
//objectMapper.writeValue(res.getWriter(),mapAsJson );
//
//session.setAttribute("jsonStats", mapAsJson);
        
  session.setAttribute("studentStats", map);
return "viewReport";
	}
	
	@RequestMapping("/getJsonOfMap2.html")
	public @ResponseBody String graphicalReport(HttpServletResponse res) throws IOException {
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
		
		List<Statistics> stats= new ArrayList<>();
		Statistics st=new Statistics();
		
		//String currentTopic= new String();
		for(int i=0;i<topicList.size();i++)
{
			List<Integer> status= new ArrayList<Integer>();
			int correctAns=0;
			int wrongAns=0;
			int unAttemptedAns=0;
	
	 String currentTopic= topicList.get(i);
	System.out.println("current topic from topicList :"+currentTopic);
	
	st.setTopicName("Topic");
	st.setTopicName("Correct");
	st.setTopicName("Wrong");
	st.setTopicName("Unattempted");
	//stats.add(st);
	
	
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
				
				
				
			}
			else 
			{
				unAttemptedAns=unAttemptedAns+1;
					}
			
			}
		
		    
		}
	Statistics s= new Statistics();
	s.setTopicName(currentTopic);
	s.setCorrect(String.valueOf(correctAns));
	s.setWrong(String.valueOf(wrongAns));
	s.setUnAttempted(String.valueOf(unAttemptedAns));
	stats.add(s);
	
	status.add(correctAns);
    status.add(wrongAns);
    status.add(unAttemptedAns);
	map.put(currentTopic, status);
    
	}
		
		
		
		


String mapAsJson = new ObjectMapper().writeValueAsString(stats);
System.out.println(mapAsJson);
objectMapper.writeValue(res.getWriter(),mapAsJson );

session.setAttribute("jsonStats", mapAsJson);
        
  

	return null;
	}
	
	@RequestMapping("/myTopics.html")
	public String myTopics()
	{
		 List<FacultySubject> fs1=(List<FacultySubject>)session.getAttribute("mySubjects");
		 
		 User u=(User)session.getAttribute("faculty");
		 long id=u.getId();
		 session.setAttribute("topicsListIHaveSet", facultyDao.getMyTopics(id));
		 session.setAttribute("setTopicForTheseSubjects", fs1);
		
		return "myTopics";
	}
	
	@RequestMapping(value="/tellMyTopics.html",method=RequestMethod.POST)
	public String setTopics(@RequestParam String id,@RequestParam String topic,@RequestParam String semester)
	{
		
		int sem=Integer.parseInt(semester);
		long subId= Long.parseLong(id);
		String topics=topic;
		User u=(User)session.getAttribute("faculty");
		long userId= u.getId();
		long facId=u.getFacultyProfile().getId();
		System.out.println("facId: "+facId);
		
		Topics t= new Topics();
		
		t.setFac(facultyDao.find(facId));
		t.setSub(courseDao.getThisSubject(subId));
		t.setUser(userDao.getThisUser(userId));
		t.setSem(sem);
		t.setTopics(topics);
		t=facultyDao.save(t);
		
		return "redirect:/myTopics.html";
	}
	
	@RequestMapping("/editTopics.html")
	public String goToEdit()
	{
		User u=(User)session.getAttribute("faculty");
		long id=u.getId();
		session.setAttribute("topicsListIHaveSet", facultyDao.getMyTopics(id));
		return "editTopics";
	}
	
  @RequestMapping(value="/editMyTopics.html",method=RequestMethod.POST)
  public String edit(@RequestParam String topic,@RequestParam String thisObj)
  {
	  
	  long id=Long.parseLong(thisObj);
	  System.out.println("id of this topic obj: "+id);
	  Topics t=facultyDao.findTopic(id);
	  t.setTopics(topic);
	  t=facultyDao.save(t);
	  return "redirect:/editTopics.html";
  }
  
  
  @RequestMapping(value="/deleteMyTopics.html",method=RequestMethod.POST)
  public String deleteTopic(@RequestParam String Obj)
  {
	  long id= Long.parseLong(Obj);
	  facultyDao.deleteTopic(id);
	  
	  return "redirect:/editTopics.html";
  }
}
