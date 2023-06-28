package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import com.vyshniakov.tennis.OngoingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OngoingMatchTest {
    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private OngoingMatch ongoingMatch;

    @BeforeEach
    void setup() {
        ongoingMatch = new OngoingMatch(player1, player2, true);
    }

    @Test
    @DisplayName("player1 win the BO3 match")
    void player1WinBO3() {
        assertNull(ongoingMatch.getWinner());
        addMatchPointsToPlayer(true, 2);
        assertNotNull(ongoingMatch.getWinner());
        assertEquals(player1, ongoingMatch.getWinner());
    }

    @Test
    @DisplayName("player2 win the BO3 match")
    void player2WinBO3() {
        assertNull(ongoingMatch.getWinner());
        addMatchPointsToPlayer(false, 2);
        assertNotNull(ongoingMatch.getWinner());
        assertEquals(player2, ongoingMatch.getWinner());
    }

    @Test
    @DisplayName("player1 win the BO5 match")
    void player1WinBO5() {
        ongoingMatch = new OngoingMatch(player1, player2, false);
        assertNull(ongoingMatch.getWinner());
        addMatchPointsToPlayer(true, 3);
        assertNotNull(ongoingMatch.getWinner());
        assertEquals(player1, ongoingMatch.getWinner());
    }

    @Test
    @DisplayName("player2 win the BO5 match")
    void player2WinBO5() {
        ongoingMatch = new OngoingMatch(player1, player2, false);
        assertNull(ongoingMatch.getWinner());
        addMatchPointsToPlayer(false, 3);
        assertNotNull(ongoingMatch.getWinner());
        assertEquals(player2, ongoingMatch.getWinner());
    }


    private void addMatchPointsToPlayer(boolean firstPlayer, int matchPointsCount) {
        for (int i = 0; i < matchPointsCount; i++) {
            addSetScoreToPlayer(firstPlayer);
        }
    }

    private void addGameScoreToPlayer(boolean firstPlayer) {
        for (int i = 0; i < 4; i++) {
            if (firstPlayer) {
                ongoingMatch.addPlayer1GamePoint();
            } else {
                ongoingMatch.addPlayer2GamePoint();
            }
        }
    }

    private void addSetScoreToPlayer(boolean firstPlayer) {
        for (int i = 0; i < 6; i++) {
            addGameScoreToPlayer(firstPlayer);
        }
    }

}