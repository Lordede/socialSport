<%-- Author: Hubertus Seitz--%>

<%@ page language="java" contentType="application/json; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

[
<c:forEach var="leaderboard" items="${leaderboard}" varStatus="status">
{
    "id"        : "${leaderboard.id}",
    "username"  : "${leaderboard.username}",
    "points"    : "${leaderboard.points}"

}<c:if test="${not status.last}">,</c:if>
</c:forEach>
]