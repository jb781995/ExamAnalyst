package newExam.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import newExam.models.FacultyProfile;
import newExam.models.FacultySubject;
import newExam.models.StudentProfile;
import newExam.models.Subject;
import newExam.models.TermDate;
import newExam.models.User;
import newExam.models.dao.AdminDao;
import newExam.models.dao.CourseDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.FacultySubjectDao;
import newExam.models.dao.StudentDao;
import newExam.models.dao.UserDao;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
//@SessionAttributes("fpFacultyObj")
@SessionAttributes("facultyObj")
public class AuthorityActionController {
	
	
	 @Autowired
	 private HttpSession session;
	 
//	 @Autowired
//	 private SessionStatus sessionStatus;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private StudentDao studentDao;
	
	
	@Autowired
	private AdminDao adminDao;
	
	
	@Autowired
	private FacultySubjectDao facultySubjectDao;
	
	@Autowired
	private CourseDao courseDao;
	
	User user;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	@RequestMapping(value="/addFaculty.html",method=RequestMethod.GET)
	public String addFaculty(@RequestParam long id,ModelMap model)
	{
		//session.setAttribute("id", id);
		model.put("faculty", new FacultyProfile());
		System.out.println("a");
		
		session.setAttribute("thisUser", userDao.getThisUser(id));
		System.out.println("b");
		
		
		session.setAttribute("semester", courseDao.getSemesters());
		System.out.println("c");
		//model.put("subjects", );
		//System.out.println("d");
		
		return "addFaculty";
	}
	
	@RequestMapping(value="/showSubject.html",method=RequestMethod.GET)
	public String showSubjects(@RequestParam Integer semester,@RequestParam long id,ModelMap model)
	{
		List<Subject> subjects=courseDao.getSubjects(semester);
		session.setAttribute("subjects", subjects);
		System.out.println("d");
		return "redirect:/addFaculty.html?id="+id;
		
	}

	
	
	@RequestMapping(value="/addFaculty.html",method=RequestMethod.POST)
	public String addFaculty(@ModelAttribute FacultyProfile faculty,ModelMap model)
	{
		
		
		long id=faculty.getId();
		User u=facultyDao.saveTypeAsFaculty(id);
		faculty.setFacUser(u);
		faculty=facultyDao.saveFaculty(faculty);
		
		
		
		System.out.println("this faculty is saved as faculty type in user");
		
		return "redirect:/reviewer.html";
	}
	
	
	@RequestMapping(value="/addStudent.html",method=RequestMethod.GET)
	String addStudent(@RequestParam long id,ModelMap model)
	{
		model.put("student", new StudentProfile());
		session.setAttribute("thisUser", userDao.getThisUser(id));
		System.out.println("b");
		
		return "addStudent";
		
	}
	
	@RequestMapping(value="/addStudent.html",method=RequestMethod.POST)
	String addStudent(@ModelAttribute StudentProfile student,ModelMap model)
	{
		
	
	
		long id=student.getId();
		User u=studentDao.saveTypeAsStudent(id);
		
		student.setStuUser(u);
		student=studentDao.saveStudent(student);
		System.out.println("this student is saved as student type in user");
		return "redirect:/reviewer.html";
		
	}
	
	
	
//	@RequestMapping(value="/thisSemester.html",method=RequestMethod.GET)
//	 public String displaySubject(@RequestParam Integer sem,@RequestParam long id,ModelMap model)
//	 {
//		System.out.println("hi");
//		
//		model.addAttribute("subjectsList", courseDao.getSubjects(sem));
//		System.out.println("going to assign subjects to this faculty");
//	    return "redirect:/assignSubject.html?id="+id;
//	 }
	
