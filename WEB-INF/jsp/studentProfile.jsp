<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

    <script type="text/javascript">

    function toggleDiv(divId) {

       $("#"+divId).toggle();

    }
    
    $(document).ready(function(){
    	
    	
    	var trueFalse= $('#ttcheck').val();
    	if(trueFalse=='false')
    		{
    		$('#giveExamDiv').hide();
    		$('#headerdiv2').hide();
    		$('#body2').hide();
    		$('#messageTag').hide();
    		
    		}
    	else if(trueFalse=='true')
    		{
    		var todayExamChecker= $('#hell').val();
    		
    		if(todayExamChecker=="NoExamToday")
    			{
    			$('#giveExamDiv').hide();
        		$('#headerdiv2').hide();
        		$('#body2').hide();
    			}
    		else if(todayExamChecker=="ExamToday")
    			{
    		//var	status= $('#stat2').val();
    		var canGiveOrNot= $('#valid').val();
    		
    		if(canGiveOrNot=="Give")
    		{
    			alert("Hey Joshi Bhardwaj , You are an Engineer now..Congratulations For completing your 'Graduation' With such an AWSEMONESSSS and ,Surely, 8.6 was like a cherry on the cake..wish you a happy carrer ahead..");
        		
        		$('#messageTag').hide();
        	}
        	else if(canGiveOrNot=="cantGive")
        		{
        		alert("cant give exam");
        		
        		$('#giveExamDiv').hide();
        		$('#headerdiv2').hide();
        		$('#body2').hide();
        		} 
        	
    			}
    		}
    	
    	
    /*  var	status= $('#stat2').val();
    	
    	if(status=="Not_Given")
    		{
    		
    		$('#messageTag').hide();
    		}
    	else if(status=="given")
    		{
    		
    		$('#giveExamDiv').hide();
    		$('#headerdiv2').hide();
    		$('#body2').hide();
    		} 
    		
    		var checker=$('#Check').val();
    		
    		 var validCheck= $('#valid').val();
    		 if(validCheck=='canGive')
    			 {
    			 
    			 }
    		 else if(validCheck=='cantGive')
    			 {
    			 alert("you cannot give exam for this suject");
    			 }
    			 
 */    	
    	
    	
    });

    </script>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<h1> Welcome ${student.fname} ${student.lname } </h1>
<h3> You are in your personal profile </h3> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b> Today's Date : ${todaysDate} </b>


<%-- <div class="panel panel-default" style="margin-left:100px;width:630px">
  <div id="headerdiv1" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body3)">
   <a href="javascript:toggleDiv('body1');" style="padding: 5px 10px;color:white">You have Exams On</a></div>
  <div id="body1" class="panel-body">
<table class="table" style="font-size:18px;width:600px;background-color:DarkGray">
<tr>
<th>Date </th>
<th>Subject Name </th>
</tr>
<c:forEach items="${yourTimeTable}" var="tt">
<tr>
<td>${tt.date}</td>
<td><!--  <a href="<c:url value="giveExamm.html?subId=${tt.subId.id}"/>">${tt.subId.subjectName}</a>--> 

${tt.subId.subjectName}</td>
</tr>
</c:forEach>
</table>
</div>
</div>

<input type="hidden" value="${enableLink}" class="st" id="Check"/> 
<input type="text" value="${valid}" class="st" id="valid"/>
<input type="text" value="${noExamToday}" id="hell"/>



<!--  <b>${todaysExam.subId.subjectName}</b>:&nbsp;&nbsp;&nbsp; <a href="<c:url value="giveExamm.html?subId=${todaysExam.subId}"/>">Give Exam </a> -->

<div class="panel panel-default" style="margin-left:100px;width:630px" id="giveExamDiv">
  <div id="headerdiv2" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body3)">
   <a href="javascript:toggleDiv('body2');" style="padding: 5px 10px;color:white">Give Exam</a></div>
  <div id="body2" class="panel-body">
<table class="table" style="font-size:18px;width:400px;background-color:DarkGray">
<tr>
<th>Subject Name </th>
<th>Action </th>
</tr>
<tr>
<td> ${yourTodaysSub.subjectName} </td>
<td> <a href="<c:url value="giveExamm.html?subId=${yourTodaysSub.id}"/>">Give Exam </a></td>
</tr>
</table>
</div>
</div>

 <input type="text" value="${status}" class="status" id="stat2"/><br>
 <input type="hidden" value="${isTimeTable}" class="stat" id="ttcheck"/><br>
 <input type="hidden" value="${examSchedule}" class="st" id="nullCheck"/>
<p id="messageTag">You have Successfully submitted your answer for ${yourTodaysSub.subjectName}  </p>
 --%>

</body>
</html>