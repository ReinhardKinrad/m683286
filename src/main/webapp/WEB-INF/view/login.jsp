<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../../resources/css/loginstyles.css">
</head>
<body>
<security:authorize access="!isAuthenticated()">
    <div class="parent">
        <f:form action="/login" method="post">
            <label><b>Username</b> <input type="text" placeholder="Enter Username" name="username" required></label>
            <label><b>Password</b><input type="password" placeholder="Enter Password" name="password" required></label>
            <button type="submit">Login</button>
            <ul>Not registred? <a href="/registration">reg</a></ul>
            <ul><a href="/show-items">log in as a guest</a></ul>
        </f:form>
    </div>
</security:authorize>
<security:authorize access="isAuthenticated()">
    You are already logged in.
</security:authorize>

</body>
</html>