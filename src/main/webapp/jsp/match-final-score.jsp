<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.vyshniakov.tennis.OngoingMatch" %>
<html>
<head>
    <title>Final score</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<% OngoingMatch endedMatch = (OngoingMatch) request.getAttribute("endedMatch"); %>
<fieldset>
    <legend>Final score:</legend>
    <table class="finalScore">
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
    </table>
    <div>
        <a href="/">Go home</a>
    </div>
</fieldset>
</body>
</html>
