<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Your Exam Topics</title>

<script type="text/javascript">

</script>
</head>
<body>
<jsp:include page="common/Fheader.jsp" ></jsp:include>

<br>
<br>
<div class="panel panel-default" style="width:850px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px"></div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:700px;margin:0px auto;background-color:DarkGray">
<tr>
<th> Subject Id </th>
<th> Semester</th>
<th>Subject </th>
<th>Topics You want to Set </th>
<th>Let Your Partner Know </th>
</tr>
<c:forEach items="${setTopicForTheseSubjects}" var="st">
<tr>
<td><input type="text" value="${st.lstsubject.id}" id="s${st.id}" size="4" disabled/> </td>
<td> <input type="text" value="${st.lstsubject.sem.id}" id="sem${st.id}" size="4" disabled/></td>
<td> ${st.lstsubject.subjectName }</td>
<td> <input type="text" id="topic${st.id}"/></td>
<td> <input type="button" value="Ok" class="btn btn-success topic"  id="${st.id}"></td>
</tr>
</c:forEach>
</table>
</div>
</div>
<br>
<br>


<form action="<c:url value="tellMyTopics.html"/>" method="POST" id="hide">
		<input type="hidden" id="subID" name="id" value=" " /> 
		<input type="hidden" id="topics" name="topic" value=" " /> 
		<input type="hidden" id="sem" name="semester" value=" " /> 
		
	</form>

<%-- <table border="1">
<tr>
<th> Subject Id </th>
<th> Semester</th>
<th> Subject</th>
<th> Edit Topic</th>
<th> Action</th>
</tr>
<c:forEach items="${topicsListIHaveSet}" var="tl" >
<tr>
<input type="hidden" value="${tl.id}" id="h${tl.id}"/>
<td><input type="text" value="${tl.sub.id}" id="s${tl.id}" disabled/> </td>
<td><input type="text" value="${tl.sub.sem.id}" id="sem${tl.id}" disabled/> </td>
<td><input type="text" value="${tl.sub.subjectName}" id="sub${tl.id}" disabled/> </td>
<td><input type="text" value="${tl.topics}" id="t${tl.id}" disabled/> </td>
<td id="row${tl.id}"> <input type="button" class="editt" value="Edit" id="e${tl.id}" /></td>
</tr>
</c:forEach>
</table>


	
	<form action="<c:url value="editMyTopics.html"/>" method="POST" id="hide2">
		 
		<input type="hidden" id="topics" name="topic" value=" " /> 
		
		<input type="hidden" id="obj" name="thisObj" value="" />
		
	</form>
	
	<form action="<c:url value="deleteMyTopics.html"/>" method="POST" id="hide3">
		 
		<input type="hidden" id="delObj" name="Obj" value="" />
	</form>
 --%>
    <script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="resources/javascript/tellAboutMyTopics.js" />"></script>
	  <!--  <script src="<c:url value="resources/javascript/editMyTopics.js" />"></script> --> 
</body>
</html>