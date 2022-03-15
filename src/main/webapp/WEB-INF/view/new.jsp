<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: all
  Date: 13.02.2022
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<head>
    <link rel="stylesheet" href="../../resources/css/new.css">
    <link rel="icon" href="../../resources/ico/favicon.png">
    <title>Sell</title>
</head>
<body>
<div class="container">
    <div class="navbar">
        <a class="active" href="/show-items">Home</a>
        <security:authorize access="hasRole('ROLE_USER')">
            <a href="/show-items/my-items">Show my items</a>
            <a href="/show-items/my-bids">Show my bids</a>
            <a href="/sell/new">Sell</a>
            <div class="navbar-right">
                <a id="currentUser" class="user">${currentUser}</a>
                <a href="/logout">Logout</a>
            </div>
        </security:authorize>
    </div>

    <div class="form-container">

        <f:form id="lot-form" method="post" action="/sell/new" modelAttribute="lotForm">
            <p>
                <label>
                    <span>Title:</span>
                    <f:input type="text" path="item.title" />
                    <span class="error" aria-live="polite"></span>
                    <f:errors path="item.title" cssClass="error"/>
                </label>
            </p>
            <p>
            <label>
                <span>Description:</span>
                <textarea form="lot-form" name="item.description" class="input-description" maxlength="300"></textarea>
                <span class="error" aria-live="polite"></span>
                <f:errors path="item.description" cssClass="error"/>
            </label>
            </p>
            <p>
            <label>
                <span>Stop date:</span>
                <f:input type="date" path="stopDate"/>
                <span class="error" aria-live="polite"></span>
                <f:errors path="stopDate" cssClass="error"/>
            </label>
            </p>
            <p>
            <label>
                <span>Start price:</span>
                <f:input type="text" path="startPrice"/>
                <span class="error" aria-live="polite"></span>
                <f:errors path="startPrice" cssClass="error"/>
            </label>
            </p>
            <p>
            <label>
                <span>Bid inc:</span>
                <f:input type="text" path="bidInc"/>
                <span class="error" aria-live="polite"></span>
                <f:errors path="bidInc" cssClass="error"/>
            </label>
            </p>
            <button type="submit">Sell</button>
        </f:form>
    </div>
</div>
</body>
</html>
