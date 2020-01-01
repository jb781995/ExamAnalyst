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
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<div class="panel panel-default" style="width:1000px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Students List:</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:900px;margin:0px auto;background-color:DarkGray">
<tr>
<th>Enrollment No. </th>
<th> Name </th>
<th>Action </th>
</tr>
<c:forEach items="${studentsList}" var="students">
<tr>
<td> ${students.enrollmentNo}</td>
<td> ${students.stuUser.fname}&nbsp;&nbsp;${students.stuUser.lname}</td>
<td> <a href="<c:url value="viewRep.html?stuId=${students.id}"/>">View Report </a></td>
</tr>
</c:forEach>
</table>
</div>
</div>

</body>
</html>