package com.vyshniakov.controller;

import com.vyshniakov.dao.PlayerDao;
import com.vyshniakov.model.Player;
import com.vyshniakov.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/new-match")
public class MatchScoreController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
        List<String> players = getPlayers(req);
        validatePlayers(players);

    }

    private List<String> getPlayers(HttpServletRequest req) throws IOException {
        return req.getReader().lines()
                .map(s -> s.split("&"))
                .map(array -> array[1])
                .toList();
    }

    private void validatePlayers(List<String> players) {
        PlayerDao playerDao = new PlayerDao();
        for (String player : players) {
            if (playerDao.findPlayerByName(player).isEmpty()) {
                playerDao.addPlayer(new Player(player));
            }
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //resp.sendRedirect("match-started.jsp");
//        req.getRequestDispatcher("match-started.jsp").forward(req, resp);
//    }

}
