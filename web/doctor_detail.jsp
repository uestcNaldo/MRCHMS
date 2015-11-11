<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/11
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  String d_name = (String)request.getAttribute("d_name");
  int d_age = (int)request.getAttribute("d_age");
  String d_gender = (String)request.getAttribute("d_gender");
%>
<html>
<head>
  <title>Doctor Details --- Medical Record Information Management System</title>
  <base href="<%=basePath%>">
  <link href="css/doctor_detail.css" rel="stylesheet">
</head>
<body>
<div class="detail-head">
  <h1>Doctor Details</h1>
  <p>You can search patients information</p>
</div>

<div class="detail-doctor">
  <div class="detail-name">
    <p>Name:</p><span id="doctor-name"><%=d_name%></span>
  </div>
  <div class="detail-name">
    <p>Age:</p><span id="doctor-age"><%=d_age%></span>
  </div>
  <div class="detail-name">
    <p>Gender:</p><span id="doctor-gender"><%=d_gender%></span>
  </div>
</div>

<div class="doctor-search">
  <form method="post" action="${pageContext.request.contextPath}/servlet/OperateingServlet?action=search" id="search-form">
    <input type="text" name="search-name">
    <span><input type="submit" value="Search"></span>
  </form>
</div>
<div class="patients-list">
  <table id="pTable" width="80%">
    <tr>
      <th>Name</th>
      <th>Gender</th>
      <th>Age</th>
    </tr>
    <tr>
      <td></td>
      <td></td>
      <td></td>
    </tr>
  </table>
</div>
</body>
</html>
