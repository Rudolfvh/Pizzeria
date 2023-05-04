<%--
  Created by IntelliJ IDEA.
  User: matya
  Date: 04.05.2023
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/menu" method="get">

  <a href="${pageContext.request.contextPath}/order">
    <button type="button">Order</button>
  </a>
  <br>
  <a href="${pageContext.request.contextPath}/orderlist">
    <button type="button">OrderList</button>
  </a>
  <br>
  <a href="${pageContext.request.contextPath}/logout">
    <button type="button">Logout</button>
  </a>
  <br>
</form>
</body>
</html>
