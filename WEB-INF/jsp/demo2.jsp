<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<script type="text/javascript" src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript">

function Countdown()
{

this.start_time="01:30"
this.target_id="#timer";
this.name="timer";
}

Countdown.prototype.init=function()
{
this.reset();
setInterval(this.name+'.tick()',1000);
}

Countdown.prototype.reset=function()
{
time=this.start_time.split(":");
this.minutes=parseInt(time[0]);
this.seconds=parseInt(time[1]);
this.update_target();
}



Countdown.prototype.tick=function()
{
if(this.seconds>0 || this.minutes>0)
{

  if(this.seconds==0)
  {
   this.minutes=this.minutes-1;
   this.seconds=59;
   }
else
{
this.seconds=this.seconds-1;
}
}
this.update_target();
}

Countdown.prototype.update_target=function()
{
seconds=this.seconds;
if(seconds<10)seconds="0"+seconds;
$(this.target_id).val(this.minutes+":"+seconds);

if($(this.target_id).val()=="0:00")
{
	
	//clickanchor();
	window.location.href = "returnStudentProfile.html";
   // return false;
   
    
	
	}

}

function clickanchor()
{
	alert("Going IN ");
	$("#endexam").click();
	
	
	}
</script>
<style>
.palette
{
    border-radius: 25px;
    background: #73AD21;
    padding: 20px; 
    width: 200px;
    height: 250px;  
    float:right;  

}
</style>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>

<form> 
<h3>Regular MideSem For <i> Subject: ${particularSubDate.subId.subjectName}</i> </h3>
<table border="1">
<tr>
<td>No. Of Questions in this Exam:&nbsp;&nbsp; </td>
<td id="size">&nbsp;&nbsp;${TotalQuestions} </td>
</tr>
</table>

<div class="palette">
</div>
<label id="lbl"> </label>

<input type="text" id="timer">
<script type="text/javascript">
timer= new Countdown();
timer.init();
</script>

<div class="panel panel-default" style="width:800px;margin-left:50px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px"><b> Question: <i> ${thisSub.subjectName }</i> </b></div>
  <div class="panel-body">

<textarea rows="7" cols="80" name="question" id="textArea" disabled>1)  ${thisQuestion.question}</textarea> </br>
Only Type In The Option Like:(A or B or C or D)</br> <input type="text" name="correctOption" id="ans" value=" " /> </br></br>
<input type="button" name="nextQue" value="Next" id="${index}" class="btn btn-success next"/>
<input type="hidden" name="nextIndex" id="hdn" />&nbsp;
<input type="hidden" id="hdnCurrentQue" value="1"/>&nbsp;
<!--  <a href="<c:url value="showPreviousQ.html?currentQuestion=${counter}"/>"><input type="button" name="previous" value="Show Previous"/></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
<input type="button" value="Previous" class="btn btn-success previous"/>
<input type="button" name="review" value="Mark for Review" id="${index}" class="btn btn-success mark" /> &nbsp;
<input type="button" name="unMarkReview" value="UnMark" id="${index}" class="btn btn-ccess unmark" /> &nbsp;
<a class="btn btn-success" href="<c:url value="showAnsWholeListt.html"/>">Show Whole List </a> <br> <br>

<a class="btn btn-success endexam" id="endexam" href="<c:url value="returnStudentProfile.html"/>"> End Exam </a>&nbsp;&nbsp;
<%-- <a class="btn btn-success" href="<c:url value="goBack.html"/>"> back </a> &nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="logOutt.html"/>"> logOut </a>&nbsp;&nbsp; --%>

</div>
</div>

</form>

      <script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
      <script src="<c:url value="resources/javascript/questionPaper.js" />"></script>



</body>
</html>
</body>
</html>