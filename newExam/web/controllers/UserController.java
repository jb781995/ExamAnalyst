package newExam.web.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import newExam.models.FacultyProfile;
import newExam.models.FacultySubject;
import newExam.models.StudentProfile;
import newExam.models.Subject;
import newExam.models.TermDate;
import newExam.models.TimeTable;
import newExam.models.Topics;
import newExam.models.User;
import newExam.models.dao.AdminDao;
import newExam.models.dao.CourseDao;
import newExam.models.dao.ExamsDao;
import newExam.models.dao.FacultyDao;
import newExam.models.dao.FacultySubjectDao;
import newExam.models.dao.StudentDao;
import newExam.models.dao.UserDao;

@Controller
public class UserController {
	
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
	private AdminDao adminDao;
	
	
	
	 @Autowired
	 private HttpServletRequest request;
	 
	 @Autowired
	 private HttpSession session;
	 
	 User user;
	
	@RequestMapping("index.html")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping(value="register.html",method=RequestMethod.GET)
	public String register(ModelMap model)
	{
		model.put("user", new User());
		return "register";
	}

	@RequestMapping(value="register.html",method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute User user,ModelMap model,BindingResult br)
	{
//		if(br.hasErrors())
//		{
//			return "register";
//		}
		
	user =userDao.saveUser(user);
	System.out.println("user is saved in db");
	return "redirect:/index.html";
    }

	
	@RequestMapping("login.html")
	 public String login()
	 {
		 return "login";
	 }
	 
	 @RequestMapping(value="login.html",method=RequestMethod.POST)
	 public String loginPost()
	 {
		 String email=request.getParameter("email");
		 String password=request.getParameter("password");
		 User u= userDao.getUser(email);
		 
		 session.setAttribute("user", u);
		 System.out.println("got type");
		 try
		 {
		 if(u!=null)
		 {
			 String type=u.getType();
			 u.setType(type);
		  if(u.isAdmin())
		 {
			 session.setAttribute("adminUser", u);
			System.out.println("admin login");
		    return "redirect:/admin.html";
		}
		 
		 else if(u.isReviewer())
		 {
			 session.setAttribute("reviewerUser", u);
			System.out.println("reviewer login");
	        return "redirect:/reviewer.html";
		 }
		 else if(u.isReviewer2())
		 {
			 session.setAttribute("reviewerUser2", u);
			 System.out.println("scarlett login :");
			 return "redirect:/reviewer2.html";
		 }
		 else if (u.isStudent())
		 {
			 session.setAttribute("student", u);
			 
			 session.setAttribute("studentProfile", new StudentProfile());
			 
			 System.out.println("Identified as student");
			 return "redirect:/studentProfile.html";
			 }
		 else if (u.isFaculty())
		 {
			 session.setAttribute("faculty", u);
			 session.setAttribute("facultyProfile", new FacultyProfile());
			 System.out.println("Identified as faculty");
			 return "redirect:/facultyProfile.html";
			 }
		 }
		 }
		 catch(Exception e)
		 {
		 return "errorPage";
		 }
		 return "redirect:/";
	 }
	 
	 @RequestMapping("/reviewer.html")
	 public String addFaculty(ModelMap model)
	 {
//		 String type=user.getType();
//			if(type.equals("NULL"))
		 
		    session.setAttribute("userList", userDao.getAllUsers());
		    session.setAttribute("facultyList", facultyDao.getAllFaculties());
		    session.setAttribute("facultiesWithSubsAssigned", facultySubDao.facultiesWithSubjectsAllocated());
		    session.setAttribute("approvedList", facultySubDao.facultiesWithSubjectsApproved());
		    System.out.print(" users with null type");
		 return "reviewer";
	 }
	 
	 @RequestMapping("/reviewer2.html")
	 public String reviewer2Home(ModelMap model)
	 {
		 
		 
		 
		 List<TermDate> td= adminDao.getTermDateList();
		 if(td!=null)
		 {
			 session.setAttribute("termDateStatus", "notNull");
			 session.setAttribute("listOfTermDate", td);
		 }
		 else
		 {
			 session.setAttribute("termDateStatus", "null");
		 }
		 return "reviewer2";
	 }
	 
	 public static long compareTo( java.util.Date date1, java.util.Date date2 )
	 {
	 //returns negative value if date1 is before date2
	 //returns 0 if dates are even
	 //returns positive value if date1 is after date2
	   return date1.getTime() - date2.getTime();
	 }
	 
