<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: all
  Date: 13.02.2022
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../../resources/css/registration.css">
</head>
<body>
<security:authorize access="!isAuthenticated()">

    <br>
    <c:form method="post" modelAttribute="userForm" action="/registration">
        <label><b>Username</b><input type="text" placeholder="Enter Username" name="username" required></label>
        <c:errors path="username" cssClass="error"/>

        <label><b>Email</b><input type="text" placeholder="Enter Email" name="email" required></label>
        <c:errors path="email" cssClass="error"/>

        <label><b>Password</b><input type="password" placeholder="Enter Password" name="password" required></label>
        <c:errors path="password" cssClass="error"/>

        <label><b>Confirm Password</b><input type="password" placeholder="Confirm Password" name="confirmPassword" required></label>
        ${confirmPassNotEquals}

        <label><b>Firstname</b><input type="text" placeholder="Enter Firstname" name="firstname" required></label>
        <c:errors path="firstname" cssClass="error"/>

        <label><b>Lastname</b><input type="text" placeholder="Enter Lastname" name="lastname" required></label>
        <c:errors path="lastname" cssClass="error"/>

        <button type="submit">Registration</button>
        <ul>Already registered? <a href="/login">login</a></ul>
    </c:form>
</security:authorize>
<security:authorize access="isAuthenticated()">
    You are already registered.
</security:authorize>


</body>
</html>
