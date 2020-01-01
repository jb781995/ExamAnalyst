<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>${reviewerUser.fname }, Now you need to assign subjects to ${thisFaculty.fname} </h3>
<!--<form:form modelAttribute="subjectsList">-->
<p> List of Semesters:
<c:forEach items="${semesters}" var="sem">
<a href="<c:url value="thisSemester.html?sem=${sem.semester }&id=${thisFaculty.id }"/>"> semester ${sem.semester} , </a>
</c:forEach>
</p>

<c:forEach items ="${subjectsList}" var="subjects">
<form:checkbox path="subjectName" value="${subjects.subjectName}" />
<p> hi </p>
<p>${subjects.subjectName} </p>
</c:forEach>

<!--</form:form> --> 
</body>
</html>