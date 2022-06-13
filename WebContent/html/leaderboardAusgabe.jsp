<%@ page language="java" contentType="application/jason; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="set" items="${JoinBean}" varStatus="status">
{
    "id":${set.id}
    "name":


}
</c:forEach>
]