	@RequestMapping(value="/getSubjectBySemester.html",method=RequestMethod.GET)
	public @ResponseBody List<Subject> getSubjects(@RequestParam("sem")Integer sem,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException
	{
	List<Subject> lstsubjects	= courseDao.getSubjects(sem);
		response.setContentType("application/json");
		objectMapper.writeValue(response.getWriter(), lstsubjects);
		return null;
		
	}
	
	@RequestMapping(value="/savesubjects.html",method=RequestMethod.POST)
	public @ResponseBody String savesubjects(@RequestParam("subjects")Integer sem,HttpServletResponse response,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException
	{
		String subject =request.getParameter(sem.toString());	
		Subject s =new Subject();
		s.setSubjectName(subject);
		FacultyProfile f= new FacultyProfile();
	 f.setFacId(sem);
	 List<Subject> lstsub=new ArrayList<Subject>();
	 lstsub.add(s);
	// f.setSubjects(lstsub);
	 
	 facultyDao.saveFacultySubject(f);
		
		response.setContentType("application/json");
		objectMapper.writeValue(response.getWriter(), "sucess");
		return null;
		
	}
	
	@RequestMapping(value="/assignSub.html",method=RequestMethod.GET)
	 public String assignSubject(@RequestParam long id,ModelMap model)
	 {
		
		
		session.setAttribute("userObj", userDao.getThisUser(id));
		//session.setAttribute("facultyObj", facultyDao.findThisFaculty(id));
		

		session.setAttribute("semesters", courseDao.getSemesters());
		System.out.println("going to assign subjects");
		
		return "assignSubject2";
	 }
	
	@RequestMapping(value="/assignSubject2.html",method=RequestMethod.GET)
	public String assignSubjectToFaculty(ModelMap model,@RequestParam long id)
	{
		//model.put("assignSubjectToFaculty", new FacultySubject());
//		session.setAttribute("facultyObj", userDao.getThisUser(id));
		
		return "assignSubject2";
	}
	@RequestMapping(value="/assignSubject2.html",method=RequestMethod.POST)
	public String assignSubjectToFaculty(@ModelAttribute FacultySubject assignSubjectToFaculty )
	{
		//model.put("assignSubjectToFaculty", new FacultySubject());
		
		return "redirect:/reviewer.html";
	}
	
	
	@RequestMapping(value="/hiddenForm.html",method=RequestMethod.POST)
	public String saveSubject(@RequestParam String hiddenField,HttpSession session)
	{
		User u=(User) session.getAttribute("userObj");
		FacultyProfile f=(FacultyProfile) session.getAttribute("facultyObj");
		long uId=u.getId();
		
		
				
List<Subject> lstsubject = new ArrayList<Subject>();

		String semList[]=hiddenField.split(",");
		for(String s:semList)
		{
			FacultySubject fs=new FacultySubject();
			System.out.println(s);
			long slong =Long.valueOf((String)s).longValue();
			//fs.setLstfaculty(f);
			
			fs.setLstfaculty(facultyDao.findThisFaculty(uId));
			
			 fs.setUser(userDao.getThisUser(uId));  
			
		    fs.setLstsubject(courseDao.getThisSubject(slong));
		    
		    FacultySubject	freturns=facultySubjectDao.saveSubjectDetails(fs);
		    long idForAssigned= freturns.getId();
		    freturns=facultySubjectDao.assignedSubjectToThisFaculty(idForAssigned);
		    
		   }
		
		return "redirect:/reviewer.html";
	}
	
	
	@RequestMapping(value="unAssign.html",method=RequestMethod.GET)
	public String unAssignSub(ModelMap model,@RequestParam long id)
	{
		facultySubjectDao.unAssign(id);
		return "redirect:/reviewer.html";
	}
	
	@RequestMapping (value="/startEndDate.html")
	public String set(HttpServletRequest req) throws ParseException
	{
		
		String sem= req.getParameter("sem");
		int semester= Integer.parseInt(sem);
		String from= req.getParameter("fromD");
		String to=req.getParameter("toD");
		 System.out.println("sem: "+sem+" || from :" +from+" || to :"+to);
		 
		 TermDate td2= new TermDate();
		 
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		 Date from1=df.parse(from);
		 Date to1= df.parse(to);
		 
		 td2.setFromD(from1);
		 td2.setToD(to1);
		 td2.setSemester(semester);
		 td2.setStatus("NotMoved");
		 td2= adminDao.save(td2);
		 
		 
		 
	  
		return "redirect:/reviewer2.html";
	}
	
	
	@RequestMapping(value="/editTermDate.html", method=RequestMethod.POST)
	public String editTermDate(@RequestParam String sem,@RequestParam String from ,@RequestParam String to,@RequestParam String id) throws ParseException
	{
		long id2=Long.parseLong(id);
		int semester= Integer.parseInt(sem);
		TermDate td= adminDao.find(id2);
		String frmDate=from;
		String toDate=to;
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		 
		 Date from1= df.parse(frmDate);
		 Date to1= df.parse(toDate);
		
		td.setFromD(from1);
		td.setToD(to1);
		td.setSemester(semester);
		td= adminDao.save(td);
		
		
		return "redirect:/reviewer2.html";
	}
}
