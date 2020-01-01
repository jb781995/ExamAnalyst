package newExam.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import newExam.models.ExamQuestions;
import newExam.models.FacultyProfile;
import newExam.models.Semester;
import newExam.models.Subject;
import newExam.models.dao.CourseDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.FacultySubjectDao;

@Controller
public class ExamController {
	
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private FacultySubjectDao facultySubDao;
	
	
	@Autowired
	 private HttpServletRequest request;
	 
	 @Autowired
	 private HttpSession session;
	 
//	 private FacultyProfile fp;
//	 private Subject subject;
//	 private Semester sem;
//	 
//    @RequestMapping(value="/saveAndNext.html")
//    public String saveAndNext(@ModelAttribute ExamQuestions question)
//    {
//    	question=(ExamQuestions)session.getAttribute("question");
//    	fp=(FacultyProfile)session.getAttribute("faculty");
//    	subject=(Subject)session.getAttribute("thisSub");
//    	return "redirect:/SetQuestionPaper.html";
    //}
	 
//	 @RequestMapping("/showWholeList.html")
//	 public String showQuestionsList()
//	 {
//		 return "redirect:/viewAllQuestions.html";
//	 }
//	 
//	 @RequestMapping(value="/viewAllQuestions.html")
//	 public String showList(ModelMap model)
//	 {
//		 @SuppressWarnings("unchecked")
//		List<ExamQuestions> list= (List<ExamQuestions>)session.getAttribute("questionsList");
//		 model.put("qlist", list);
//		 return "viewAllQuestions";
//	 }
	 
}
