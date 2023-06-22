package com.vyshniakov.service;

import com.vyshniakov.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private final Player player1 = new Player("player1");
    private final Player player2 = new Player("player2");
    private Match match;

    @BeforeEach
    void setup() {
        match = new Match(player1, player2, true);
    }

    @Test
    @DisplayName("player1 win the BO3 match")
    void player1WinBO3() {
        assertNull(match.getWinner());
        addMatchPointsToPlayer(true, 2);
        assertNotNull(match.getWinner());
        assertEquals(player1, match.getWinner());
    }

    @Test
    @DisplayName("player2 win the BO3 match")
    void player2WinBO3() {
        assertNull(match.getWinner());
        addMatchPointsToPlayer(false, 2);
        assertNotNull(match.getWinner());
        assertEquals(player2, match.getWinner());
    }

    @Test
    @DisplayName("player1 win the BO5 match")
    void player1WinBO5() {
        match = new Match(player1, player2, false);
        assertNull(match.getWinner());
        addMatchPointsToPlayer(true, 3);
        assertNotNull(match.getWinner());
        assertEquals(player1, match.getWinner());
    }

    @Test
    @DisplayName("player2 win the BO5 match")
    void player2WinBO5() {
        match = new Match(player1, player2, false);
        assertNull(match.getWinner());
        addMatchPointsToPlayer(false, 3);
        assertNotNull(match.getWinner());
        assertEquals(player2, match.getWinner());
    }


    private void addMatchPointsToPlayer(boolean firstPlayer, int matchPointsCount) {
        for (int i = 0; i < matchPointsCount; i++) {
            addSetScoreToPlayer(firstPlayer);
        }
    }

    private void addGameScoreToPlayer(boolean firstPlayer) {
        for (int i = 0; i < 4; i++) {
            if (firstPlayer) {
                match.addPlayer1GamePoint();
            } else {
                match.addPlayer2GamePoint();
            }
        }
    }

    private void addSetScoreToPlayer(boolean firstPlayer) {
        for (int i = 0; i < 6; i++) {
            addGameScoreToPlayer(firstPlayer);
        }
    }

}