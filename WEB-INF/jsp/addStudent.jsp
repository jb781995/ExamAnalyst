<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<br>
<br>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<form:form modelAttribute="student">
<div class="panel panel-default" style="width:800px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Show me Latest Registrations</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">
<tr>
<th>First Name </th>
<th> Last Name </th>
<th> Email </th>
</tr> 
<tr>
<td> ${thisUser.fname}  </td>
<td> ${thisUser.lname}</td>
<td> ${thisUser.email}</td>
</tr>
</table>
</div>
</div>
<br>

<div class="panel panel-default" style="width:800px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Enter Student Details</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">

<tr>
<th>Enter enrollment No:</th><td> <form:input path="enrollmentNo"/> </td>
</tr><tr>
<th>Enter semester: </th> <td><form:input path="semester"/></td> </tr>
<tr><th>Enter Division [A or B]: </th> <td><form:input path="division"/></td>
</tr> 
<tr>
<td> </td> <td> <input class="btn btn-success" type="submit" value="Save Student Profile"/> </td> 
</tr>
</table>



</form:form>
</body>
</html>