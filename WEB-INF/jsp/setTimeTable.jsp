<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>
	<form>
		<div class="panel panel-default" style="width:800px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Set TimeTable</div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:800px;margin:0px auto;background-color:DarkGray">

			<tr>
				<th>Subject code</th>
				<th>Subject Name</th>
				<th>Date (DD/MM/YYYY)</th>
				<th>Type</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${subjects}" var="sub">
				<tr>
					<td id="subject${sub.id}">${sub.id}</td>
					<td>${sub.subjectName}</td>
					<td><input type="date" name="examDate" class="examDate"
						id="date${sub.id}" /></td>
					<td><input type="radio" name="selection" class="selection"
						value="Regular" checked="unchecked" /> Regular <input
						type="radio" name="selection" class="selection" value="Remedial"
						checked="unchecked" /> Remedial</td>
					<td><input type="button" name="${sub.id}" value="set"
						id="${sub.id}" class="setDate2" /> <%-- <a
					href="<c:url value="setDateForThisSub.html?sem=${sub.sem.id }&sub=${sub.id }"/>"
					class="setDate" id="${sub.id }">Set </a> --%></td>

						
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</form>
	<form action="<c:url value='/setDateForThisSub2.html' />" id="hide" method="post">
							
								<input type="hidden" id="hiddenDate" name="Date"
								value=" " />
								<input type="hidden" id="hiddenSubId" name="SubId"
								value=" " />
								
								<input type="hidden" id="hiddenExamType" name="type"
								value=" " />
							
						</form>
	<script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="resources/javascript/examDate.js" />"></script>


	<a href="<c:url value="admin.html"/>">Back </a>
</body>

</html>