<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr>

<th> Question Number </th>
<th>Student's Answer </th> 
</tr>

<c:forEach items="${answersList}" var="list" varStatus="loop">

<tr>
<td>${loop.index+1} </td>
<td>${list.studentAnswer} </td>

</tr>


</c:forEach>


</table>
<a href="<c:url value="demo.html"/>"> back </a>

</body>
</html>