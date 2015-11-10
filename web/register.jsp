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
</head>
<body>
<div class="register-head">
  <h1 class="head-title">MRIMS Register</h1>
  <p class="head-instrct">Welcome to register account.</p>
</div>

<div class="register-body">
  <form method="post" action="${pageContext.request.contextPath}/servlet/OperateingServlet" id="register-form">
    <div class="register-username">
      Username:&nbsp;&nbsp;<input type="text" name="register-username">
    </div>
    <div class="register-password">
      Password:&nbsp;&nbsp;&nbsp;<input type="text" name="register-password">
    </div>
    <div class="register-password">
      Confirm Password:&nbsp;<input type="text" name="register-confPassword">
    </div>
    <div class="register-name">
      Name:<input type="text" name="register-name">
    </div>
    <div class="register-age">
      Age:<input type="number" name="register-age">
    </div>
    <div class="register-gender">
      
    </div>
  </form>
</div>
</body>
</html>
