<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
.btnColor
{
backgroud-color: red;
}
</style>
</head>
<body>
<form action="<c:url value="saveAndNextStu.html" />" method="POST"> 
<h3>Regular MideSem For <i> Subject: ${particularSubDate.subId.subjectName}</i> </h3>
<table border="1">
<tr>
<td>No. Of Questions in this Exam:&nbsp;&nbsp; </td>
<td id="size">&nbsp;&nbsp;${TotalQuestions} </td>
</tr>
</table> </br> </br> </br>

<div class="palette">
</div>
${index}
<textarea rows="7" cols="80" name="question" >${thisQuestion.question}</textarea> </br>
Only Type In The Option Like:(A or B or C or D)</br> <input type="text" name="correctOption" value="${previousQuestion.studentAnswer}" /> </br></br>
<input type="submit" name="next" value="next"/>
<a href="<c:url value="showPreviousQ.html?currentQuestion=${counter}"/>"><input type="button" name="previous" value="Show Previous"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="review" value="Mark for Review" id="${index}" class="mark" /> </br> </br> </br>
<a href="<c:url value="showAnsWholeList.html"/>">Show Whole List </a> </br> </br> </br>

<a href="<c:url value="returnStudentProfile.html"/>"> End Exam </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="<c:url value="goBack.html"/>"> back </a>


</form>

      <script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
      <script src="<c:url value="resources/javascript/questionPaper.js" />"></script>

<script>
$(document).ready(function()
		{
		alert("palette");
			
	 size=parseInt($("#size").text(),10);
	for (var i=1;i<=size;i++)
		{
		index=i;
		$(".palette").append("<input type='button' name='btn' value='"+i+"' id='btn"+i+"'/>");
		}

	$(".mark").click(function(){
		var id=$(this).attr("id");
		alert("id of this mark: "+id);
		$("#btn"+id).css("background-color","red");
		
	});
	
	
	});
</script>

</body>
</html>