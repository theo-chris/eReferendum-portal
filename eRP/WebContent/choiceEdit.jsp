<%@ page import="Domain.User" %>
<%@ page import="Domain.McOptions" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.McQuestion" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mortu
  Date: 22/11/2018
  Time: 05:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% HttpSession session1 = request.getSession();
        User electionCom = null;
        if (session1.getAttribute("user") != null){
            electionCom = (User) session1.getAttribute("user");

            if (electionCom.getRole() !=0){
                response.sendRedirect("error.jsp?id=6");
            }
        }else{
            response.sendRedirect("error.jsp?id=1");
        }
    %>
    <title>Title</title>
</head>
<body>
<form action="./doneEdit" method="POST">
<table>
  <%

      if(request.getParameter("optionNum") != null){

      for(int i =0; i <Integer.parseInt(request.getParameter("optionNum")); i++) {


  %>
   Choice Number: <%= i+1%><textarea name="<%=i%>">  </textarea> <br>


    <input type="hidden" id="num" name="num" value="<%=Integer.parseInt(request.getParameter("optionNum"))%>"/>
    <input type="hidden" id="questStr" name="questStr" value="<%=request.getParameter("ta")%>"/>

<%}}%>

    </table>
<br>
<input type="submit" name="submit" value="Confirm choices"/>
</form>
</body>
</html>
