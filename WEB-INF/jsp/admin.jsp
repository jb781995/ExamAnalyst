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
//     	alert("Hey Joshi Bhardwaj , You are an Engineer now..Congratulations For completing your 'Graduation' With such an AWSEMONESSSS and ,Surely, 8.6 was like a cherry on the cake..wish you a happy carrer ahead..");
		
    var status =$('#e').val();
    
    if(status=="yesss")
    	{
    	$('#move').hide();
    	}
    else if(status=="nooo")
    	{
       var check= $('#c').val();
          if(check=="cantMove")
        	  {
        	  $('#move').hide();
        	  }
    	}
    
    });

    </script>

</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<h3> <b> <i> Hello ${adminUser.lname } ${adminUser.fname }..</i> </b> You Are Logged in as Admin.. </h3>
<h1> OOOOPPPSSS , sorry .. as an <b><i>ENGINEER</i></b>  </h1> </br>
</br></br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h3>&nbsp;&nbsp;&nbsp;&nbsp; Hey Joshi Bhardwaj , You are an <b><u>Engineer</u></b> now..Congratulations For completing your </h3></br>
                                                       <h3>&nbsp;&nbsp;&nbsp;&nbsp;<b><u>"Graduation"</u></b>&nbsp; With such an <i>AWESOMENESSS</i>&nbsp; and ,Definitely, <b>8.6</b> was like a cherry on the cake..</h3></br>
                                                       <h3>&nbsp;&nbsp;&nbsp;&nbsp;wish you a happy and successful career ahead..</h3>

<%-- <div class="panel panel-default" style="width:800px;margin-left:150px">
  <div id="headerdiv1" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body1)">
  
  <a href="javascript:toggleDiv('body1');" style="padding: 5px 10px;color:white">Approve Following Faculties</a></div>
  <div id="body1" class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;background-color:DarkGray">
<tr>
<th> Id </th>
<th> Faculty Id </th>
<th> Name</th>
<th> Assigned Subject</th>
<th> Semester </th>
<th> Action </th>
</tr>

<c:forEach items="${ facultiesWithSubsAssigned}" var="lstFaculties">
<tr>
<td> ${lstFaculties.id }</td>
 <td> ${lstFaculties.lstfaculty.facId}</td> 
<td>${lstFaculties.user.fname}  ${ lstFaculties.user.lname}</td>
<td> ${lstFaculties.lstsubject.subjectName }</td>
<td> ${lstFaculties.lstsubject.sem.semester } </td>
<td> <a class="btn btn-success" href="<c:url value='approve.html?id=${ lstFaculties.id}'/>"> Approve</a></td>
</tr>
</c:forEach> 
</table>
</div>
</div>

<div class="panel panel-default" style="width:1000px;margin-left:150px">
  <div id="headerdiv2" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body1)">
  
  <a href="javascript:toggleDiv('body2');" style="padding: 5px 10px;color:white">Set Exam Schedule For</a></div>
  <div id="body2" class="panel-body">
    <table  class="table" style="font-size:18px;width:800px">
<tr>

<c:forEach items="${semList }" var="semester">
<td><a class="btn btn-success" href="<c:url value='setTimeSchedule.html?sem=${ semester.semester}'/>"> semester ${semester.semester } </a> </td>
</c:forEach>
</tr>
</table>
</div>
</div>

<p><a  class="btn btn-success"href="<c:url value="editTimeTable.html"/>">Edit TimeTable </a>  </p>



<div class="panel panel-default" style="width:1000px;margin-left:150px" id="move">
  <div id="headerdiv3" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body2)">
  
  <a href="javascript:toggleDiv('body3');" style="padding: 5px 10px;color:white">Move Students</a></div>
  <div id="body3" class="panel-body">
    <table  class="table" style="font-size:18px;width:800px">
    <tr>
    <th>Semester </th>
    <th>Action </th>
    
    </tr>
    <c:forEach items="${movableList}" var="ml">
    <tr>
    <td>From Semester: <input type="text" value="${ml.semester}" name="from" id="from${ml.id}" disabled/> to semester : <input type="text" value="${ml.semester+1}" name="to" id="to${ml.id}" disabled/></td>
    <td> <input type="button" value="Move" id="${ml.id}" class="moveThis"  id="${ml.id}" /></td>
    </tr>
    </c:forEach>
    </table>
    </div>
    </div>
    

<input type="text" value="${isEmptyy}" id="e"/><br>
<input type="text" value="${checker}" id="c">

<form action="<c:url value="move.html"/>" id="moveForm" method="POST">
<input type="hidden" name="from" id="f" value=""/>
<input type="hidden" name="to" id="t" value=""/>
<input type="hidden" name="id" id="i" value=""/>
</form>

<script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="resources/javascript/forMove.js" />"></script>


 --%>

</body>
</html>