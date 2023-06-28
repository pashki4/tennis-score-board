package com.vyshniakov.service;

import com.vyshniakov.tennis.OngoingMatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();
    private OngoingMatchesService() {

    }

    public static Map<UUID, OngoingMatch> getOngoingMatches() {
        return matches;
    }

    public static OngoingMatch getMatchByUUID(UUID uuid) {
        return matches.get(uuid);
    }

    public static void addMatch(OngoingMatch ongoingMatch) {
        matches.put(ongoingMatch.getUuid(), ongoingMatch);
    }
}
