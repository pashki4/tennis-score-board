<%@ page import="com.vyshniakov.model.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All matches</title>
</head>
<body>
<% List<Match> allMatches = (List<Match>) request.getAttribute("allMatches");%>
<% for (Match match : allMatches) { %>
<%= match%>
<br>
<%}%>
</body>
</html>
