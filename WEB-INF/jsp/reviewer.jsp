<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>

</br>
</br>
</br>
</br>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviewer</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

    <script type="text/javascript">

    function toggleDiv(divId) {
      
       $("#"+divId).toggle();

    }

    </script>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<div class="container">
<h2> Hello <b><i>${reviewerUser.fname} ${reviewerUser.lname} </i> </b> </h2>


<div class="panel panel-default">
  <div id="headerdiv1" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body1)">
  
  <a href="javascript:toggleDiv('body1');" style="padding: 5px 10px;color:white">Show me Latest Registrations</a></div>
  <div id="body1" class="panel-body">
    <table border="1" class="table" style="font-size:18px;background-color:DarkGray">
<tr >
<th style="text-align:center">User No.</th>
<th style="text-align:center">First Name</th>
<th style="text-align:center">Last Name</th>
<th style="text-align:center">email</th>
<th style="text-align:center">Actions </th>
</tr>

<c:forEach items="${userList}" var="users">
<tr border="1">
<td style="text-align:center">${users.id}</td> 
<td style="text-align:center">${users.fname} </td>
<td style="text-align:center">${users.lname} </td>
<td style="text-align:center">${users.email} </td>
<td style="text-align:center"><a class="btn btn-success"  href="<c:url value='addFaculty.html?id=${users.id}' />">Add as Faculty &nbsp;&nbsp;&nbsp; </a>
    <a style="text-align:center" class="btn btn-success" href="<c:url value='addStudent.html?id=${users.id}' />">Add as Student  </a></td>
</tr>
</c:forEach>

</table>
  </div>
</div>


<div class="panel panel-default">
  <div id="headerdiv2" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body2)"> 
  <a href="javascript:toggleDiv('body2');" style="padding: 5px 10px;color:white">List of Faculties </a></div>
  <div  id="body2" class="panel-body">

<table border="1" class="table" style="font-size:18px;background-color:DarkGray">
<tr>
<th style="text-align:center">User Reg. No.</th>

<th style="text-align:center;color:DarkBlue">First Name</th>
<th style="text-align:center">Last Name</th>
<th style="text-align:center">email</th>
<th style="text-align:center">Actions </th>
</tr>

<c:forEach items="${facultyList}" var="faculties">
<tr>
<td style="text-align:center">${faculties.id}</td>

<td style="text-align:center">${faculties.fname} </td>
<td style="text-align:center">${faculties.lname} </td>
<td style="text-align:center">${faculties.email} </td>

<td style="text-align:center"><a style="text-align:center" href="<c:url value='assignSub.html?id=${faculties.id}'/>" class="btn btn-success">Assign subjects  </a>
    </td>
</tr>
</c:forEach>

</table>
</div>
</div>


</br>
</br>
<div class="panel panel-default">
  <div id="headerdiv3" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body3)">
   <a href="javascript:toggleDiv('body3');" style="padding: 5px 10px;color:white">These Are Faculties ,  to whom You have Assigned Subjects</a></div>
  <div id="body3" class="panel-body">
<table class="table" style="font-size:18px;background-color:DarkGray">
<tr>
<th>Id </th>
<th>Faculty Id </th>
<th> Name </th>
<th> Subject </th>
<th> Semester</th>
<th>Actions </th>
</tr>
<c:forEach items="${facultiesWithSubsAssigned }" var="listFaculty">
<tr>
<td>${listFaculty.id}</td>
<td>${listFaculty.lstfaculty.facId} </td>
<td>${listFaculty.user.fname}  ${ listFaculty.user.lname} </td>
<td>${listFaculty.lstsubject.subjectName} </td>
<td>${listFaculty.lstsubject.sem.semester} </td>
<td> <a href=" <c:url value='unAssign.html?id=${listFaculty.id }'/>" class="btn btn-success">Un-Assign Subject</a> </td>

</tr>
</c:forEach>
</table>
</div></div>


<div class="panel panel-default">
  <div id="headerdiv4" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body4)">
   <a href="javascript:toggleDiv('body4');" style="padding: 5px 10px;color:white">Faculties Approved By Admin</a></div>
  <div id="body4" class="panel-body">
<table class="table" style="font-size:18px;background-color:DarkGray">
<tr>
<th> Name </th>
<th> Subject </th>
<th> Semester</th>
</tr>
<c:forEach items="${approvedList}" var="al">
<tr>
<td>${al.user.fname} ${al.user.lname} </td>
<td>${al.lstsubject.subjectName} </td>
<td>${al.lstsubject.sem.semester} </td>
</tr>
</c:forEach>

</table>
</div>
</div>
<a href="<c:url value='index.html'/>"> logout </a>
</div>

<script src="bootstrap.min.js"></script>
</body>
</html>