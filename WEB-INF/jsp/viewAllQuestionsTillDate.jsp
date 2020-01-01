<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<jsp:include page="common/Fheader.jsp" ></jsp:include>

<div class="panel panel-default" style="width:850px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px"></div>
  <div class="panel-body">
    <table  class="table" style="font-size:18px;width:700px;margin:0px auto;background-color:DarkGray">
    <tr>
    <th>Question Number </th>
<th>Topic </th>
<th>Questions </th>
<th>Answer </th> 
    
</tr>
<c:forEach items="${workTillDay}" var="wtd" varStatus="loop">
<tr>
<td> ${loop.index+1}</td>
<td> ${wtd.topic}</td>
<td> ${wtd.question}</td>
<td> ${wtd.correctOption}</td>
</tr>
</c:forEach>
</table>
</div>
</div>

</body>
</html>