<%@ page import="com.rdlab.marketplace.domain.Lot" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<a href="/show-items/my-items">show my items</a>
<table class="lot-list">
    <tr class="all-col">
        <th class="id-col">ID</th>
        <th>Title</th>
        <th class="description-col">Description</th>
        <th>Seller</th>
        <th>Start price</th>
        <th>Stop date</th>
        <th>Bid Inc</th>
        <th>Bidder</th>
        <th>Bidding</th>
    </tr>

    <c:forEach items="${lotList}" var="lot">
        <tr>
            <td class="id-col1">${lot.id}</td>
            <td>${lot.item.title}</td>
            <td>${lot.item.description}</td>
            <td>${lot.user.username}</td>
            <td>${lot.startPrice}</td>
            <td>${lot.stopDate}</td>
            <td>${lot.bidInc}</td>
            <td>${lot.bid.username}</td>
            <td><form action="/show-items" method="post" >
                <input type="text" >
                <button type="submit" >Submit</button>
            </form></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
