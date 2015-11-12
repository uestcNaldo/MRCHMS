<%--
  Created by IntelliJ IDEA.
  User: 11656
  Date: 2015/11/11
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
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
  <link href="css/doctor_detail.css" rel="stylesheet">
  <link href="css/patient_detail_p.css" rel="stylesheet">
</head>
<body>
<div class="detail-head">
  <h2>Patient Details</h2>
</div>

<div class="detail-doctor">
  <div class="detail-name">
    <p>Name:<span id="doctor-name"><%=p_name%></span></p>
  </div>
  <div class="detail-name">
    <p>Age:<span id="doctor-age"><%=p_age%></span></p>
  </div>
  <div class="detail-name">
    <p>Gender:<span id="doctor-gender"><%=p_gender%></span></p>
  </div>
</div>

<div class="patient-mr">
  <h3>Here are your medical records history information</h3>
  <table border="1" width="100%" cellpadding="10">
    <tr>
      <th align="left">主诉:</th>
      <td><%=m_cc%></td>
    </tr>
    <tr>
      <th align="left">现病史:</th>
      <td><%=m_curh%></td>
    </tr>
    <tr>
      <th align="left">既往史:</th>
      <td><%=m_lsth%></td>
    </tr>
    <tr>
      <th align="left">个人史:</th>
      <td><%=m_perh%></td>
    </tr>
    <tr>
      <th align="left">家族史:</th>
      <td><%=m_famh%></td>
    </tr>
  </table>

</div>

</body>
</html>
