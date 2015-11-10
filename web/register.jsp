<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/6
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register - Medical Record Information Management System</title>
  <link href="css/register.css" rel="stylesheet">
  <script language="JavaScript">
    function compPassword(password_conf){
      var password = document.getElementById("password_conf").value;
      var hint = document.getElementById("confPass");
      if(password != password_conf){
        hint.innerHTML = "Passwords don't match";
      }
    }
  </script>
</head>
<body>
<div class="register-head">
  <h1 class="head-title">MRIMS Register</h1>
  <p class="head-instrct">Welcome to register account.</p>
</div>

<div class="register-body">
  <form method="post" action="${pageContext.request.contextPath}/servlet/OperateingServlet?action=register" id="register-form">
    <div>
      Select Role:<label>
      <input type="radio" name="role" value="doctors">
    </label> Doctor
      <label>
        <input type="radio" name="role" value="patients">
      </label> Patient
    </div>
    <div class="register-username">
      <label>
        Username:&nbsp;&nbsp;<input type="text" name="register-username" required>
      </label>
    </div>
    <div class="register-password">
      <label>
        Password:&nbsp;&nbsp;&nbsp;<input type="text" name="register-password" id="password"  onkeyup="compPassword(this.value)"required>
      </label>
    </div>
    <div class="register-password">
      <label>
        Confirm Password:&nbsp;&nbsp;<input type="text" name="register-confPassword" id="password_conf" required><span id="confPass"></span>
      </label>
    </div>
    <div class="register-name">
      <label>
        Name:&nbsp;&nbsp;<input type="text" name="register-name" required>
      </label>
    </div>
    <div class="register-age">
      <label>
        Age:&nbsp;&nbsp;&nbsp;<input type="number" name="register-age" required>
      </label>
    </div>
    <div class="register-gender">
      <label>
        Gender:<input type="radio" name="sex" value="male"> Male
        <input type="radio" name="sex" value="female"> Female
      </label>
    </div>
    <input type="submit" value="Register" class="register-submit">
  </form>
</div>
</body>
</html>
