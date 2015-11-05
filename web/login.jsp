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
    <title>Login In --- Medical Record Information Management System</title>

</head>
<body>
<h1>This is Login JSP</h1>
<form method="post" action="servlet/OperateingServlet">
  <label>
    <span>UserName:</span><input type="text" name="username">
  </label>
  <label>
    <span>PassWord:</span><input type="password" name="password">
  </label>
  <input type="submit" value="Submit">
</form>
</body>
</html>
