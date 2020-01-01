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
<h2> Hello <b><i>${reviewerUser2.fname} ${reviewerUser2.lname} </i> </b> </h2>

<form action="<c:url value='/startEndDate.html' />" method="POST">
<table border=1>
<tr>
<th> Semester </th>
<th> From (DD/mm/YYYY)</th>
<th> To(DD/mm/YYYY)</th>
<th> Action </th>
</tr>


<tr>
<td><input type="text" name="sem" /> </td>
<td><input type="text" name="fromD"/> </td>
<td><input type="text" name="toD"/> </td>
<td><input type="submit" name="done" Value="Ok"  /> </td>

</tr>


</table>


</form>
<br>
<br>

<table border=1>
<tr>
<th> Semester </th>
<th> From</th>
<th> To</th>
<th> Action</th>
</tr>
<c:forEach items="${listOfTermDate}" var="li">
<tr>
<td><input type="text" value="${li.semester}" id= "sem${li.id}" disabled  /> </td>
<td><input type="text" value="${li.fromD}"  id= "from${li.id}" disabled /> </td>
<td><input type="text" value="${li.toD}"  id= "to${li.id}" disabled /> </td>
<td id="row${li.id}"><input type="button" name="edit" Value="Edit" id="${li.id}" class="edit"/> </td>

</tr>
</c:forEach>
</table>

<input type="text" value="${termDateStatus}">

<form action="<c:url value="editTermDate.html"/>" method="POST"
		id="hide">
		<input type="hidden" id="id" name="id" value=" " /> 
		<input type="hidden" id="semester" name="sem" value=" " /> 
		<input type="hidden" id="from" name="from" value=" " /> 
		<input type="hidden" id="to" name="to" value=" " />
	</form>

<script
		src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="resources/javascript/termDate.js" />"></script>


</body>
</html>