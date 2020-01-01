package newExam.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import newExam.models.FacultySubject;
import newExam.models.Semester;
import newExam.models.StudentProfile;
import newExam.models.Subject;
import newExam.models.TermDate;
import newExam.models.TimeTable;
import newExam.models.dao.AdminDao;
import newExam.models.dao.CourseDao;
import newExam.models.dao.FacultySubjectDao;
import newExam.models.dao.StudentDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AdminActionController {
	
	@Autowired
	FacultySubjectDao facultySubDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	CourseDao courseDao;
	
	@Autowired
	StudentDao stuDao;
	
	@Autowired
	 private HttpSession session;
	
	
	
	FacultySubject fs;
	TimeTable tt;
	
	@RequestMapping(value="/approve.html",method=RequestMethod.GET)
	public String approve(ModelMap model,@RequestParam long id)
	{
		fs=facultySubDao.approveThisFaculty(id);
		System.out.println("goooodddddd....");
		
		return "redirect:/admin.html";
	}
	
	@RequestMapping(value="/setTimeSchedule.html",method=RequestMethod.GET)
	public String setPaper(ModelMap model, @RequestParam Integer sem)
	{
		//model.put("timeTable", new TimeTable());
		session.setAttribute("subjects", courseDao.getSubjects(sem));
		
		session.setAttribute("sem", sem);
		return "redirect:/setTimeTable.html";
		
	}
	

	
	
	
	
	@RequestMapping(value="/setDateForThisSub2.html",method=RequestMethod.POST)
	public String saveDateForThisSub(@RequestParam String Date,@RequestParam ("Date")@DateTimeFormat(pattern="dd/MM/yyyy")Date date, @RequestParam String SubId,@RequestParam String type,HttpServletRequest req) throws ParseException
	{
		TimeTable tt= new TimeTable();
		
		long id=Long.parseLong(SubId);
		System.out.println("subId"+id);
		
		String DateString = Date;
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date startDate = df.parse(DateString);
	        String newDateString = df.format(startDate);
	        System.out.println("date i got from form :"+newDateString);
	        
	   
		
		
		
		//Date d=Date;
		
		
		
        String examType=type;
        int sem=(int) session.getAttribute("sem");
        System.out.println("subId:"+id);
        System.out.println("date:"+date);
        System.out.println("type:"+examType);
        System.out.println("sem:"+sem);
        tt.setDate(startDate);
        tt.setExamType(examType);
        tt.setSem(courseDao.getThisSem(sem));
        tt.setSubId(courseDao.getThisSubject(id));
        tt=adminDao.setDate(tt);
        System.out.println("Date set");
		
		return "redirect:/setTimeTable.html?sem="+sem;
	}
	
	@RequestMapping(value="/setTimeTable.html")
	public String setTimeTable()
	{
		return "setTimeTable";
	}
	
	@RequestMapping("/editTime.html")
	public String goToEdit()
	{
		return "redirect:/editTimeTable.html";
	}
	
	@RequestMapping("/editTimeTable.html")
	public String editTimeTable()
	{
		session.setAttribute("listOfTimeTable", adminDao.getAllTimeTable());
	    return "editTimeTable";	
	}
	
	@RequestMapping(value="/deleteTimeTab.html",method=RequestMethod.POST)
	public String delete(@RequestParam String dateId)
	{
		long id=Long.parseLong(dateId);
		adminDao.deleteThisDate(id);
		
		return "redirect:/editTimeTable.html";
	}
	
	@RequestMapping(value="/editTimeTab.html",method=RequestMethod.POST)
	public String commit(@RequestParam String Date,@RequestParam String type,@RequestParam String Id) throws ParseException
	{
		System.out.println("inside edit"+Date+type+Id);
		
//		String v_date_str=Date;
//	
//		DateFormat formatter;
//		formatter = new SimpleDateFormat("dd-MM-yyyy");
//		Date date_temp=null;
//		try {
//		        date_temp = (Date) formatter.parse(v_date_str);
//		    } catch (ParseException ex) {
//		 
		//}
		//String DateString = Date;
	  //  DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	  //  Date startDate = df.parse(DateString);
	     //   String newDateString = df.format(startDate);
		String DateString = Date;
		
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    Date startDate = df.parse(DateString);
	        String newDateString = df.format(startDate);
	        String eType= type;
	        long id=Long.parseLong(Id);
	        TimeTable tt= adminDao.findById(id);
	        tt.setDate(startDate);
	        tt.setExamType(eType);
	        tt=adminDao.setDate(tt);
		return "redirect:/editTimeTable.html";
	}
	
	@RequestMapping(value="/move.html",method=RequestMethod.POST)
	public String moveStudentsOfThisSem(@RequestParam String from,@RequestParam String to,@RequestParam String id,HttpServletRequest req)
	{
		long id2=Long.parseLong(id);
		int from2= Integer.parseInt(from);
		int to2=Integer.parseInt(to);
		System.out.println("from : "+from2+" and to : "+to2+"  id: "+id2);
		
		List<StudentProfile> sp= stuDao.getStudentOfThisSem(from2);
		for(StudentProfile sp2:sp)
		{
			if(from2==8)
			{
				System.out.println("do nothing");
			}
			else
			{
			sp2.setSemester(to2);
			sp2=stuDao.saveStudent(sp2);
		}
		}
		
		TermDate td= adminDao.find(id2);
		td.setStatus("Moved");
		td= adminDao.save(td);
		
		return "redirect:/admin.html";
	}

}
