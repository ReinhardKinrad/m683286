<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: all
  Date: 13.02.2022
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:form method="post" action="/registration">
        <div><label>Username: <input type="text" name="username"/></label></div>
        <div><label>Password: <input type="password" name="password"/></label></div>
        <div><label>Email: <input type="email" name="email"/></label></div>
        <div><label>Firstname: <input type="text" name="firstname"/></label></div>
        <div><label>Lastname: <input type="text" name="lastname"/></label></div>
        <div><input type="submit" value="Submit"/></div>
    </c:form>
</body>
</html>
