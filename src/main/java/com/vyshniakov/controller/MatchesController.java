package com.vyshniakov.controller;

import com.vyshniakov.model.Match;
import com.vyshniakov.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches/*")
public class MatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        String playerName = null;
        if (req.getParameter("filter_by_player_name") != null) {
            playerName = req.getParameter("filter_by_player_name");
        }
        FinishedMatchesPersistenceService persistenceService = new FinishedMatchesPersistenceService();
        List<Match> matches;
        if (playerName != null) {
            matches = persistenceService.findAllMatchesPaginationFilterByPlayerName(page, playerName);
        } else {
            matches = persistenceService.findAllMatchesPagination(page);
        }
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/jsp/all-matches.jsp").forward(req, resp);
    }
}
