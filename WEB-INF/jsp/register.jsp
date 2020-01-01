<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="../css/superHero.css">

<jsp:include page="common/head.jsp" ></jsp:include>
<jsp:include page="common/header.jsp" ></jsp:include>
<jsp:include page="common/scripts.jsp"></jsp:include>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<br>

<form:form modelAttribute="user" onsubmit="return runValidate();">
<div class="panel panel-default" style="width:400px;margin-left:100px;margin-top:30px">
  <div id="headerdiv1" class="panel-heading" style="font-size:20px" onclick="toggleDiv(body1)">
  
  <a href="javascript:toggleDiv('body1');" style="padding: 5px 10px;color:white"> Registrations</a></div>
  <div id="body1" class="panel-body">
    <table  class="table" style="font-size:18px;background-color:DarkGray">
<tr>
  <th>Email:</th>
  <td><form:input path="email" id="email1" />             
  </tr>
<tr>
  <th>Password:</th>
  <td><form:password path="password" required=""  />      
</tr>

<tr>
  <th>Last Name:</th>
  <td><form:input path="lname" />         
</tr>
<tr>
  <th>First Name:</th>
  <td><form:input path="fname" />         
</tr>


 


<tr>
  <td><br /></td><td><input type="submit" name="register" value="register" id="submit1" /></td>
</tr>
</table>
</div>
</div>
</form:form>

<%-- <script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
    <script src="<c:url value="resources/javascript/validation.js" />"></script> --%>

</body>
</html>
