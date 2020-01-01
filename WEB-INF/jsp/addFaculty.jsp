<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<br>
<br>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<form:form modelAttribute="faculty">

<div class="panel panel-default" style="width:800px;margin-left:300px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Show me Latest Registrations</div>
  <div class="panel-body">
<table class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">
<tr>
<th> First Name </th>
<th> Last Name </th>
<th> Email </th>
<th> Enter Faculty Registration Id </th>
 <!--    <th>Select Semesters</th>
<th> Select subjects For this Faculty </th> -->
</tr>

<tr>
<td> ${thisUser.fname} </td>
<td> ${thisUser.lname} </td>
<td> ${thisUser.email} </td>
<td> <form:input path="facId"/> </td>



</tr>
</table>
<input  class="btn btn-success" type="submit" name="register" value="Add Faculty" />

</div>
</div>

</form:form>



</body>
</html>