	 @RequestMapping("/admin.html")
	 public String adminHome(ModelMap model) throws ParseException
	 {
		 model.put("facultiesWithSubsAssigned",facultySubDao.facultiesWithSubjectsAllocated());
		 model.put("semList", courseDao.getSemesters());
		 
		 List<TermDate> td= adminDao.getTermDateList();
		 
		 
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
		 Date d= new Date();
		 String StringDate= df.format(d);
		 Date currentDate = df.parse(StringDate);
		 System.out.println("current date: "+currentDate);
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		 
		 List<TermDate> t3= new ArrayList<TermDate>();
		 
		 try
		 {
			 if(td!=null)
			 {
		 for(TermDate td2:td)
		 {
			
			 Date d2= td2.getToD();
			 System.out.println("from term date table : "+d2);
			 if(currentDate.before(d2) && td2.getStatus().equals("NotMoved"))
			 {
				 System.out.println("before");
				 session.setAttribute("checker", "cantMove");
			 }
			 else if(currentDate.after(d2) && td2.getStatus().equals("NotMoved"))
			 {
				 System.out.println("after");
				 t3.add(td2);
				 session.setAttribute("checker", "Move");
			 }
			 else if(currentDate.compareTo(d2)==0 && td2.getStatus().equals("NotMoved"))
			 {
				 t3.add(td2);
				 System.out.println("todays day : can move");
				 session.setAttribute("checker", "move");
			 }
			 
			 
		 }
		 session.setAttribute("isEmptyy", "nooo");
		 }
			 else
			 {
				 session.setAttribute("isEmptyy", "yesss");
			 }
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		 
		 session.setAttribute("movableList", t3);
		 
		 
		 //model.put("faculties", va)
		
		 return "admin";
	 }
	 
//	 @RequestMapping("/facultyProfile.html")
//	 public String facultyHome(ModelMap model)
//	 {
//		 User u=(User)session.getAttribute("faculty");
//		 long Id= u.getId();
//		 model.put("mySubjects", facultySubDao.getMySubjects(Id));
//		
//		 return "facultyProfile";
//	 }
	 
	 @RequestMapping("/facultyProfile.html")
	 public String facultyHome(ModelMap model)
	 {
		 int i=0;
		 User u=(User)session.getAttribute("faculty");
		 long Id= u.getId();
		 session.setAttribute("mySubjects", facultySubDao.getMySubjects(Id));
		 List<FacultySubject> fs1= facultySubDao.getMySubjects(Id);
		 List<Object> fslist = new ArrayList<Object>();
        List<TimeTable> tt= new ArrayList<>();
        List<TimeTable> tt2= new ArrayList<>();
        TimeTable t3= new TimeTable();
        for(FacultySubject fs:fs1)
        {
        	
        	System.out.println("fs1 size: "+fs1.size());
        	
        	long subId= fs.getLstsubject().getId();
        	System.out.println("sub id: "+subId);
        	try
        	{
        	tt=facultyDao.getMyTimeTable(subId);
        	System.out.println("tt size: "+tt.size());
        	for(int j=0;j<=tt.size();j++)
        	{
        		t3=tt.get(j);
        		tt2.add(t3);
        	}
        	}
        	catch(Exception e)
        	{
        		
        	}
        }
        session.setAttribute("facultyTimeTale", tt2);
		  
        List<FacultySubject> partnerList= new ArrayList<FacultySubject>();
        List<FacultySubject> duplicate= new ArrayList<FacultySubject>();
        //FacultySubject fs3= new FacultySubject();
		 for(FacultySubject fs2:fs1)
		 {
		long subIds= fs2.getLstsubject().getId();
			 System.out.println(subIds+": sub ids");
			 //session.setAttribute("mySubPartners", facultySubDao.getMyPartners(subIds));
		 	for(FacultySubject fs3:facultySubDao.getMyPartners(subIds,Id))
		 	{
		 		long id= fs3.getUser().getId();
		 		if(id==Id)
		 		{
		 			duplicate.add(fs3);
		 		}
		 		else
		 		{
		 		partnerList.add(fs3);
		 		}
		 	}
		 }
		 
		 System.out.println("duplicate length: "+duplicate.size());
		  
		 session.setAttribute("Partners", partnerList);
		 
		 List<Topics> topic= new ArrayList<Topics>();
		 List<Topics> topic2= new ArrayList<Topics>();
		 Topics t= new Topics();
		 
		 for(FacultySubject fs4:partnerList)
		 {
			 long partnerId= fs4.getLstfaculty().getId();
			 long subId= fs4.getLstsubject().getId();
			 topic2=facultyDao.getTopicsOfMyPartners(partnerId, subId);
			 for(Topics t2:topic2)
			 {
				 topic.add(t2);
			 }
		 }
		 
		 
		 session.setAttribute("partnersTopicList", topic);
		
		 return "facultyProfile";
	 }
	 
	 @RequestMapping("/studentProfile.html")
	 public String studentProfile(ModelMap model) throws ParseException
	 {
		 
		
		 StudentProfile s= (StudentProfile)session.getAttribute("studentProfile");
		 User u=(User)session.getAttribute("student");
		 
		 int sem= u.getStudentProfile().getSemester();
		 long stuId=u.getStudentProfile().getId();
		 
		 
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		 Date d= new Date();
		 String StringDate= df.format(d);
		 Date startDate = df.parse(StringDate);
		 
		 System.out.println("todays date:"+d);
		 
		 session.setAttribute("yourTimeTable", examDao.getTimeTableBySem(sem));
		 List<Integer> i = new ArrayList<Integer>();
		 int counter=0;
 
		 try
		 {
			 if(examDao.getTimeTableBySem2(sem)!=null)
			 {
				 session.setAttribute("isTimeTable", "true");
		 for(TimeTable tt:examDao.getTimeTableBySem(sem))
		 {
			 System.out.println("sub name: "+tt.getSubId().getSubjectName()+" and date: "+ tt.getDate());
			 Date d2= tt.getDate();
			
			 if(startDate.compareTo(d2)==0)
			 { 
				 counter++;
				 i.add(counter);
				 System.out.println("date matched");
				 
				 long subId= tt.getSubId().getId();
				 if(examDao.examValidationForThisStudent(stuId)==null)
						 {
					 session.setAttribute("noExamToday", "ExamToday");
					 session.setAttribute("valid", "Give");
					 session.setAttribute("yourTodaysSub", courseDao.getThisSubject(subId));
				     session.setAttribute("status", "Not_Given");
						 }
				 else{
					 session.setAttribute("noExamToday", "NoExamToday");
					 
					 session.setAttribute("valid", "cantGive");
					 session.setAttribute("yourTodaysSub", courseDao.getThisSubject(subId));
					 session.setAttribute("status", "given");
					 //session.setAttribute("examSchedule", "notNull");
				 }
				 
					 }
			 else if(startDate.before(d2))
			 {
				 System.out.println("before");
			 }
			 else if(startDate.after(d2))
			 {
				 System.out.println("after");
			 }
			 else
			 {
				 System.out.println("date not matched");
			 }
						 }
		 }
			 else
			 {
				 session.setAttribute("isTimeTable", "false");
				// session.setAttribute("examSchedule", "null ");
			 }
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
		 System.out.println("counter size: "+i.size());
		 if(i.size()==0)
		 {
			 session.setAttribute("noExamToday", "NoExamToday");
		 }
		 
		 
		 
		 		 return "studentProfile";
	 }
	 
	 @InitBinder
	 public void initbinder(WebDataBinder binder)
	 {
		 binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("DD/MM/YYYY"),true));
		 
	 }
	 
