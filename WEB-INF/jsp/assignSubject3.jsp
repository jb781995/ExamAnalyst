<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" />"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <p> Hello </p>
 
 <c:forEach items="${semesters}" var="semester">
 <form:checkbox path="sem" value="${ semester.semester}" onclick="showSubject()" class="semester" /> sem ${ semester.semester} </br>
 </c:forEach>
 
 <script src="<c:url value="resources/javascript/ajax2.js" />"></script>
</body>
</html>