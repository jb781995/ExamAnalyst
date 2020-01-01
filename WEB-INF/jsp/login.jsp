<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<form action="login.html" method="post">
<!-- action="<c:url value='/login' />" -->
<table border="1">
<tr>
  <th>Email:</th>
  <td><input type="text" name="email" /></td>
</tr>
<tr>
  <th>Password:</th>
  <td><input type="password" name="password" /></td>
</tr>
<tr>
  <th><br /></th>
  <td><input type="submit" name="login" value="Login"/></td>
</tr>
</table>
<a href="<c:url value="forgotPwd.html"/>">Forgot Password ? </a>
</form>

</body>
</html>