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



<table border="1">
<tr>
<th>Question Number </th>
<th>Topic </th>
<th>Questions </th>
<th>Answer </th> 
<th>Action </th>
</tr>
<c:forEach items="${qlist}" var="listOfQuestions" varStatus="loop">
<tr>

<td id="no${loop.index+1}">${loop.index+1}</td>
<!--  <td>${listOfQuestions.topic} </td>-->
<td><input type="text" name="topic" value="${listOfQuestions.topic}"
						id="topic${loop.index+1}"  class="topic" disabled /> </td>
<!--  <td>${listOfQuestions.question} </td> -->
<td><textarea rows="7" cols="80" name="question" id="que${loop.index+1}" class="question" disabled>${listOfQuestions.question}</textarea> </td>
<!--  <td>${listOfQuestions.correctOption} </td> -->
<td> <input type="text" name="answer" value="${listOfQuestions.correctOption}"
						id="option${loop.index+1}" class="option"  disabled />  </td>
						<td id="actions${loop.index+1}"> <input type="button" class="edit" id="${loop.index+1}" value="Edit"/> </td>
</tr>
</c:forEach>
<a href="<c:url value="return.html?qNo=${youReturnedFromQuestion}"/>"> back </a>
</table>

<form action="<c:url value='/change.html' />" id="hide1" method="POST">
							
								<input type="hidden" id="Question" name="Que"
								value=" " />
								<input type="hidden" id="QTopic" name="Topic"
								value=" " />
								
								<input type="hidden" id="Answer" name="Option"
								value=" " />
								
								<input type="hidden" id="ID" name="QNo"
								value=" " />
							
						</form>
						
<form action="<c:url value='/delete.html' />" id="hide2" method="POST">
							
								<input type="hidden" id="QId" name="QueNo"
								value=" " />
								</form>
						
						

<script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
<script src="<c:url value="resources/javascript/editFromQuestionList.js" />"></script>

</body>
</html>