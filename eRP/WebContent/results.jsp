<%@ page import="Domain.User" %>
<%@ page import="Domain.McQuestion" %>
<%@ page import="Domain.McOptions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Results</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" ></script>
    <link  href ="CSS/css.css" type="text/css" rel="stylesheet"/>
    <script src="JQ/resultsChart.js"></script>

    <%
        HttpSession se = request.getSession();
        User user = null;
        McQuestion question = (McQuestion)se.getAttribute("mcQuestion");
        List<McOptions> answers = question.getChoices();
        Map<Integer,Float> percentages = (HashMap<Integer,Float>)se.getAttribute("percentages");
        Integer voteSum = (Integer)se.getAttribute("sumOfVotes");
        Map<Integer,Integer>votes = (HashMap<Integer,Integer>)se.getAttribute("votes");

        if(se.getAttribute("user")!=null){
            user = (User)se.getAttribute("user");
            if(user.getRole()!=0){
                response.sendRedirect("error.jsp?id=6");
            }
        }else{
            response.sendRedirect("error.jsp?id=1");
        }
    %>
    <%
        if(user!=null && user.getRole()==0){
    %>
</head>
<body>
<h3>
    Referendum Question:<br><h4><%=question.getQuestionStr() %></h4><br>
</h3>

<div id="chartWrapper">

    <ul id="chart">
        <%for(int i=1;i<=percentages.size();i++){%>
        <%=answers.get(i-1).getOption()  %><p>Percentage:  <%=percentages.get(i-0)%> </p> <br>
        <li title="<%=percentages.get(i) %>" class="print">
            <span class="line"></span>
            <span class="percentage"></span>
        </li>
        <%}%>

    </ul>

</div>
<div id="stats">
    <h2>Statistics</h2>
    <b>Total votes:</b><%=voteSum %><br><br>
    <%for (int i=0;i<answers.size();i++){%>
    <b><%=answers.get(i).getOption() %>:<%=votes.get(i+1) %></b><br><br>
    <%}%>
</div>
</body>
</html>
<%}%>