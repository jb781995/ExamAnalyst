<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" />"></script>
   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Subjects</title>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>

<form>



<div class="panel panel-default" style="width:980px;margin-left:200px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Faculty Details</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">
<tr >
<td> Faculty Reg. No </td>
<td> Faculty  Id</td>
<td> Faculty Name</td>
</tr><tr>
<td>${userObj.id } </td> 

<td>${userObj.facultyProfile.facId }  </td>

<td> ${userObj.fname }</td>
</tr>
</table> 
</div>
</div>




<div class="panel panel-default" style="width:980px;margin-left:200px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">You can select multiple semester to assign subjects</div>
  <div class="panel-body">


<table class="table" style="font-size:18px;background-color:DarkGray"><tr>
<c:forEach items="${semesters }" var="semList">
<td>
<input type="checkbox" value="${semList.semester }" class="semester" > Semester ${semList.semester }</td>
</c:forEach></tr>
</table>
</div></div>


<div class="panel panel-default" style="width:980px;margin-left:200px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Subjects list : Select multiple subjects if required</div>
  <div class="panel-body">



<div id="subjects" style="font-size:20px">
</div>
</div></div>
</form>


   <form action="<c:url value='/hiddenForm.html' />" id="hide" method="post">
  <input type="hidden" id="hiddenField1" name="hiddenField" value=" " />
  <!--  <input type="hidden" id="facId" name="hiddenFacID" value="${facultyObj.facId}"> -->
  </form>
  <div style="width:100%;text-align:center">
  <input class="btn btn-success" type="button" id="saveBtn" value="Assign Subjects">
  	</div>
  	<script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="resources/javascript/ajax1.js" />"></script>
</body>
</html>