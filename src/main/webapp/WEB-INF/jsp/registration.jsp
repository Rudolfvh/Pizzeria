<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br/>
    <label for="pwd">Password:
        <input type="password" name="pwd" id="pwd">
    </label><br/>
    <label for="phone">Phone:
        <input type="text" name="phone" id="phone">
    </label><br/>
    <select name="role" id="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option label="${role}">${role}</option>
            <br>
        </c:forEach>
    <input type="submit" value="Send">
</form>
<form action="${pageContext.request.contextPath}/login" method="get">
    <button type="submit">Back</button>
</form>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>