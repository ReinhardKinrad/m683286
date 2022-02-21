<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: all
  Date: 08.02.2022
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<head>
    <link rel="stylesheet" href="../../resources/css/show.css">
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="topnav">
        <a class="active" href="/show-items">Home</a>
        <security:authorize access="hasRole('ROLE_USER')">
            <a href="/show-items/my-items">Show my items</a>
            <a href="/show-items/my-bids">Show my bids</a>
            <a href="/sell/new">Sell</a>
            <div class="topnav-right">
                <a>${currentUser}</a>
                <a href="/logout">Logout</a>
            </div>
        </security:authorize>
        <security:authorize access="!isAuthenticated()">
            <div class="topnav-right">
                <a href="/login">Login</a>
            </div>
        </security:authorize>

    </div>

    <div class="table-container">
        <table class="lot-list">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th class="description-col">Description</th>
                <th>Seller</th>
                <th>Start price</th>
                <th>Stop date</th>
                <th>Bid Inc</th>
                <th>Bidder</th>
                <security:authorize access="isAuthenticated()">
                    <th>Bidding</th>
                </security:authorize>

            </tr>

            <c:forEach items="${lotList}" var="lot">
                <tr>
                    <td>${lot.id}</td>
                    <td>${lot.item.title}</td>
                    <td>${lot.item.description}</td>
                    <td>${lot.user.username}</td>
                    <td>${lot.startPrice}</td>
                    <td>${lot.stopDate}</td>
                    <td>${lot.bidInc}</td>
                    <td>${lot.bid.username}</td>
                    <security:authorize access="isAuthenticated()">
                        <c:choose>
                            <c:when test="${lot.user.username != currentUser}">
                                <td>
                                    <f:form method="post" action="/show-items/${lot.id}">
                                        <label>
                                            <input type="text" name="price">
                                        </label>
                                        <button type="submit">Submit</button>
                                    </f:form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    It's your lot!
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </security:authorize>
                </tr>
            </c:forEach>

        </table>
    </div>

</div>

</body>
</html>
