<%@ page import="Domain.User" %>
<%@ page import="Domain.McQuestion" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%



    HttpSession session1 = request.getSession();

    User electionCom = null;
    if (session1.getAttribute("user") !=null){
        electionCom = (User) session1.getAttribute("user");
        if (electionCom.getRole()!= 0){
            response.sendRedirect("error.jsp?id=6");
        }
    }else {
        response.sendRedirect("error.jsp?id=1");
    }
    boolean status = false ;
    if (session1.getAttribute("status") != null) {


        status = (boolean) session1.getAttribute("status");
    }

    boolean percentage = false;
    if (session1.getAttribute("percentage") != null){
        percentage = (boolean)session1.getAttribute("percentage");
    }
    if(electionCom !=  null){
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title></title>
</head>
<body>
<h3>Greetings, <%=electionCom.getFullName()%></h3>

<p>Choose something to do:</p>
<%
    if (status){%>
<%--<%if (session1.getAttribute(request.getParameter("status")) == true)%>--%>


<form action="./startReferendum">
    <h4>Referendum has been stopped.</h4>
    <input type="submit" value="Start Referendum"/>
</form>
<%}else if(percentage){%>


<form action="./stopReferendum">
    <h4>Referendum has been started.</h4>
    <input type="submit" value="Stop Referendum"/>

</form>
<%}%>
    <form action="./editReferendum">
    <input type="submit" value="Edit Referendum question or options." />
    </form>

    <form action="./results">
        <input type="submit" value="View Results">
    </form>
    <form action="./logout">
        <input type="submit" value="Logout" />

    </form>
</body>
</html>
<%}%>