package com.vyshniakov.controller;

import com.vyshniakov.dao.MatchDaoImpl;
import com.vyshniakov.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/match-overview")
public class MatchOverviewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchDaoImpl matchDao = new MatchDaoImpl();
        List<Match> allMatches = matchDao.findAllMatches();
        req.setAttribute("allMatches", allMatches);
        req.getRequestDispatcher("/jsp/all-matches.jsp").forward(req, resp);
    }
}
