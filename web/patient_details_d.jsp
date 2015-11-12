<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/12
  Time: 13:14
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
  String m_cc = (String) request.getAttribute("m_cc");
  String m_curh = (String) request.getAttribute("m_curh");
  String m_lsth = (String) request.getAttribute("m_lsth");
  String m_perh = (String) request.getAttribute("m_perh");
  String m_famh = (String) request.getAttribute("m_famh");

%>
<html>
<head>
  <title>Patient Details --- MRIMS</title>
  <base href="<%=basePath%>" target="_blank">
  <link href="css/patient_detail_d.css" rel="stylesheet">
</head>
<body>
<div class="patient-head">
  <h2>Here are patient information</h2>
  <small>You can modify patient's medical records</small>
</div>
<div class="patient-details">
  <div class="patient-info">
    <label>Name: <%=p_name%></label>&nbsp;&nbsp;&nbsp;
    <label>Age: <%=p_age%></label>&nbsp;&nbsp;&nbsp;
    <label>Gender: <%=p_gender%></label>&nbsp;&nbsp;&nbsp;
  </div>
  <div class="patient-mr">
    <form action="servlet/OperateingServlet?action=modify" method="post" id="patient-mr">
    <table border="1" width="100%" cellpadding="10">
      <tr>
        <th align="left">主诉:</th>
        <td>
          <label><textarea name="m_cc" form="patient-mr" rows="10" cols="100"><%=m_cc%></textarea></label>
        </td>
      </tr>
      <tr>
        <th align="left">现病史:</th>
        <td>
          <label><textarea name="m_curh" form="patient-mr" rows="10" cols="100"><%=m_curh%></textarea></label>
        </td>
      </tr>
      <tr>
        <th align="left">既往史:</th>
        <td>
          <label><textarea name="m_lsth" form="patient-mr" rows="10" cols="100"><%=m_lsth%></textarea></label>
        </td>
      </tr>
      <tr>
        <th align="left">个人史:</th>
        <td>
          <label><textarea name="m_perh" form="patient-mr" rows="10" cols="100"><%=m_perh%></textarea></label>
        </td>
      </tr>
      <tr>
        <th align="left">家族史:</th>
        <td>
          <label><textarea name="m_famh" form="patient-mr" rows="10" cols="100"><%=m_famh%></textarea></label>
        </td>
      </tr>
    </table>
      <input type="hidden" name="p_id" value="<%=p_id%>">
      <input type="submit" value="Save">
    </form>
  </div>
</div>
</body>
</html>
