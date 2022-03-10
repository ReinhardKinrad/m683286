<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
    <title>Title</title>
</head>
<body>
<div class="container">

    <div class="topnav">

        <a class="active" href="/show-items">Home</a>
        <a href="/show-items/my-items">Show my items</a>

        <div class="topnav-right">
            <a>${currentUser}</a>
            <a href="/logout">Logout</a>
        </div>

    </div>

    <div class="sell-container">

        <f:form method="post" action="/sell/new">
            <label>
                <b>Title: </b>
                <input type="text" name="title"/>
                <f:errors path="title" cssClass="error"/>
            </label>
            <label>
                <b>Description:</b>
                <input type="text" name="description"/>
                <f:errors path="description" cssClass="error"/>
            </label>
            <label>
                <b>Stop date</b>
                <input type="text" name="stopDate"/>
                <f:errors path="stopDate" cssClass="error"/>
            </label>
            <label>
                <b>Start price</b>
                <input type="text" name="startPrice"/>
                <f:errors path="startPrice" cssClass="error"/>
            </label>
            <label>
                <b>Bid inc.</b>
                <input type="text" name="bidInc"/>
                <f:errors path="bidInc" cssClass="error"/>
            </label>
            <button type="submit">Sell</button>
        </f:form>
    </div>

</div>
</body>
</html>
