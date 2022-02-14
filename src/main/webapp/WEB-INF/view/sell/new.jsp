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
    <meta charset="utf-8">
</head>
<body>

<br>
<c:form method="post" action="/sell/new">
    <div><label>Title: <input type="text" name="title"/></label></div>
    <div><label>Description: <input type="text" name="description"/></label></div>
    <div><label>Stop date <input type="text" name="stopDate"/></label></div>
    <div><label>Start price <input type="text" name="startPrice"/></label></div>
    <div><label>Bid inc. <input type="text" name="bidInc"/></label></div>
    <div><input type="submit" value="Submit"/></div>
</c:form>
</body>
</html>
