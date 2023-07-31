<%@ page import="java.util.*,
                 com.vyshniakov.service.OngoingMatchesService,
                 com.vyshniakov.tennis.OngoingMatch,
                 com.vyshniakov.tennis.GamePoints" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match score</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Match Score</h1>
<% UUID matchId = UUID.fromString(request.getParameter("uuid"));%>
<% OngoingMatch ongoingMatch = OngoingMatchesService.getMatchByUUID(matchId); %>
<table>
    <tr>
        <th>PLAYER</th>
        <th>SETS</th>
        <th>GAMES</th>
        <th>POINTS</th>
    </tr>
    <tr>
        <td><%= ongoingMatch.getPlayer1().getName()%></td>
        <td><%= ongoingMatch.getPlayer1Score()%>
        </td>
        <td><%= ongoingMatch.getCurrentSet().getPlayer1Score()%>
        </td>
        <td>
            <%
                if (ongoingMatch.getCurrentSet().getCurrentGame().getWinner() != null) {
                    out.print(GamePoints.LOVE);
                } else {
                    out.print(ongoingMatch.getCurrentSet().getCurrentGame().getPlayer1Points());
                }
            %>
        </td>
    </tr>
    <tr>
        <td><%= ongoingMatch.getPlayer2().getName()%>
        </td>
        <td><%= ongoingMatch.getPlayer2Score()%>
        </td>
        <td><%= ongoingMatch.getCurrentSet().getPlayer2Score()%>
        </td>
        <td>
            <%
                if (ongoingMatch.getCurrentSet().getCurrentGame().getWinner() != null) {
                    out.print(GamePoints.LOVE);
                } else {
                    out.print(ongoingMatch.getCurrentSet().getCurrentGame().getPlayer2Points());
                }
            %>
        </td>
    </tr>
</table>

<fieldset>
    <legend>Add point to player:</legend>
    <form class="addPointsToPlayer1" action="${pageContext.request.contextPath}/match-score?uuid=<%=matchId%>"
          method="POST">
        <button name="playerId" type="submit" value="player1"><%=ongoingMatch.getPlayer1().getName()%>
        </button>
        <button name="playerId" type="submit" value="player2"><%=ongoingMatch.getPlayer2().getName()%>
        </button>
    </form>
</fieldset>

</body>
</html>