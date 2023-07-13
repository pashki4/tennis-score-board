<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All matches</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .pagination a {
            color: gray;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
        }

        .pagination a.active {
            background-color: dodgerblue;
            color: white;
        }

        .pagination a:hover:not(.active) {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<h3>All matches</h3>
<c:set var="matches" value='<%=request.getAttribute("matches")%>'/>
<c:forEach var="match" items="${matches}">
    <c:out value="${match}"/>
    <br>
</c:forEach>

<% int allAvailableRecords = Math.toIntExact((Long) request.getAttribute("availableRecords"));
    int recordPerPage = (int) request.getAttribute("recordsPerPage");
    int pagesToShow = allAvailableRecords / recordPerPage;
    if (pagesToShow > 1) {%>
<c:set var="counter" value="1"/>
<c:set var="playerName" value='<%=request.getAttribute("playerName")%>'/>
<% for (int i = 1; i <= pagesToShow; i++) {
    if (i == (int) request.getAttribute("currentPage")) {%>
<div class="pagination">
    <a class="active"
       href="${pageContext.request.contextPath}/matches?page=${counter}&filter_by_player_name=${playerName}">${counter}</a>
</div>
<%} else {%>
<div class="pagination">
    <a href="${pageContext.request.contextPath}/matches?page=${counter}&filter_by_player_name=${playerName}">${counter}</a>
</div>
<%}%>
<c:set var="counter" value="${counter + 1}"/>
<%
        }
    }
%>
</body>
</html>
