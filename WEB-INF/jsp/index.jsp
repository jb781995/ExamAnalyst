<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="../css/superHero.css">
<jsp:include page="common/head.jsp" ></jsp:include>
<jsp:include page="common/header.jsp" ></jsp:include>

</br>
</br>
</br>
</br>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body style="background-color:#2b3e50;color:#ebebeb;font-size:20px">




 <form action="login.html" method="post">

<div class="container">
<div >
<div style="text-align:center;width:100%;"><h2 style="padding-left:10px;"> Welcome LDians...!!!! </h2></div><br>
<table  class="table" style="width:450px;margin:0px auto;font-size:20px">
 
<tr>
  <th style="text-align:left">Email :</th>
  <td><input type="text" name="email" class="form-control"/></td>
</tr>
<tr>
  <th style="text-align:left;">Password :</th>
  <td><input type="password" name="password" class="form-control" /></td>
</tr>
<tr>
  <th><br /></th>
  <td style="text-align:center"><input type="submit" name="login" value="Login" class="btn btn-default" style="width:150px" />&nbsp;&nbsp;
  </td>
</tr>
<tr>
<td>
</td>
<td style="text-align:center">
  <a href="<c:url value="forgotPwd.html"/>">Forgot Password ? </a></td>
</tr>
</table>
<br>
<div style="text-align:center;width:100%">
New User?&nbsp;&nbsp;&nbsp;<a href="<c:url value='register.html' />"> Register </a></div> 
</div>
</div>
</form>

 <!--  <a href="<c:url value='login.html' />"> Login </a> -->
</body>
</html>