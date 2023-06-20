package com.vyshniakov.service;

import com.vyshniakov.model.OngoingMatch;

import java.util.HashMap;
import java.util.Map;

public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private Map<Integer, OngoingMatch> matches;

    public OngoingMatchesService() {
        initDb();
    }

    private void initDb() {
        this.matches = new HashMap<>();
    }

    public static OngoingMatchesService getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public Map<Integer, OngoingMatch> getOngoingMatches() {
        return matches;
    }

    public OngoingMatch getMatchById(Integer id) {
        return matches.get(id);
    }

    public void addMatch(OngoingMatch ongoingMatch) {
//        this.matches.put(ongoingMatch.getId(), ongoingMatch);
    }
}
