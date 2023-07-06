package com.vyshniakov.controller;

import com.vyshniakov.service.OngoingMatchesService;
import com.vyshniakov.tennis.OngoingMatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score/*")
public class MatchScoreController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerId = req.getReader().lines()
                .map(input -> input.split("=")[1])
                .findAny()
                .orElseThrow();
        UUID matchId = UUID.fromString(req.getParameter("uuid"));

        OngoingMatch ongoingMatch = OngoingMatchesService.getMatchByUUID(matchId);

        if (playerId.equals("player1")) {
            ongoingMatch.addPlayer1GamePoint();
        } else {
            ongoingMatch.addPlayer2GamePoint();
        }
        resp.sendRedirect("/jsp/match-score.jsp?uuid=" + ongoingMatch.getUuid());
    }
}
