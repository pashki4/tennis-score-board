<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vyshniakov.tennis.OngoingMatch" %>
<html>
<head>
    <title>Final score</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .home a {
            color: gray;
        }
    </style>
</head>
<body>
<% OngoingMatch endedMatch = (OngoingMatch) request.getAttribute("endedMatch");
    int setsCount = endedMatch.getSets().size();
%>
<fieldset>
    <legend>Final score:</legend>
    <table class="finalScore">

        <%
            if (setsCount == 2) {%>
        <tr>
            <th></th>
            <th colspan="3">Final</th>
        </tr>
        <tr>
            <td><%= endedMatch.getPlayer1().getName()%>
            <td><%= endedMatch.getSets().get(0).player1WinGamesCount()%>
            <td><%= endedMatch.getSets().get(1).player1WinGamesCount()%>
        </tr>
        <tr>
            <td><%= endedMatch.getPlayer2().getName()%>
            <td><%= endedMatch.getSets().get(0).player2WinGamesCount()%>
            <td><%= endedMatch.getSets().get(1).player2WinGamesCount()%>
        </tr>
        <%} else {%>
        <tr>
            <th></th>
            <th colspan="3">Final</th>
        </tr>
        <tr>
            <td><%= endedMatch.getPlayer1().getName()%>
            <td><%= endedMatch.getSets().get(0).player1WinGamesCount()%>
            <td><%= endedMatch.getSets().get(1).player1WinGamesCount()%>
            <td><%= endedMatch.getSets().get(2).player1WinGamesCount()%>
        </tr>
        <tr>
            <td><%= endedMatch.getPlayer2().getName()%>
            <td><%= endedMatch.getSets().get(0).player2WinGamesCount()%>
            <td><%= endedMatch.getSets().get(1).player2WinGamesCount()%>
            <td><%= endedMatch.getSets().get(2).player2WinGamesCount()%>
        </tr>
        <%}%>
    </table>
    <div class="home">
        <a href="${pageContext.request.contextPath}/">Go home</a>
    </div>
</fieldset>
</body>
</html>
