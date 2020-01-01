<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a href="<c:url value='index.html'/>" class="navbar-brand">Exam Analyst</a>
			<button class="navbar-toggle" type="button" data-toggle="collapse"
				data-target="#navbar-main">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="navbar-main">
			<c:if test="${sessionScope.user eq null }">
			<ul class="nav navbar-nav navbar-right">
				<%-- <li><a href="<c:url value='register.html'/>" >Register</a></li>
				<li><a href="<c:url value='login.html'/>"
					>Login</a></li> --%>
			</ul>
			</c:if>
			<c:if test="${sessionScope.user ne null }">
			<ul class="nav navbar-nav navbar-right">
			<li><a href="<c:url value='facultyProfile.html'/>">Home</a></li>
				<li class="dropdown" >
			 <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">My Tasks <span class="caret"></span></a>
			  <ul class="dropdown-menu" aria-labelledby="download">
			  
			  <li><a href="<c:url value='myTopics.html' />">Tell my partners about my Topics</a></li>
                <li><a href="<c:url value='editTopics.html' />">Edit my Topics</a></li>
                
			  </ul>
			 </li>
				<li><a href="<c:url value='index.html'/>">Log Out</a></li>
				
				
			</ul>
			</c:if>
			
			
			
			

		</div>
		<jsp:include page="scripts.jsp"></jsp:include>
	</div>
</div>
