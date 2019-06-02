<%--
  Created by IntelliJ IDEA.
  User: mortu
  Date: 21/11/2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oops! Error has occurred</title>
</head>
<body>
<h2>Oops! Something went wrong.</h2>
    <%int id = Integer.parseInt(request.getParameter("id")); %>
    <%--Case login password or email wrong--%>
    <% if(id==0){ %>
    <p>Email or password are <b>incorrect.</b></p>
    <p>Click <a href="./login.jsp"><b>back to login</b></a> to try again</p>
    <%--Case not authorized--%>
    <%}if(id==1){%>
    <p>You do not meet the requirements to view this page.</p>
    <p>Click <a href="./login.jsp"><b>back to login</b></a> to try again</p>

    <%--Case email not valid--%>
    <%} if (id==2) {%>

    <p>Email either incorrect or already used.</p>
    <p>Make sure your email is of the form my@email.whatever</p>
    <p><a href="./signup.jsp"><b>Back to registration</b></a></p>
    <%--Case BIC invalid or used--%>
    <%} if(id==3) {%>
    <p>The BIC is either invalid or already used!</p>
    <p><a href="./signup.jsp"><b>Back to registration</b></a></p>
    <%--Case age of year less than 16--%>
    <%} if(id==4) {%>
    <p>You are not eligible to register.</p>
    <p>You must be over 16 years old.</p>
    <p>Format: <b>yyyy-mm-dd</b></p>
    <p><a href="./signup.jsp"><b>Back to registration</b></a></p>

    <%} if(id==5) {%>
    <p>You have already voted!</p>
    <p><a href="./mainVoter.jsp"><b>Back to main page.</b></a></p>


    <%} if(id==6) {%>
    <p>You are not allowed to view this page.</p>
    <p><a href="./logout"><b>Logout</b></a></p>

    <%}%>


</body>
</html>
