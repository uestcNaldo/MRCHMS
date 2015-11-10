<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/4
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
  <title>Login --- Medical Record Information Management System</title>
  <base href="<%=basePath%>">
  <link href="css/login.css" rel="stylesheet">
  <script language="JavaScript">
    function detectID(id){
      var form=document.getElementById("login-form");
      if(id==0){
        form.action+="&ID=doctor";
      }
      if(id==1){
        form.action+="&ID=patient";
      }
      form.submit();
    }
  </script>
</head>
<body>
<div class="login-head">
  <h1 class="head-title">Medical Record Information System</h1>
  <p class="head-instrct">Welcome to MRIMS, login or register to enter system.</p>
</div>

<div class="login-table">
  <form method="post" action="servlet/OperateingServlet?action=login" id="login-form">
    <div class="login-username">
      UserName:&nbsp;<input type="text" name="login-username">
    </div>
    <div class="login-password">
      PassWord:&nbsp;&nbsp;<input type="password" name="login-password">
    </div>
    <input type="submit" value="Doctor-Login" class="login-submit" onclick="detectID(0)">
    <input type="submit" value="Patient-Login" class="login-submit" onclick="detectID(1)">
  </form>
  <form method="post" action="servlet/OperateingServlet?action=register">
    <input type="submit" value="Register" class="register-submit">
  </form>
</div>
</body>
</html>
