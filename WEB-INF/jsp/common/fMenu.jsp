<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
#not{
position:relative;
margin-left:-7%;
color: white;}
</style>

<nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li style="margin-left:5px" class="nav-header">
                    <div style="margin-left:35px" class="dropdown profile-element"> <span>
                          <image class="img" width="90" height="90" src="download.html?fileId=${sessionScope.user.transcript.id }"/>
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${sessionScope.user.fname} ${sessionScope.user.lname}</strong>
                            </span></span></a>
                    </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <li>
                    <a   href="<c:url value='student_Details.html' />"><i class="fa fa-th-large"></i> <span class="nav-label">Student Profile</span> </a>
                    
                </li>
                 <li>
                    <a href="<c:url value='viewnotice.html' />"><i class="fa fa-globe"></i> <span class="nav-label">Notifications</span><c:if test="${index gt 0 or sessionScope.readvar eq 0 }">
                <image class="img" width="18" height="18" src="img/not.png"/><font id="not">${index }</font></c:if></a>
                   
                </li>
                 <li>
                    <a href="<c:url value='ViewPractical.html' />"><i class="fa fa-edit"></i> <span class="nav-label">Practical Tasks</span>
                    <c:if test="${prac gt 0 or sessionScope.readvar eq 0 }">
                <image class="img" width="18" height="18" src="img/not.png"/><font id="not">${prac}</font></c:if>
                    </a>
                   
                </li>
                
                <li>
               
                    <a href="<c:url value='unsubmittedPrac.html' />"><i class="fa fa-edit"></i> <span class="nav-label">Pending Practicals</span>
                    <c:if test="${unp gt 0 or sessionScope.readvar eq 1}">
                <image class="img" width="18" height="18" src="img/not.png"/><font id="not">${unp}</font></c:if>
                    </a>
                   
                </li>
                <li>
                    <a href="<c:url value='viewmarks.html' />"><i class="fa fa-edit"></i> <span class="nav-label">View Marks</span></a>
                   
                </li>
                
               <%--  <li>
                    <a href="<c:url value='viewvideo.html' />"><i class="fa fa-edit"></i> <span class="nav-label">View video</span></a>
                   
                </li> --%>
                
                <li>
                    <a href="<c:url value='videoList.html' />"><i class="fa fa-table"></i> <span class="nav-label">Lecture Videos</span></a>
                    
                </li>
                
                
                 <li>
                <c:if test="${sessionScope.user.category ne 'mentor' and sessionScope.user.category ne 'HOD'  }">
                    <a href="<c:url value='leave_application.html' />"><i class="fa fa-files-o"></i> <span class="nav-label">Leave Application</span></a>
                    </c:if>
                    
                </li>
                
                 
                 <li>
                    <a href="<c:url value='changePwd.html?id=${sessionScope.user.id }' />"><i class="fa fa-laptop"></i> <span class="nav-label">Change Password</span></a>
                </li>
                
                               
                
            </ul>

        </div>
    </nav>
</html>