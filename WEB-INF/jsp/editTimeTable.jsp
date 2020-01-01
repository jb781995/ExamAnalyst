<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.dates {
	enable: false;
}
</style>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
	<form>
		<div class="panel panel-default" style="width:1000px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">EDit TimeTable</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:900px;margin:0px auto;background-color:DarkGray">

			<tr>
				<th>Id</th>
				<th>Subject</th>
				<th>Semester </th>
				<th>Date</th>
				<th>Type</th>
				<th id="action">Action</th>
			</tr>
			<c:forEach items="${listOfTimeTable}" var="list">
				<tr>
					<td id="number${list.id}">${list.id}</td>
					<td>${list.subId.subjectName}</td>
					<td>${list.subId.sem.id} </td>
					<td><input type="text" name="date" value="${list.date}"
						id="date${list.id}" class="dates" size="9" disabled /></td>
					<td><input type="text" name="type" size="10" value="${list.examType}"
						id="type${list.id}" class="examType" disabled /></td>
					<td id="row${list.id}"><input  type="button" name="enable"
						value="Edit" id="${list.id}" class="btn btn-success edit" />&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</form>

	<form action="<c:url value="deleteTimeTab.html"/>" method="POST"
		id="hide2">
		<input type="hidden" id="Id" name="dateId" value=" " />

	</form>

	<form action="<c:url value="editTimeTab.html"/>" method="POST"
		id="hide">
		<input type="hidden" id="hiddenDate" name="Date" value=" " /> 
		<input type="hidden" id="hiddenExamType" name="type" value=" " /> 
		<input type="hidden" id="hiddenId" name="Id" value=" " />
	</form>

	<script
		src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="resources/javascript/editTimeTable.js" />"></script>

</body>
</html>