package com.vyshniakov.controller;

import com.vyshniakov.model.Player;
import com.vyshniakov.service.OngoingMatchesService;
import com.vyshniakov.tennis.OngoingMatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Player> players = extractPlayers(req);
        OngoingMatch match = new OngoingMatch(players.get(0), players.get(1), true);
        OngoingMatchesService.addMatch(match);
//        req.setAttribute("match", OngoingMatchesService.getOngoingMatches().get(match.getUuid()));
//        req.getRequestDispatcher("/jsp/match-score.jsp?uuid=" + match.getUuid())
//                .forward(req, resp);
        resp.sendRedirect("/jsp/match-score.jsp?uuid=" + match.getUuid());
    }

    private List<Player> extractPlayers(HttpServletRequest req) throws IOException {
        return req.getReader().lines()
                .map(parameters -> parameters.split("&"))
                .flatMap(Arrays::stream)
                .map(keyValue -> keyValue.split("=")[1])
                .map(Player::new)
                .toList();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/match-started.jsp").forward(req, resp);
    }
}
