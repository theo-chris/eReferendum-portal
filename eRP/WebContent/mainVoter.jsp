<%@ page import="Domain.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% HttpSession session1 = request.getSession();
    User voter = null;
    if(session1.getAttribute("user") != null){
        voter = (User) session1.getAttribute("user");
        if(voter.getRole() != 1){
            response.sendRedirect("error.jsp?id=6");
        }
    }else{
        response.sendRedirect("error.jsp?id=1");
    }

    if(voter !=  null){
%>
<html>
<head>
    <title>El</title>


</head>
<body>
<h1>Voter Page</h1>

<h3>Greetings, <%=voter.getFullName()%></h3>
<br>


<form action="./vote">

    <input type="submit" value="Vote" />
</form>



<a href="/logout" ><input type="submit" value="Logout" /></a>
</form>
</body>
</html>
<%}%>