package com.vyshniakov.controller;

import com.vyshniakov.dao.MatchDao;
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
        Long allAvailableRecords = 0L;

        //Not atomic operation
        if (playerName != null && !playerName.isBlank()) {
            matches = persistenceService.findAllMatchesPaginationFilterByPlayerName(page, playerName);
            allAvailableRecords = persistenceService.recordsByPlayerName(playerName);
        } else {
            matches = persistenceService.findAllMatchesPagination(page);
            allAvailableRecords = persistenceService.records();
        }
        req.setAttribute("matches", matches);
        req.setAttribute("availableRecords", allAvailableRecords);
        req.setAttribute("playerName", playerName);
        req.setAttribute("recordsPerPage", MatchDao.RECORDS_PER_PAGE);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/jsp/all-matches.jsp").forward(req, resp);
    }
}
