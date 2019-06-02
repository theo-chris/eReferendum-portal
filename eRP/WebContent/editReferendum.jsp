<%@ page import="Domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Edit Page</title>

    <script type="text/javascript">


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
    </script>
</head>
<body>
<h1>Edit Page</h1>

<form action="./choiceEdit.jsp" method="GET">
    <table>
        <tr>
            <td class="details">Question:</td>
            <td><textarea name="ta" rows="1" cols="20" required></textarea></td>
        </tr>
        <tr>
            <td class="details">Number of choices:</td>
            <td><input type="text" name="optionNum"   required/></td>

        </tr>

    </table>
    <%--<input type="hidden" id="questionStr" name="questionStr" value="<%=request.getParameter("refQuest")%>"/>--%>

    <input type="submit">
</form>
</body>
</html>
