<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--подключить стороннюю библеотеку под определенным префиксом--%>
<%--<%@taglib prefix="c" uri="" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--включить другой jsp в состав этого--%>
<%--<%@include file="index.jsp"%>--%>
<html>
<head>
  <title>Заказы</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Заказы: </h1>
<ul>
  <c:if test="${not empty requestScope.orderlist}">
    <c:forEach var="order" items="${requestScope.orderlist}">
      <li>${fn:toLowerCase(order.getPizza().getName())}</li>
      <li>${fn:toLowerCase(order.getDateGet())}</li>
    </c:forEach>
  </c:if>
</ul>
</body>
</html>
