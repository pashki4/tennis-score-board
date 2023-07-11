package com.vyshniakov.service;

import com.vyshniakov.dao.MatchDao;
import com.vyshniakov.model.Match;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
    private final MatchDao matchDao = new MatchDao(emf);

    public FinishedMatchesPersistenceService() {
    }

    public void saveMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> findAllMatches() {
        return matchDao.findAllMatches();
    }
}
