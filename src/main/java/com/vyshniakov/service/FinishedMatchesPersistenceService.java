package com.vyshniakov.service;

import com.vyshniakov.dao.MatchDao;
import com.vyshniakov.model.Match;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
    private final MatchDao matchDao = new MatchDao(emf);

    public FinishedMatchesPersistenceService() {
    }

    public void saveMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> findAllMatchesPagination(int pageNumber) {
        return matchDao.findAllMatchesPagination(pageNumber);
    }

    public List<Match> findAllMatchesPaginationFilterByPlayerName(int pageNumber, String playerName) {
        return matchDao.findAllMatchesPaginationFilterByPlayerName(pageNumber, playerName);
    }

    public Long recordsByPlayerName(String playerName) {
        return matchDao.recordsByPlayerName(playerName);
    }

    public Long records() {
        return matchDao.records();
    }
}
