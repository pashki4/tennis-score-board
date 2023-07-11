package com.vyshniakov.service;

import com.vyshniakov.dao.MatchDaoImpl;
import com.vyshniakov.model.Match;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
    private final MatchDaoImpl matchDao = new MatchDaoImpl(emf);

    public FinishedMatchesPersistenceService() {
    }

    public void saveMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> findAllMatches() {
        return matchDao.findAllMatches();
    }
}
