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
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 15px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Profile</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

    <script type="text/javascript">

    function toggleDiv(divId) {
      
       $("#"+divId).toggle();

    }

    </script>
</head>
<body>
<jsp:include page="common/Fheader.jsp" ></jsp:include>
<div class="container">

<div class="row">

  <div class="col-lg-6">
  <div class="well bs-component">
<h1> Welcome ${faculty.fname} ${faculty.lname } </h1>




<div class="panel panel-default" style="width:400px;margin-left:100px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Your Subjects</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">
<c:forEach items="${mySubjects}" var="subjects">
<tr>
<b> <i> <td> </nbsp>${subjects.lstsubject.subjectName} </td> <td>(sem ${subjects.lstsubject.sem.semester}):  </i> </b> </td> <td><a href="<c:url value="setPaperForThisSub.html?subId=${subjects.lstsubject.id }"/>"> Set Paper</a> </br> </td>
</tr>
</c:forEach>  
</table>
</div>
</div>

<div class="panel panel-default" style="width:400px;margin-left:100px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">See Result Of:</div>
  <div class="panel-body">


<c:forEach items="${mySubjects}" var="sem">
<a href="<c:url value="resultOfYourStu.html?sem=${sem.lstsubject.sem.semester}&subId=${sem.lstsubject.id}"/>">&nbsp;Semester${sem.lstsubject.sem.semester} </a></br>
</c:forEach>
</div>
</div>

<div class="panel panel-default">
  <div id="headerdiv2" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body2)"> 
  <a href="javascript:toggleDiv('body2');" style="padding: 5px 10px;color:white">Your Subject TimeTable</a></div>
  <div  id="body2" class="panel-body">

<table class="table" style="font-size:18px;background-color:DarkGray">
<tr>
<th>Date </th>
<th>Subject </th>
<th>Type </th>
</tr>
<c:forEach items="${facultyTimeTale}" var="tt">
<tr>
<td>${tt.date} </td>
<td> ${ tt.subId.subjectName}</td>
<td> ${tt.examType}</td>
</tr>
</c:forEach>
</table>
</div>
</div>


   
   
   <div class="panel panel-default">
  <div id="headerdiv3" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body3)"> 
  <a href="javascript:toggleDiv('body3');" style="padding: 5px 10px;color:white">My Partners</a></div>
  <div  id="body3" class="panel-body">

<table class="table" style="font-size:18px;background-color:DarkGray">
<tr>
<th>Partner's Name </th>
<th>Subject </th>
</tr>
<c:forEach items="${Partners}" var="p">
<tr>
<td>${p.user.fname} &nbsp;&nbsp; ${p.user.lname} </td>
<td>${p.lstsubject.subjectName} </td>
</tr>
</c:forEach>
</table>
</div>
</div>

<!-- <a href="<c:url value="myTopics.html"/>">Tell my partners about my Topics </a>  -->

 <div class="panel panel-default" style="width:800">
  <div id="headerdiv4" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body4)"> 
  <a href="javascript:toggleDiv('body4');" style="padding: 5px 10px;color:white">My Partner's Topic Selection</a></div>
  <div  id="body4" class="panel-body">

<table class="table"  style="font-size:18px;background-color:DarkGray;border-color: black;">
<tr>
<th style="bordercolor=black;background=black">Partner's Name </th>
<th>Subject </th>
<th>Topic chosen </th>
</tr>
<c:forEach items="${partnersTopicList}" var="tl">
<tr>
<td>${tl.user.fname}&nbsp;&nbsp;${tl.user.lname} </td>
<td> ${tl.sub.subjectName}</td>
<td> ${tl.topics}</td>
</tr>
</c:forEach>
</table>

<!--  <a href="<c:url value="myTopics.html"/>">Tell my partners about my Topics </a> -->

<%-- <a href="<c:url value="editTopics.html"/>">Edit my Topics </a> --%>
</div>
</div>

	

</body>
</html>