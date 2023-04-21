<%--
  Created by IntelliJ IDEA.
  User: matya
  Date: 21.04.2023
  Time: 00:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<form action="${pageContext.request.contextPath}/order" method="post">
    <input type="radio" name="pizza_name" value="Цыплёнок барбекю" checked />Цыплёнок барбекю
    <input type="radio" name="pizza_name" value="Ранч пицца" checked />Ранч пицца
    <br>
    <input type="radio" name="pizza_name" value="Острая чили" checked />Острая чили
    <input type="radio" name="pizza_name" value="Пепперони" checked />Пепперони
    <br>
    <input type="radio" name="pizza_name" value="Цыплёнок дорблю" checked />Цыплёнок дорблю
    <input type="radio" name="pizza_name" value="Гавайская" checked />Гавайская
    <br>

    <button type="submit">Order</button>
</form>
<form action="${pageContext.request.contextPath}/logout" method="get">
    <button type="submit">LogOut</button>
</form>
<body>

</body>
</html>
