<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    Cookie cookies[] = request.getCookies();
    String email = "";
    if(cookies!=null){
        for(int i=0;i<cookies.length;i++)
            if(cookies[i].getName().equals("userEmail")){

                email = cookies[i].getValue();
            }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>eRP</title>
</head>
<body>
<h1>Welcome to eRP.</h1>
<p><b>Please enter your details to sign in</b></p>
    <form action="./error" method="POST">

        <tr>
            <td><label for="email">Email</label></td>
            <td><input type="text" value="<%=email%>" name="email" id="email" /></td>
        </tr>
        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" name="password"id="password" /><br/></td>
        </tr>
        <br>
        <tr>
            <td><input type="submit" value="Login" /></td>

        </tr>

        <span id="errorCode" style="color:#ff0000">${errorMessage}</span>

    </form>
    <br>
     <a href="signup.jsp">Sign Up!</a>


</body>
</html>
