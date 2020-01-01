<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Paper</title>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
<form action="<c:url value="saveAndNext.html?thisQNo=${counter}"/>" method="POST">
<h2> Hello..${faculty.fname} ${faculty.lname} </h2>

<div class="panel panel-default" style="width:800px;margin-left:50px;margin-top:30px;background-color:DarkGray">
  <div id="headerdiv" class="panel-heading" style="font-size:20px"><b>Set Questions For: <i> ${thisSub.subjectName }</i> </b></div>
  <div class="panel-body">

<!--<c:set var="count" scope="session" value="${count + 1}"/>-->
Topic Name:<input type="text" name="topic" value="${previousQuestion.topic}" /> </br></br></br>
Type Question Number:${index}</br><textarea rows="7" cols="90" name="question" >${previousQuestion.question}</textarea> </br></br>
Only Give The Option:(A or B or C or D)</br> <input type="text" name="correctOption" value="${previousQuestion.correctOption}"/> </br></br>



<input class="btn btn-success" type="submit" name="next" value="next"/>&nbsp;&nbsp;&nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="showPrevious.html?currentQuestion=${counter}"/>">Show Previous </a><!--  <input type="button" name="previous" value="Show Previous"/>-->&nbsp;&nbsp;&nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="showWholeList.html"/>">Show Whole List </a> &nbsp;&nbsp;&nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="returnFacultyProfile.html"/>"> Exit </a>&nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="facultyProfile.html"/>"> back </a>&nbsp;&nbsp;
<a class="btn btn-success" href="<c:url value="viewAllQuestionsTillDate.html"/>">My Work Till Now </a>

</div>
</div>
</form>
</body>
</html>