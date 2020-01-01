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

<h2>Regular MideSem For <i> Subject: ${particularSubDate.subId.subjectName}</i> </h2> </br> </br>
<h3> Following are guidlines and rules for exams </h3>

<h4> <b> Rules: </b> </h4></br>
- Do not use Any kind of book or dictionary  </br>
- Do not use internet for any kind of help   </br> </br>
<h4> <b> GuidLines: </b> </h4></br>
-Next:&nbsp;&nbsp;&nbsp;By Clicking on this link, you save your answer and move on to next Question </br>
-Previous:&nbsp;&nbsp;&nbsp;By Clicking on this link, You can move on to previous Question and change your Answer if you want</br>
-Mark:&nbsp;&nbsp;&nbsp;By Clicking on this link, You mark it to be reviewed later  </br>
-Review:&nbsp;&nbsp;&nbsp;This shows you entire palette of questions through which you can directly jump on any question</br>
-End Test:&nbsp;&nbsp;&nbsp; This brings your exam to an end, after which you will not be able to do any changes and your answers will be submitted on server.</br> </br> </br> </br>
<a href="<c:url value="beginExam.html"/>"> Begin Exam</a>&nbsp;&nbsp;&nbsp;
<a href="<c:url value="studentProfile.html"/>"> Back</a>
</body>
</html>