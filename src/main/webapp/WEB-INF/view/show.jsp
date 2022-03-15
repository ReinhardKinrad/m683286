<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="../../resources/css/show.css">
    <link rel="icon" href="../../resources/ico/favicon.png">
    <title>Marketplace</title>
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
        <security:authorize access="!isAuthenticated()">
            <div class="navbar-right">
                <a href="/login">Login</a>
            </div>
        </security:authorize>

    </div>

    <div class="table-container">
        <f:form cssClass="search-line" method="get" action="/show-items/search">
            <label>
                <input class="t" type="text" name="item" placeholder="searching...">
            </label>
            <button class="searchbt" type="submit">search</button>
        </f:form>
        <table class="lot-list">
            <tr>
                <th id="id">ID</th>
                <th id="title">Title</th>
                <th class="description-col">Description</th>
                <th>Seller</th>
                <th id="s_price">Start price</th>
                <th id="stop_date">Stop date</th>
                <th id="bid_inc">Bid Inc</th>
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
                                            <input id="bid" type="text" name="price">
                                        </label>
                                        <button type="submit">Submit</button>
                                    </f:form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <a href="/edit/${lot.id}">Edit</a>
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
<footer>
    <br>
</footer>
<script src="/resources/js/show.js">
</script>
</html>
