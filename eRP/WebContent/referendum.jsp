<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
         import="Domain.McQuestion"  import="Domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.McOptions" %><%--
  Created by IntelliJ IDEA.
  User: mortu
  Date: 22/11/2018
  Time: 01:07
  To change this template use File | Settings | File Templates.
--%>
<% HttpSession session1 = request.getSession();
    User voter = null;

    if(session1.getAttribute("user") != null){
        voter =(User) session1.getAttribute("user");


        if(voter.getRole() != 1){
            response.sendRedirect("error.jsp?id=6");
        }
    }else{
        response.sendRedirect("error.jsp?id=1");
    }
    %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Title</title>
</head>
<body>

<%
    McQuestion refsQuestion = null;

    if(session1.getAttribute("refQuestion") != null){


        refsQuestion = (McQuestion) session1.getAttribute("refQuestion");

    %>

<h2>Referendum</h2>
<br>
<h3>You can only vote once and choose one option.</h3>
<br>
<p><%= refsQuestion.getQuestionStr() %></p>

<form action="/collectVote" method="POST">

    <%

        List<McOptions> choices = refsQuestion.getChoices();

       for(int counter = 0 ; counter < choices.size(); counter++){
           McOptions option = (McOptions)choices.get(counter);

           int id = option.getOptionID();
           String optionStr = option.getOption();
    %>

    <input type="radio" name="optionID" value="<%= id%>"/> <%=optionStr%>
    <%--Used to pass needed information to the RefVote servlet.--%>
    <input type="hidden" name="refID" value="<%=refsQuestion.getQuestionID()%>"/>
    <%--<input type="hidden" name="refQuest" value="<%=refsQuestion.%>"/>--%>
    <br>
    <%}%>
    <input type="submit" value="Submit"/>



</form>

</body>
</html>
<%}%>