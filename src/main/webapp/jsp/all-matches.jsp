<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All matches</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h3>All matches</h3>
<c:set var="matches" value='<%=request.getAttribute("matches")%>'/>
<c:forEach var="match" items="${matches}">
    <c:out value="${match}"/>
    <br>
</c:forEach>
</body>
</html>
