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
         isELIgnored="false" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="../../resources/css/registration.css">
    <link rel="icon" href="../../resources/ico/favicon.png">
</head>
<body>
<security:authorize access="!isAuthenticated()">
    <div class="container">
        <c:form method="post" modelAttribute="userForm" action="/registration">
            <p class="form-title">Registration</p>
            <p>
                <label for="username">
                    <span>Please enter an username:</span>
                    <input type="text" id="username" name="username" required minlength="5"
                           maxlength="15">
                    <span class="error" aria-live="polite"></span>
                    <c:errors path="username" cssClass="error"/>
                </label>
            </p>
            <p>
                <label for="email">
                    <span>Please enter an email address:</span>
                    <input type="email" id="email" name="email" required minlength="8"
                           maxlength="50">
                    <span class="error" aria-live="polite"></span>
                    <c:errors path="email" cssClass="error"/>
                </label>
            </p>
            <p>
                <label for="password">
                    <span>Please enter a password:</span>
                    <input type="password" id="password" name="password" required minlength="8">
                    <span class="error" aria-live="polite"></span>
                    <c:errors path="password" cssClass="error"/>
                </label>
            </p>
            <p>
                <label for="confirm-password">
                    <span>Please enter a confirm password:</span>
                    <input type="password" id="confirm-password" name="confirmPassword" required>
                    <span class="error" aria-live="polite"></span>
                        ${confirmPassNotEquals}
                </label>
            </p>
            <p>
                <label for="firstname">
                    <span>Please enter a firstname:</span>
                    <input type="text" id="firstname" name="firstname" required minlength="5">
                    <span class="error" aria-live="polite"></span>
                    <c:errors path="firstname" cssClass="error"/>
                </label>
            </p>
            <p>
                <label for="lastname">
                    <span>Please enter a lastname:</span>
                    <input type="text" id="lastname" name="lastname" required minlength="5">
                    <span class="error" aria-live="polite"></span>
                    <c:errors path="lastname" cssClass="error"/>
                </label>
            </p>
            <button>Registration</button>
            <ul>Already registered? <a href="/login">login</a></ul>
        </c:form>
    </div>
</security:authorize>
<security:authorize access="isAuthenticated()">
    You are already registered.
</security:authorize>


</body>
<script src="/resources/js/reg.js">
</script>
</html>
