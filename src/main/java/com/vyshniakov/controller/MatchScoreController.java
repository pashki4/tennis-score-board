package com.vyshniakov.controller;

import com.vyshniakov.dao.MatchDaoImpl;
import com.vyshniakov.model.Match;
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
        addPointToPlayerById(playerId, ongoingMatch);

        if (ongoingMatch.isEnded()) {
            OngoingMatchesService.removeEndedMatchByUUID(ongoingMatch.getUuid());
            MatchDaoImpl matchDao = new MatchDaoImpl();
            Match endedMatch = mapToMatch(ongoingMatch);
            matchDao.save(endedMatch);
            req.setAttribute("endedMatch", ongoingMatch);
            req.getRequestDispatcher("/jsp/match-final-score.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/match-current-score.jsp?uuid=" + ongoingMatch.getUuid())
                    .forward(req, resp);
        }
    }

    private Match mapToMatch(OngoingMatch ongoingMatch) {
        Match result = new Match();
        result.setPlayer1(ongoingMatch.getPlayer1());
        result.setPlayer2(ongoingMatch.getPlayer2());
        result.setWinner(ongoingMatch.getWinner());
        return result;
    }

    private void addPointToPlayerById(String playerId, OngoingMatch ongoingMatch) {
        if (playerId.equals("player1")) {
            ongoingMatch.addPlayer1GamePoint();
        } else {
            ongoingMatch.addPlayer2GamePoint();
        }
    }
}