	 @RequestMapping(value="/giveExamm.html",method=RequestMethod.GET)
	 public String checkDateForThisSub(@RequestParam long subId) throws ParseException
	 {
		 TimeTable tt= new TimeTable();
		 session.setAttribute("subject", courseDao.getThisSubject(subId));
		 
		 User u=(User)session.getAttribute("student");
		 long stuId= u.getStudentProfile().getId();
		 session.setAttribute("stuId", stuId);
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		 Date d= new Date();
		 String StringDate= df.format(d);
		 Date startDate = df.parse(StringDate);
		 Date d2= examDao.getTimeTable(subId).getDate();
		  if(startDate.compareTo(d2)==0)
		 {
			 long validSubId=subId;
			 session.setAttribute("particularSubDate", examDao.getTimeTable(validSubId));
				//session.setAttribute("valid", "canGive");
			 session.setAttribute("validSubId",validSubId );
			 return "redirect:/giveExam.html";
		 }
		 else
		 {
			// session.setAttribute("valid", "cantGive");
			 return "redirect:/studentProfile.html";
		 }
	 }
	 
	 
	 @RequestMapping("/forgotPwd.html")
	 public String forgot()
	 {
		 return "forgotPwd";
	 }
	 
	 
	 @RequestMapping("/sendMeNewPwd.html")
	 public String sendNewPwd(HttpServletResponse response) throws ServletException
	 {
		 String from ="bhardwajjsh95@gmail.com";
	        String to = request.getParameter( "userEmail" );
	      
	        String subject = "Your New Password";
	        Random r= new Random();
	        long number= r.nextLong();
	        
	        
	       
	        User u = userDao.getUser(to);
	        if(u!=null)
	        {
	        String content = u.getPassword();
	     
	        Properties props = System.getProperties();
	     
	        props.put("mail.smtp.auth", true);
	        props.put("mail.smtp.starttls.enable", true);
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	       
	        Session mailSession = null;

	        mailSession = Session.getInstance(props,  
	                new javax.mail.Authenticator() {  
	            protected PasswordAuthentication getPasswordAuthentication() {  
	                return new PasswordAuthentication("bhardwajjsh95@gmail.com", "family@love7895");  
	            }  
	        });  
	        

	        
	        try
	        {
	        	Message message = new MimeMessage(mailSession);

	            
	            message.setFrom(new InternetAddress("bhardwajjsh95@gmail.com"));

	            
	            message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	            
	            message.setSubject("Forgot Password Request From ExamAnalayst System..");

	            
	            message.setText("Hello,  "
	               + "Your Password:  "+content);

	            
	            Transport.send(message);

	            System.out.println("Sent message successfully....");
	        	

	        }
	        catch( Exception e )
	        {
	            throw new ServletException( e );
	        }

           session.setAttribute("error", "");

	        return "redirect:/index.html";
	        }
	        
	        else
	        {
	        	session.setAttribute("error", "Your Email Doesnt Exists... ");
	        	return "redirect:/forgotPwd.html";
	        }
	        	
	       
	        	
	        

	 }
	 
	 
	 
	 

	 
}
