
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>--%>
    <%--<link href="CSS/meter.css" type="text/css" rel="stylesheet"/>--%>
    <%--<script src="JQ/meterJS.js"></script>--%>
    <title>eRP</title>
  </head>
  <body>
<h1><i>This is the registration page</i></h1>
<p><b>Please  fill the form to register.</b></p>
    <form action="./register" method="POST">
        <table>
          <tr>
            <td><label for="email">Email</label></td>
            <td><input type="text" name="email" id="email" required/></td>
          </tr>
          <tr>
            <td><label for="fullName">Full name</label></td>
            <td><input type="text"  id="fullName"name="fullName" required/></td>
          </tr>
          <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" id="password" name="password" required/></td>
          </tr>
          <tr>
            <td><label for="confirm">Confirm password</label></td>
            <td><input type="password" id="confirm" name="confirm" on required/></td>
          </tr>

          <tr>
            <td><label for="dob">Date of birth</label></td>
            <td><input type="text" id="dob" name="dob" required/></td>
          </tr>
          <tr>
            <td><label for="homeAddress">Home Address</label></td>
            <td><input type="text" id="homeAddress"  name="homeAddress" required/></td>
          </tr>
          <tr>
            <td><label for="BIC">BIC</label></td>
            <td><input type="text" id="BIC" name="BIC" required/></td>
          </tr>
          <tr>
            <td>
          <input id="submitBtn" type="submit" value="Submit Registration Form">
            </td>
          </tr>

          <%--<tr><td><meter max="4" id="password-strength-meter"></meter>--%>
            <%--<p id="password-strength-text"></p></td></tr>--%>

        </table>



      <span id="errorCode" style="color:#ff0000"></span>
    </form>
<a href="/login.jsp" ><input type="submit" value="Back"></a>

  <script>



      $("#email").change(function(){

          $.ajax({
              type: "POST",
              url: "./existingEmail",
              data:{email:$("#email").val()},
              success: function(answer){

                  var ret=jQuery.parseJSON(answer);

                  $('#errorCode').hide();

                  if(ret.existing_user==true){

                      $('#errorCode').html("Email is already used!");
                      $('#errorCode').show();
                      $("#submitBtn").prop('disabled', true);
                  }else{
                      $('#errorCode').hide();
                      $("#submitBtn").prop('disabled', false);
                  }

              },
              error: function(){
                  alert("Error occurred");
              }
          });
      });
      var password=$("#password");
      var confirm=$("#confirm");
      confirm.change(function(){
          if(confirm.val()!= password.val()){
              $('#errorCode').html("Password fields must match!");
              $('#errorCode').show();
              $("#submitBtn").prop('disabled', true);
          }else{

              $('#errorCode').hide();
              $("#submitBtn").prop('disabled', false);
          }
      });

      $("#BIC").change(function(){

          $.ajax({
              type: "POST",
              url: "./availableBIC",
              data:{BIC:$("#BIC").val()},
              success: function(answer){

                  var message=jQuery.parseJSON(answer);

                  $('#errorCode').hide();

                  if(message.BIC_AVAILABLE==false){

                      $('#errorCode').html("BIC used or invalid!");
                      $('#errorCode').show();
                      $("#submitBtn").prop('disabled', true);

                  }else{

                      $('#errorCode').hide();
                      $("#submitBtn").prop('disabled', false);
                  }

              },
              error: function(){
                  alert("Error occurred");
              }
          });
      });

  </script>
  </body>
</html>


