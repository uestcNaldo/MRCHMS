<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/12
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  String p_id = (String) request.getAttribute("p_id");
  String p_name = (String) request.getAttribute("p_name");
  int p_age = (int) request.getAttribute("p_age");
  String p_gender = (String) request.getAttribute("p_gender");

%>
<html>
<head>
  <title>Search Result --- MRIMS</title>
  <base href="<%=basePath%>" target="_blank">
  <link href="css/search-result.css" rel="stylesheet">
</head>
<body>
<div class="search-head">
  <h2>Here are results</h2>
</div>
<div class="search-result">
  <table class="result-table" border="1" width="100%">
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>Gender</th>
    </tr>
    <tr>
      <td><a href="${pageContext.request.contextPath}/servlet/OperateingServlet?action=show&pid=<%=p_id%>"><%=p_name%></a></td>
      <td><%=p_age%></td>
      <td><%=p_gender%></td>
    </tr>
  </table>
</div>
</body>
</html>
