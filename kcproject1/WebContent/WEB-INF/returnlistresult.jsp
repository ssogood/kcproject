<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>returnlistresult.jsp</title>
</head>
<body>
<c:set var="returnList" value="${requestScope.returnList}"></c:set>
<c:forEach var="info" items="${returnList}">
${info.return_no}
${info.return_date}
${info.return_state_flag}
<br>
</c:forEach>

</body>
</html>